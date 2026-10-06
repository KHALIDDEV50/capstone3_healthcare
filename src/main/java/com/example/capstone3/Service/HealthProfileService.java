
package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.HealthProfile;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.HealthProfileRepository;
import com.example.capstone3.Repository.NutritionPlanRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class HealthProfileService {

    private final HealthProfileRepository healthProfileRepository;
    private final UserRepository userRepository;
    private final NutritionPlanRepository nutritionPlanRepository;

    // Get All Health Profiles
    public List<HealthProfile> getAllHealthProfiles() {
        return healthProfileRepository.findAll();
    }

    // Get Health Profile By ID
    public HealthProfile getHealthProfileById(Integer id) {

        HealthProfile healthProfile =
                healthProfileRepository.findById(id).orElse(null);

        if (healthProfile == null) {
            throw new ApiException("Health profile not found");
        }

        return healthProfile;
    }

    // Get Health Profile By User ID
    public HealthProfile getHealthProfileByUserId(Integer userId) {

        User user =
                userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ApiException("User not found");
        }

        HealthProfile healthProfile =
                healthProfileRepository.findHealthProfileByUserId(userId);

        if (healthProfile == null) {
            throw new ApiException("This user has no health profile");
        }

        return healthProfile;
    }

    // Add Health Profile
    public void addHealthProfile(
            Integer userId,
            HealthProfile healthProfile) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ApiException("User not found");
        }

        // One Health Profile per User
        if (healthProfileRepository.existsByUserId(userId)) {
            throw new ApiException("User already has a health profile");
        }

        // Validate conditions
        validateConditions(healthProfile.getConditions());

        healthProfile.setId(null);
        healthProfile.setUser(user);

        healthProfileRepository.save(healthProfile);
    }

    // Update Health Profile
    public void updateHealthProfile(Integer id, HealthProfile healthProfile) {

        HealthProfile oldHealthProfile = healthProfileRepository.findById(id).orElse(null);

        if (oldHealthProfile == null) {
            throw new ApiException("Health profile not found");
        }

        // Validate conditions
        validateConditions(healthProfile.getConditions());

        oldHealthProfile.setHeightCm(healthProfile.getHeightCm());

        oldHealthProfile.setConditions(healthProfile.getConditions());

        oldHealthProfile.setExerciseRisk(healthProfile.getExerciseRisk());

        healthProfileRepository.save(oldHealthProfile);
    }

    // Delete Health Profile
    public void deleteHealthProfile(Integer id) {

        HealthProfile healthProfile = healthProfileRepository.findById(id).orElse(null);

        if (healthProfile == null) {
            throw new ApiException("Health profile not found");
        }

        // Nutrition plan depends on health profile
        if (nutritionPlanRepository.existsByUserId(healthProfile.getUser().getId())) {

            throw new ApiException("Delete the user's nutrition plan before deleting the health profile");
        }

        healthProfileRepository.delete(healthProfile);
    }

    // Validate Chronic Conditions
    private void validateConditions(String conditions) {

        if (conditions == null || conditions.trim().isEmpty()) {
            throw new ApiException("Conditions are required");
        }

        // Must start and end as JSON array
        if (!conditions.trim().startsWith("[") || !conditions.trim().endsWith("]")) {

            throw new ApiException("Conditions must be a valid JSON array");
        }
    }
}
