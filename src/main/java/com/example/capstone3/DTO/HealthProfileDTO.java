package com.example.capstone3.DTO;

import com.example.capstone3.Enum.ChronicCondition;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthProfileDTO {

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "Height is required")
    @DecimalMin(
            value = "50.0",
            message = "Height must be at least 50 cm"
    )
    @DecimalMax(value = "250.0", message = "Height must be at most 250 cm")
    private Double heightCm;

    /*@NotNull(message = "Activity level is required")
    private ActivityLevel activityLevel;*/

    // Use [] if there are no chronic conditions
    @NotNull(message = "Conditions are required (use [] if none)")
    private List<
            @NotNull(message = "Condition cannot be null")
                    ChronicCondition
            > conditions;

    @NotNull(message = "Exercise risk is required")
    private Boolean exerciseRisk;
}