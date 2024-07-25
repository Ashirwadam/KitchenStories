package com.kitchen.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
public class Steps {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "step_index")
    private int index;
    private String description;
    private String image;
    private String utensils;
    private String ingredientMeasurements;
}
