package com.kitchen.repository;

import com.kitchen.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    // return all recipes that contain the specific tag
//    @Query("SELECT r FROM recipe r, recipe_tags rt, Tag t WHERE r.id = rt.recipe_id and t.id = rt.tags_id and t.name LIKE %:tag%")
    @Query("SELECT r FROM Recipe r JOIN r.tags t WHERE t.name LIKE %:tag%")
    List<Recipe> findByRecipeTagsContaining(@Param("tag") String tag);

    @Query("SELECT r FROM Recipe r JOIN r.chef c WHERE c.id = :id")
    List<Recipe> findAllRecipesByChef(@Param("id") UUID id);
}

/*
SELECT r.*
FROM recipe r
JOIN recipe_tags rt ON r.id = rt.recipe_id
JOIN tag t ON rt.tags_id = t.id
WHERE t.name LIKE %?1%;
 */