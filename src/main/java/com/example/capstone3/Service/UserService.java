
package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.HealthProfile;
import com.example.capstone3.Model.NutritionPlan;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.HealthProfileRepository;
import com.example.capstone3.Repository.NutritionPlanRepository;
import com.example.capstone3.Repository.UserRepository;
import com.example.capstone3.Repository.VitalSignRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final NutritionPlanRepository nutritionPlanRepository;
    private final HealthProfileRepository healthProfileRepository;
    private final VitalSignRepository vitalSignRepository;

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User By ID
    public User getUserById(Integer id) {

        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new ApiException("User not found");
        }

        return user;
    }

    // Add User
    public void addUser(User user) {

        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ApiException("Email already exists");
        }

        if (userRepository.findUserByPhone(user.getPhone()) != null) {
            throw new ApiException("Phone already exists");
        }

        user.setId(null);

        userRepository.save(user);
    }

    // Update User
    public void updateUser(Integer id, User user) {

        User oldUser = userRepository.findUserById(id);

        if (oldUser == null) {
            throw new ApiException("User not found");
        }

        User emailOwner =
                userRepository.findUserByEmail(user.getEmail());

        if (emailOwner != null && !emailOwner.getId().equals(id)) {
            throw new ApiException("Email already exists");
        }

        User phoneOwner =
                userRepository.findUserByPhone(user.getPhone());

        if (phoneOwner != null && !phoneOwner.getId().equals(id)) {
            throw new ApiException("Phone already exists");
        }

        oldUser.setFullName(user.getFullName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone(user.getPhone());
        oldUser.setPassword(user.getPassword());
        oldUser.setDateOfBirth(user.getDateOfBirth());
        oldUser.setGender(user.getGender());
        oldUser.setWhatsappOptIn(user.getWhatsappOptIn());

        userRepository.save(oldUser);
    }

    // Delete User
    // Delete health data first to avoid FK errors
    @Transactional
    public void deleteUser(Integer id) {

        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new ApiException("User not found");
        }

        // Delete Nutrition Plan
        NutritionPlan nutritionPlan = nutritionPlanRepository.findNutritionPlanByUserId(id);

        if (nutritionPlan != null) {
            nutritionPlanRepository.delete(nutritionPlan);
        }

        // Delete Health Profile
        HealthProfile healthProfile = healthProfileRepository.findHealthProfileByUserId(id);

        if (healthProfile != null) {
            healthProfileRepository.delete(healthProfile);
        }

        // Delete Vital Signs
        vitalSignRepository.deleteAll(vitalSignRepository.findAllByUser_IdOrderByMeasuredAtDesc(id)
        );

        // Delete User
        userRepository.delete(user);
    }
}