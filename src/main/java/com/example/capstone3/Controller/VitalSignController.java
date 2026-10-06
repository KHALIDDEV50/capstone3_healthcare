
package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.VitalSignDTO;
import com.example.capstone3.Model.VitalSign;
import com.example.capstone3.Service.VitalSignService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vital-sign")
@AllArgsConstructor
public class VitalSignController {

    private final VitalSignService vitalSignService;

    // Get All Vital Signs
    @GetMapping("/get")
    public ResponseEntity<?> getAllVitalSigns() {

        List<VitalSign> vitalSigns = vitalSignService.getAllVitalSigns();

        return ResponseEntity.status(200).body(vitalSigns);
    }

    // Get Vital Sign By ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getVitalSignById(
            @PathVariable Integer id) {

        VitalSign vitalSign = vitalSignService.getVitalSignById(id);

        return ResponseEntity.status(200).body(vitalSign);
    }

    // Get Vital Signs By User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVitalSignsByUserId(@PathVariable Integer userId) {

        List<VitalSign> vitalSigns = vitalSignService.getVitalSignsByUserId(userId);

        return ResponseEntity.status(200).body(vitalSigns);
    }

    // Add Vital Sign
    @PostMapping("/add")
    public ResponseEntity<?> addVitalSign(@RequestBody @Valid VitalSignDTO vitalSignDTO) {

        vitalSignService.addVitalSign(vitalSignDTO);

        return ResponseEntity.status(200).body(new ApiResponse("Vital Sign Add Successful"));
    }

    // Update Vital Sign
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVitalSign(@PathVariable Integer id, @RequestBody @Valid VitalSignDTO vitalSignDTO) {

        vitalSignService.updateVitalSign(id, vitalSignDTO);

        return ResponseEntity.status(200).body(new ApiResponse("Vital Sign Update Successful"));
    }

    // Delete Vital Sign
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVitalSign(@PathVariable Integer id) {

        vitalSignService.deleteVitalSign(id);

        return ResponseEntity.status(200).body(new ApiResponse("Vital Sign Delete Successful"));
    }
}
