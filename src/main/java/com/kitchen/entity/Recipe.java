package com.kitchen.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String image;
    private String name;
    private int ratings;
    @Column(length = 10000)
    private String description;
    private String difficulty;
    private String preparationTime;
    private String bakingTime;
    private String restingTime;
    private String utensils;
    private String calories;
    private String fat;
    private String protein;
    private String carbohydrates;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredients> ingredients;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Steps> steps;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_chef",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "chef_id"))
    private Chef chef;

}
