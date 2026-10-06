package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSignDTO {

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "Type is required")
    private String type;

    @NotNull(message = "Primary value is required")
    @Positive(message = "Primary value must be positive")
    private Double systolic;

    @Positive(message = "Secondary value must be positive")
    private Double diastolic;

    private String context;

    @NotNull(message = "Measured at is required")
    @PastOrPresent(message = "Measured at cannot be in the future")
    private LocalDateTime measuredAt;
}