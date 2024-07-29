package com.kitchen.repository;

import com.kitchen.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChefRepository extends JpaRepository<Chef, UUID> {
    Optional<Chef> findByName(String name);
}
