
package com.example.capstone3.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "health_assessments")
@AllArgsConstructor
@NoArgsConstructor
public class HealthAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User ID
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // Previous Assessment ID
    @Column(name = "previous_assessment_id")
    private Long previousAssessmentId;

    // Attachments JSON
    @Column(columnDefinition = "json")
    private String attachments;

    // User Notes
    @Column(name = "user_notes", columnDefinition = "text")
    private String userNotes;

    // Profile Snapshot JSON
    @Column(name = "profile_snapshot", columnDefinition = "json")
    private String profileSnapshot;

    // Extracted Values JSON
    @Column(name = "extracted_values", columnDefinition = "json")
    private String extractedValues;

    // AI Conclusion
    @Column(name = "ai_conclusion", columnDefinition = "text")
    private String aiConclusion;

    // Assessment Trend
    @Column
    private String trend;

    // Current Assessment
    @Column(name = "is_current", nullable = false)
    private Boolean isCurrent = false;

    // Assessment Date
    @Column(name = "assessment_date")
    private LocalDate assessmentDate;

    // Next Due Date
    @Column(name = "next_due_date")
    private LocalDate nextDueDate;

    // Created At
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
