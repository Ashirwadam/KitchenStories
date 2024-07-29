package com.kitchen.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String image;
    private String profile;
    private String link;
    @Column(length = 10000)
    private String description;
}
