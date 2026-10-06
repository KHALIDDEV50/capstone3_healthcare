
        package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.Model.HealthProfile;
import com.example.capstone3.Service.HealthProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/health-profiles")
@AllArgsConstructor
public class HealthProfileController {

    private final HealthProfileService healthProfileService;

    // Get All Health Profiles
    @GetMapping("/get")
    public ResponseEntity<?> getAllHealthProfiles() {

        return ResponseEntity
                .status(200)
                .body(healthProfileService.getAllHealthProfiles());
    }

    // Get Health Profile By ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getHealthProfileById(
            @PathVariable Integer id) {

        return ResponseEntity
                .status(200)
                .body(healthProfileService.getHealthProfileById(id));
    }

    // Get Health Profile By User ID
    @GetMapping("/get/user/{userId}")
    public ResponseEntity<?> getHealthProfileByUserId(
            @PathVariable Integer userId) {

        return ResponseEntity
                .status(200)
                .body(
                        healthProfileService
                                .getHealthProfileByUserId(userId)
                );
    }

    // Add Health Profile
    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addHealthProfile(
            @PathVariable Integer userId,
            @Valid @RequestBody HealthProfile healthProfile) {

        healthProfileService.addHealthProfile(
                userId,
                healthProfile
        );

        return ResponseEntity
                .status(200)
                .body(
                        new ApiResponse(
                                "Health profile added successfully"
                        )
                );
    }

    // Update Health Profile
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHealthProfile(
            @PathVariable Integer id,
            @Valid @RequestBody HealthProfile healthProfile) {

        healthProfileService.updateHealthProfile(
                id,
                healthProfile
        );

        return ResponseEntity
                .status(200)
                .body(
                        new ApiResponse(
                                "Health profile updated successfully"
                        )
                );
    }

    // Delete Health Profile
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHealthProfile(
            @PathVariable Integer id) {

        healthProfileService.deleteHealthProfile(id);

        return ResponseEntity
                .status(200)
                .body(
                        new ApiResponse(
                                "Health profile deleted successfully"
                        )
                );
    }
}
