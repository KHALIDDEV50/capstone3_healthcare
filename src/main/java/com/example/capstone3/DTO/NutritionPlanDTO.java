package com.example.capstone3.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionPlanDTO {

    // User ID
    @NotNull(message = "User ID is required")
    private Integer userId;

    // Nutrition Items
    /*@NotEmpty(message = "Items are required")
    @Valid
    private List<NutritionItem> items;*/

    // Summary
    @Size(max = 2000, message = "Summary must be at most 2000 characters")
    private String summary;
}