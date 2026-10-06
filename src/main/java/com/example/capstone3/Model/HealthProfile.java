
package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "health_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // User Relationship
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonIgnore
    private User user;

    // Height
    private Double heightCm;

    // Chronic Conditions
    @Column(columnDefinition = "text", nullable = false)
    private String conditions;

    // Exercise Risk
    @Column(nullable = false)
    private Boolean exerciseRisk;

    // Created At
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // Updated At
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
