package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.MedicationScheduleDTO;
import com.example.capstone3.Model.MedicationSchedule;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.MedicationScheduleRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationScheduleService {

    private final MedicationScheduleRepository medicationScheduleRepository;
    private final UserRepository userRepository;

    // Get all Medication Schedule
    public List<MedicationSchedule> getAllMedicationSchedule() {

        return medicationScheduleRepository.findAll();
    }

    // Get Medication Schedule By ID
    public MedicationSchedule getMedicationScheduleById(Integer id) {

        MedicationSchedule medicationSchedule = medicationScheduleRepository.findById(id).orElse(null);

        if (medicationSchedule == null) {
            throw new ApiException("Medication Schedule not found");
        }

        return medicationSchedule;
    }

    // Get Medication Schedule By User ID
    public List<MedicationSchedule> getMedicationScheduleByUserId(Integer userId) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ApiException("User not found");
        }

        return medicationScheduleRepository.findByUser_Id(userId);
    }

    // Get Active Medication Schedule By User ID
    public List<MedicationSchedule>
    getActiveMedicationScheduleByUserId(Integer userId) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new ApiException("User not found");
        }

        return medicationScheduleRepository.findByUser_IdAndIsActiveTrue(userId);
    }

    // Add Medication Schedule
    public void addMedicationSchedule(MedicationScheduleDTO medicationScheduleDTO) {

        // Find User
        User user = userRepository.findById(medicationScheduleDTO.getUserId()).orElse(null);

        if (user == null) {
            throw new ApiException("User not found");
        }

        // Create Medication Schedule
        MedicationSchedule medicationSchedule = new MedicationSchedule();

        // Set User
        medicationSchedule.setUser(user);

        medicationSchedule.setMedicationName(medicationScheduleDTO.getMedicationName());

        medicationSchedule.setDosage(medicationScheduleDTO.getDosage()
        );

        medicationSchedule.setMealRelation(medicationScheduleDTO.getMealRelation());

        medicationSchedule.setTimes(medicationScheduleDTO.getTimes());

        medicationSchedule.setStartDate(medicationScheduleDTO.getStartDate());

        medicationSchedule.setEndDate(medicationScheduleDTO.getEndDate());

        medicationSchedule.setIsActive(medicationScheduleDTO.getIsActive());

        medicationScheduleRepository.save(medicationSchedule);
    }

    // Update Medication Schedule
    public void updateMedicationSchedule(Integer id, MedicationScheduleDTO medicationScheduleDTO) {

        MedicationSchedule oldMedicationSchedule = medicationScheduleRepository.findById(id).orElse(null);

        if (oldMedicationSchedule == null) {
            throw new ApiException("Medication Schedule not found");
        }

        // Find User
        User user = userRepository.findById(medicationScheduleDTO.getUserId()).orElse(null);

        if (user == null) {
            throw new ApiException("User not found");
        }

        // Set User
        oldMedicationSchedule.setUser(user);

        oldMedicationSchedule.setMedicationName(medicationScheduleDTO.getMedicationName());

        oldMedicationSchedule.setDosage(medicationScheduleDTO.getDosage());

        oldMedicationSchedule.setMealRelation(medicationScheduleDTO.getMealRelation());

        oldMedicationSchedule.setTimes(medicationScheduleDTO.getTimes());

        oldMedicationSchedule.setStartDate(medicationScheduleDTO.getStartDate());

        oldMedicationSchedule.setEndDate(medicationScheduleDTO.getEndDate());

        oldMedicationSchedule.setIsActive(medicationScheduleDTO.getIsActive());

        medicationScheduleRepository.save(oldMedicationSchedule);
    }

    // Delete Medication Schedule
    public void deleteMedicationSchedule(Integer id) {

        MedicationSchedule medicationSchedule = medicationScheduleRepository.findById(id).orElse(null);

        if (medicationSchedule == null) {
            throw new ApiException("Medication Schedule not found");
        }

        medicationScheduleRepository.delete(medicationSchedule);
    }
}