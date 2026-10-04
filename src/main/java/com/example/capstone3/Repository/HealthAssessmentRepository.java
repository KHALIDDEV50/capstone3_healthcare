
package com.example.capstone3.Repository;

import com.example.capstone3.Model.HealthAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthAssessmentRepository extends JpaRepository<HealthAssessment, Long> {

    // Get Health Assessments By User ID
    List<HealthAssessment> findByUserId(Long userId);

    // Get Current Health Assessment By User ID
    List<HealthAssessment> findByUserIdAndIsCurrentTrue(Long userId);
}