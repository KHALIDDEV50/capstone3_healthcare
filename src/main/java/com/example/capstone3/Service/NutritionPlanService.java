
package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.NutritionPlan;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.HealthProfileRepository;
import com.example.capstone3.Repository.NutritionPlanRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NutritionPlanService {

    private final NutritionPlanRepository nutritionPlanRepository;
    private final UserRepository userRepository;
    private final HealthProfileRepository healthProfileRepository;

    // Get All Nutrition Plans
    public List<NutritionPlan> getAllNutritionPlans() {
        return nutritionPlanRepository.findAll();
    }

    // Get Nutrition Plan By ID
    public NutritionPlan getNutritionPlanById(Integer id) {

        NutritionPlan nutritionPlan =
                nutritionPlanRepository.findById(id).orElse(null);

        if (nutritionPlan == null) {
            throw new ApiException("Nutrition plan not found");
        }

        return nutritionPlan;
    }

    // Get Nutrition Plan By User ID
    public NutritionPlan getNutritionPlanByUserId(Integer userId) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ApiException("User not found");
        }

        NutritionPlan nutritionPlan =
                nutritionPlanRepository.findNutritionPlanByUserId(userId);

        if (nutritionPlan == null) {
            throw new ApiException("This user has no nutrition plan");
        }

        return nutritionPlan;
    }

    // Add Nutrition Plan
    public void addNutritionPlan(Integer userId, NutritionPlan nutritionPlan) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ApiException("User not found");
        }

        // User must have a Health Profile first
        if (!healthProfileRepository.existsByUserId(userId)) {
            throw new ApiException(
                    "User must have a health profile before creating a nutrition plan"
            );
        }

        // One Nutrition Plan per User
        if (nutritionPlanRepository.existsByUserId(userId)) {
            throw new ApiException("User already has a nutrition plan");
        }

        nutritionPlan.setId(null);
        nutritionPlan.setUser(user);

        nutritionPlanRepository.save(nutritionPlan);
    }

    // Update Nutrition Plan
    public void updateNutritionPlan(Integer id, NutritionPlan nutritionPlan) {

        NutritionPlan oldNutritionPlan =
                nutritionPlanRepository.findById(id).orElse(null);

        if (oldNutritionPlan == null) {
            throw new ApiException("Nutrition plan not found");
        }

        oldNutritionPlan.setItems(nutritionPlan.getItems());
        oldNutritionPlan.setSummary(nutritionPlan.getSummary());
        oldNutritionPlan.setReason(nutritionPlan.getReason());

        nutritionPlanRepository.save(oldNutritionPlan);
    }

    // Delete Nutrition Plan
    public void deleteNutritionPlan(Integer id) {

        NutritionPlan nutritionPlan =
                nutritionPlanRepository.findById(id).orElse(null);

        if (nutritionPlan == null) {
            throw new ApiException("Nutrition plan not found");
        }

        nutritionPlanRepository.delete(nutritionPlan);
    }
}
