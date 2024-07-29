package com.kitchen.service;

import com.kitchen.entity.Chef;
import com.kitchen.entity.Recipe;
import com.kitchen.entity.Tag;
import com.kitchen.repository.ChefRepository;
import com.kitchen.repository.RecipeRepository;
import com.kitchen.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private TagRepository tagRepository;
    public boolean saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return true;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
    public Recipe getRecipeById(UUID id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public List<Recipe> searchRecipe(String tag) {
        return recipeRepository.findByRecipeTagsContaining(tag);
    }

    public List<Recipe> getAllRecipesOfChef(UUID chefId) {
        return recipeRepository.findAllRecipesByChef(chefId);
    }

    public boolean saveBulkRecipe(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            Chef chef = recipe.getChef();
            Set<Tag> tagSet = recipe.getTags();
            for (Tag tag : tagSet) {
                Optional<Tag> existingTag = tagRepository.findByName(tag.getName());
                existingTag.ifPresent(value -> tag.setId(value.getId()));
            }
            if (chef != null) {
                Optional<Chef> existingChef = chefRepository.findByName(chef.getName());
                if (existingChef.isPresent()) {
                    recipe.setChef(existingChef.get());
                } else {
                    chefRepository.save(chef);
                }
            }
        }
        recipeRepository.saveAll(recipes);
        return true;
    }

    public List<Chef> getAllChefs() {
        return chefRepository.findAll();
    }
}
