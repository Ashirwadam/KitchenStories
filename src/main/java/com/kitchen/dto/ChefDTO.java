package com.kitchen.dto;

import com.kitchen.entity.Chef;
import com.kitchen.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChefDTO {
    private Chef chef;
    List<Recipe> recipes;
}
