package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.ExercisePlan;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.ExercisePlanRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercisePlanService {

    private final ExercisePlanRepository exercisePlanRepository;
    private final UserRepository userRepository;


    public List<ExercisePlan> getAllExercisePlans() {
        return exercisePlanRepository.findAll();
    }


    public void addExercisePlan(Integer userId, ExercisePlan exercisePlan) {

        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new ApiException("User not found");
        }

        ExercisePlan oldPlan = exercisePlanRepository.findExercisePlanByUserId(userId);

        if (oldPlan != null) {
            throw new ApiException("User already has an exercise plan");
        }

        exercisePlan.setUser(user);

        exercisePlanRepository.save(exercisePlan);
    }


    public void updateExercisePlan(Integer id, ExercisePlan exercisePlan) {

        ExercisePlan oldPlan = exercisePlanRepository.findExercisePlanById(id);

        if (oldPlan == null) {
            throw new ApiException("Exercise plan not found");
        }

        oldPlan.setGoal(exercisePlan.getGoal());
        oldPlan.setExercises(exercisePlan.getExercises());
        oldPlan.setSummary(exercisePlan.getSummary());

        exercisePlanRepository.save(oldPlan);
    }


    public void deleteExercisePlan(Integer id) {

        ExercisePlan exercisePlan = exercisePlanRepository.findExercisePlanById(id);

        if (exercisePlan == null) {
            throw new ApiException("Exercise plan not found");
        }

        exercisePlanRepository.delete(exercisePlan);
    }


    public ExercisePlan getExercisePlanByUserId(Integer userId) {

        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new ApiException("User not found");
        }

        ExercisePlan exercisePlan = exercisePlanRepository.findExercisePlanByUserId(userId);

        if (exercisePlan == null) {
            throw new ApiException("Exercise plan not found");
        }

        return exercisePlan;
    }
}