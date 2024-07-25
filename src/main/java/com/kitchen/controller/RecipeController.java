package com.kitchen.controller;

import com.kitchen.entity.Recipe;
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

    @PostMapping("/recipe")
    public ResponseEntity<String> saveRecipe(@RequestBody Recipe recipe) {
        boolean response = recipeService.saveRecipe(recipe);
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


}
