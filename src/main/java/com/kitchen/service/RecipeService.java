package com.kitchen.service;

import com.kitchen.entity.Recipe;
import com.kitchen.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
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
}
