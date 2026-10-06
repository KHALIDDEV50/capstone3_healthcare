package com.example.capstone3.Repository;

import com.example.capstone3.Model.ExercisePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercisePlanRepository extends JpaRepository<ExercisePlan, Integer> {

    ExercisePlan findExercisePlanById(Integer id);

    ExercisePlan findExercisePlanByUserId(Integer userId);
}