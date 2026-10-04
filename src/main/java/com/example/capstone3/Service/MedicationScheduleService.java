
package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.MedicationScheduleRequestDTO;
import com.example.capstone3.Model.MedicationSchedule;
import com.example.capstone3.Repository.MedicationScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationScheduleService {

    private final MedicationScheduleRepository medicationScheduleRepository;

    // Get all Medication Schedule
    public List<MedicationSchedule> getAllMedicationSchedule() {

        return medicationScheduleRepository.findAll();
    }

    // Get Medication Schedule By ID
    public MedicationSchedule getMedicationScheduleById(Long id) {

        MedicationSchedule medicationSchedule = medicationScheduleRepository.findById(id).orElse(null);

        if (medicationSchedule == null) {
            throw new ApiException("Medication Schedule not found");
        }

        return medicationSchedule;
    }

    // Get Medication Schedule By User ID
    public List<MedicationSchedule> getMedicationScheduleByUserId(Long userId) {

        return medicationScheduleRepository.findByUserId(userId);
    }

    // Get Active Medication Schedule By User ID
    public List<MedicationSchedule> getActiveMedicationScheduleByUserId(Long userId) {

        return medicationScheduleRepository.findByUserIdAndIsActiveTrue(userId);
    }

    // Add Medication Schedule
    public void addMedicationSchedule(
            MedicationScheduleRequestDTO medicationScheduleRequestDTO) {

        MedicationSchedule medicationSchedule = new MedicationSchedule();

        medicationSchedule.setUserId(medicationScheduleRequestDTO.getUserId());

        medicationSchedule.setMedicationName(medicationScheduleRequestDTO.getMedicationName());

        medicationSchedule.setDosage(medicationScheduleRequestDTO.getDosage());

        medicationSchedule.setMealRelation(medicationScheduleRequestDTO.getMealRelation());

        medicationSchedule.setTimes(medicationScheduleRequestDTO.getTimes());

        medicationSchedule.setStartDate(medicationScheduleRequestDTO.getStartDate());

        medicationSchedule.setEndDate(medicationScheduleRequestDTO.getEndDate());

        medicationSchedule.setIsActive(medicationScheduleRequestDTO.getIsActive());

        medicationScheduleRepository.save(medicationSchedule);
    }

    // Update Medication Schedule
    public void updateMedicationSchedule(
            Long id,
            MedicationScheduleRequestDTO medicationScheduleRequestDTO) {

        MedicationSchedule oldMedicationSchedule = medicationScheduleRepository.findById(id).orElse(null);

        if (oldMedicationSchedule == null) {
            throw new ApiException("Medication Schedule not found");
        }

        oldMedicationSchedule.setUserId(medicationScheduleRequestDTO.getUserId());

        oldMedicationSchedule.setMedicationName(medicationScheduleRequestDTO.getMedicationName());

        oldMedicationSchedule.setDosage(medicationScheduleRequestDTO.getDosage());

        oldMedicationSchedule.setMealRelation(medicationScheduleRequestDTO.getMealRelation());

        oldMedicationSchedule.setTimes(medicationScheduleRequestDTO.getTimes());

        oldMedicationSchedule.setStartDate(medicationScheduleRequestDTO.getStartDate());

        oldMedicationSchedule.setEndDate(medicationScheduleRequestDTO.getEndDate());

        oldMedicationSchedule.setIsActive(medicationScheduleRequestDTO.getIsActive());

        medicationScheduleRepository.save(oldMedicationSchedule);
    }

    // Delete Medication Schedule
    public void deleteMedicationSchedule(Long id) {

        MedicationSchedule medicationSchedule = medicationScheduleRepository.findById(id).orElse(null);

        if (medicationSchedule == null) {
            throw new ApiException("Medication Schedule not found");
        }

        medicationScheduleRepository.delete(medicationSchedule);
    }
}