package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "vital_signs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // User Relationship
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    // Vital Type
    @Column(nullable = false, length = 20)
    private String type;

    // Systolic / Glucose / Weight / Waist / Heart Rate
    private Double systolic;

    // Diastolic - Blood Pressure only
    private Double diastolic;

    // Set by Service
    @Column(length = 10)
    private String unit;

    // Glucose only
    @Column(length = 15)
    private String context;

    // Calculated by Service
    @Column(length = 10)
    private String flag;

    // Measured At
    private LocalDateTime measuredAt;

    // Created At
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}