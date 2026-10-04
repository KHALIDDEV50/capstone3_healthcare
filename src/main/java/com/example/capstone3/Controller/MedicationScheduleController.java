
package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.MedicationScheduleRequestDTO;
import com.example.capstone3.Model.MedicationSchedule;
import com.example.capstone3.Service.MedicationScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medication-schedule")
@RequiredArgsConstructor
public class MedicationScheduleController {

    private final MedicationScheduleService medicationScheduleService;


    // Get All Medication Schedule
    @GetMapping("/get")
    public ResponseEntity<?> getAllMedicationSchedule() {

        List<MedicationSchedule> medicationSchedules = medicationScheduleService.getAllMedicationSchedule();

        return ResponseEntity.status(200).body(medicationSchedules);
    }


    // Get Medication Schedule By ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMedicationScheduleById(@PathVariable Long id) {

        MedicationSchedule medicationSchedule = medicationScheduleService.getMedicationScheduleById(id);

        return ResponseEntity.status(200).body(medicationSchedule);
    }


    // Get Medication Schedule By User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getMedicationScheduleByUserId(@PathVariable Long userId) {

        List<MedicationSchedule> medicationSchedules = medicationScheduleService.getMedicationScheduleByUserId(userId);

        return ResponseEntity.status(200).body(medicationSchedules);
    }


    // Get Active Medication Schedule By User ID
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<?> getActiveMedicationScheduleByUserId(@PathVariable Long userId) {

        List<MedicationSchedule> medicationSchedules = medicationScheduleService.getActiveMedicationScheduleByUserId(userId);

        return ResponseEntity.status(200).body(medicationSchedules);
    }


    // Add Medication Schedule
    @PostMapping("/add")
    public ResponseEntity<?> addMedicationSchedule(@RequestBody @Valid MedicationScheduleRequestDTO medicationScheduleRequestDTO) {

        medicationScheduleService.addMedicationSchedule(medicationScheduleRequestDTO);

        return ResponseEntity.status(200).body(new ApiResponse("Medication Schedule Add Successful"));
    }


    // Update Medication Schedule
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMedicationSchedule(@PathVariable Long id, @RequestBody @Valid MedicationScheduleRequestDTO medicationScheduleRequestDTO) {

        medicationScheduleService.updateMedicationSchedule(id, medicationScheduleRequestDTO);

        return ResponseEntity.status(200).body(new ApiResponse("Medication Schedule Update Successful"));
    }


    // Delete Medication Schedule
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedicationSchedule(@PathVariable Long id) {

        medicationScheduleService.deleteMedicationSchedule(id);

        return ResponseEntity.status(200).body(new ApiResponse("Medication Schedule Delete Successful"));
    }
}
