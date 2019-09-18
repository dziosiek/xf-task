package com.example.task1.jpa;

import com.example.task1.object.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealTypeRepository extends JpaRepository<MealType, String> {
}
