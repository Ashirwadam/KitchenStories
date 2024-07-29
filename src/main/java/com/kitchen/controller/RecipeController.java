package com.kitchen.controller;

import com.kitchen.dto.ChefDTO;
import com.kitchen.entity.Chef;
import com.kitchen.entity.Recipe;
import com.kitchen.repository.ChefRepository;
import com.kitchen.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ChefRepository chefRepository;

    @PostMapping("/recipe")
    public ResponseEntity<String> saveRecipe(@RequestBody Recipe recipe) {
        boolean response = recipeService.saveRecipe(recipe);
        if (!response) {
            return new ResponseEntity<>("Recipe not saved", HttpStatus.BAD_REQUEST);
        }
       return new ResponseEntity<>("Recipe saved successfully", HttpStatus.CREATED);

    }
    @PostMapping("/bulk-recipe")
    public ResponseEntity<String> saveBulkRecipe(@RequestBody List<Recipe> recipes) {
        boolean response = recipeService.saveBulkRecipe(recipes);
        if (!response) {
            return new ResponseEntity<>("Recipe not saved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Recipe saved successfully", HttpStatus.CREATED);

    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("recipeId") UUID recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping("/recipe")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/search/tag/{tag}")
    public ResponseEntity<List<Recipe>> searchRecipe(@PathVariable("tag") String tag) {
        List<Recipe> recipes = recipeService.searchRecipe(tag);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("search/chef/{chefId}")
    public ResponseEntity<ChefDTO> getAllRecipesOfChef(@PathVariable("chefId") UUID chefId) {
        List<Recipe> recipes = recipeService.getAllRecipesOfChef(chefId);
        Chef chef = chefRepository.findById(chefId).get();

        return new ResponseEntity<>(new ChefDTO(chef, recipes), HttpStatus.OK);
    }


    @GetMapping("chefs")
    public ResponseEntity<List<Chef>> getAllChefs() {
        List<Chef> chefs = recipeService.getAllChefs();
        return new ResponseEntity<>(chefs, HttpStatus.OK);
    }


}
