
package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.HealthAssessmentRequestDTO;
import com.example.capstone3.Model.HealthAssessment;
import com.example.capstone3.Repository.HealthAssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthAssessmentService {

    private final HealthAssessmentRepository healthAssessmentRepository;

    // Get all Health Assessments
    public List<HealthAssessment> getAllHealthAssessments() {

        return healthAssessmentRepository.findAll();
    }

    // Get Health Assessment By ID
    public HealthAssessment getHealthAssessmentById(Long id) {

        HealthAssessment healthAssessment = healthAssessmentRepository.findById(id).orElse(null);

        if (healthAssessment == null) {
            throw new ApiException("Health Assessment not found");
        }

        return healthAssessment;
    }

    // Get Health Assessments By User ID
    public List<HealthAssessment> getHealthAssessmentsByUserId(Long userId) {

        return healthAssessmentRepository.findByUserId(userId);
    }

    // Get Current Health Assessment By User ID
    public List<HealthAssessment> getCurrentHealthAssessmentsByUserId(Long userId) {

        return healthAssessmentRepository.findByUserIdAndIsCurrentTrue(userId);
    }

    // Add Health Assessment
    public void addHealthAssessment(
            HealthAssessmentRequestDTO healthAssessmentRequestDTO) {

        HealthAssessment healthAssessment = new HealthAssessment();

        healthAssessment.setUserId(healthAssessmentRequestDTO.getUserId());

        healthAssessment.setPreviousAssessmentId(healthAssessmentRequestDTO.getPreviousAssessmentId());

        healthAssessment.setAttachments(healthAssessmentRequestDTO.getAttachments());

        healthAssessment.setUserNotes(healthAssessmentRequestDTO.getUserNotes());

        healthAssessment.setProfileSnapshot(healthAssessmentRequestDTO.getProfileSnapshot());

        healthAssessment.setExtractedValues(healthAssessmentRequestDTO.getExtractedValues());

        healthAssessment.setAiConclusion(healthAssessmentRequestDTO.getAiConclusion());

        healthAssessment.setTrend(healthAssessmentRequestDTO.getTrend());

        healthAssessment.setIsCurrent(healthAssessmentRequestDTO.getIsCurrent());

        healthAssessment.setAssessmentDate(healthAssessmentRequestDTO.getAssessmentDate());

        healthAssessment.setNextDueDate(healthAssessmentRequestDTO.getNextDueDate());

        healthAssessmentRepository.save(healthAssessment);
    }

    // Update Health Assessment
    public void updateHealthAssessment(
            Long id,
            HealthAssessmentRequestDTO healthAssessmentRequestDTO) {

        HealthAssessment oldHealthAssessment =
                healthAssessmentRepository.findById(id).orElse(null);

        if (oldHealthAssessment == null) {
            throw new ApiException("Health Assessment not found");
        }

        oldHealthAssessment.setUserId(healthAssessmentRequestDTO.getUserId());

        oldHealthAssessment.setPreviousAssessmentId(healthAssessmentRequestDTO.getPreviousAssessmentId());

        oldHealthAssessment.setAttachments(healthAssessmentRequestDTO.getAttachments());

        oldHealthAssessment.setUserNotes(healthAssessmentRequestDTO.getUserNotes());

        oldHealthAssessment.setProfileSnapshot(healthAssessmentRequestDTO.getProfileSnapshot());

        oldHealthAssessment.setExtractedValues(healthAssessmentRequestDTO.getExtractedValues());

        oldHealthAssessment.setAiConclusion(healthAssessmentRequestDTO.getAiConclusion());

        oldHealthAssessment.setTrend(healthAssessmentRequestDTO.getTrend());

        oldHealthAssessment.setIsCurrent(healthAssessmentRequestDTO.getIsCurrent());

        oldHealthAssessment.setAssessmentDate(healthAssessmentRequestDTO.getAssessmentDate());

        oldHealthAssessment.setNextDueDate(healthAssessmentRequestDTO.getNextDueDate());

        healthAssessmentRepository.save(oldHealthAssessment);
    }

    // Delete Health Assessment
    public void deleteHealthAssessment(Long id) {

        HealthAssessment healthAssessment =
                healthAssessmentRepository.findById(id).orElse(null);

        if (healthAssessment == null) {
            throw new ApiException("Health Assessment not found");
        }

        healthAssessmentRepository.delete(healthAssessment);
    }
}