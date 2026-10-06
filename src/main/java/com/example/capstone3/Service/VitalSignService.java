package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.VitalSignDTO;
import com.example.capstone3.Model.User;
import com.example.capstone3.Model.VitalSign;
import com.example.capstone3.Repository.UserRepository;
import com.example.capstone3.Repository.VitalSignRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VitalSignService {

    private final VitalSignRepository vitalSignRepository;
    private final UserRepository userRepository;

    // Get All Vital Signs
    public List<VitalSign> getAllVitalSigns() {

        return vitalSignRepository.findAll();
    }

    // Get Vital Sign By ID
    public VitalSign getVitalSignById(Integer id) {

        VitalSign vitalSign = vitalSignRepository.findVitalSignById(id);

        if (vitalSign == null) {
            throw new ApiException("Vital sign not found");
        }

        return vitalSign;
    }

    // Get Vital Signs By User ID
    public List<VitalSign> getVitalSignsByUserId(Integer userId) {

        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new ApiException("User not found");
        }

        return vitalSignRepository.findAllByUser_IdOrderByMeasuredAtDesc(userId);
    }

    // Add Vital Sign
    public void addVitalSign(VitalSignDTO vitalSignDTO) {

        // Check User
        User user = userRepository.findUserById(
                vitalSignDTO.getUserId()
        );

        if (user == null) {
            throw new ApiException("User not found");
        }

        // Create Vital Sign
        VitalSign vitalSign = new VitalSign();

        vitalSign.setUser(user);
        vitalSign.setType(vitalSignDTO.getType());
        vitalSign.setSystolic(vitalSignDTO.getSystolic());
        vitalSign.setDiastolic(vitalSignDTO.getDiastolic());
        vitalSign.setContext(vitalSignDTO.getContext());
        vitalSign.setMeasuredAt(vitalSignDTO.getMeasuredAt());

        // Validate
        validateVitalSign(vitalSign);

        // Set Unit
        vitalSign.setUnit(getUnit(vitalSign.getType()));

        // Calculate Flag
        vitalSign.setFlag(calculateFlag(vitalSign));

        vitalSignRepository.save(vitalSign);
    }

    // Update Vital Sign
    public void updateVitalSign(Integer id, VitalSignDTO vitalSignDTO) {

        VitalSign oldVitalSign = vitalSignRepository.findVitalSignById(id);

        if (oldVitalSign == null) {
            throw new ApiException("Vital sign not found");
        }

        // Update Data
        oldVitalSign.setType(vitalSignDTO.getType());
        oldVitalSign.setSystolic(vitalSignDTO.getSystolic());
        oldVitalSign.setDiastolic(vitalSignDTO.getDiastolic());
        oldVitalSign.setContext(vitalSignDTO.getContext());
        oldVitalSign.setMeasuredAt(vitalSignDTO.getMeasuredAt());

        // Validate
        validateVitalSign(oldVitalSign);

        // Set Unit
        oldVitalSign.setUnit(getUnit(oldVitalSign.getType()));

        // Calculate Flag
        oldVitalSign.setFlag(calculateFlag(oldVitalSign));

        vitalSignRepository.save(oldVitalSign);
    }

    // Delete Vital Sign
    public void deleteVitalSign(Integer id) {

        VitalSign vitalSign = vitalSignRepository.findVitalSignById(id);

        if (vitalSign == null) {
            throw new ApiException("Vital sign not found");
        }

        vitalSignRepository.delete(vitalSign);
    }

    // Validate Vital Sign
    private void validateVitalSign(VitalSign vitalSign) {

        String type = vitalSign.getType();

        // Blood Pressure
        if (type.equals("BLOOD_PRESSURE")) {

            if (vitalSign.getDiastolic() == null) {
                throw new ApiException("Blood pressure requires diastolic value");
            }

            if (vitalSign.getDiastolic() >= vitalSign.getSystolic()) {

                throw new ApiException("Diastolic value must be lower than systolic value");
            }
        }

        // Other Types
        else {

            if (vitalSign.getDiastolic() != null) {
                throw new ApiException("Diastolic value is only allowed for blood pressure");
            }
        }

        // Glucose
        if (type.equals("GLUCOSE")
                && vitalSign.getContext() == null) {

            throw new ApiException("Glucose requires a context: FASTING, RANDOM or POST_MEAL");
        }

        // Context only for Glucose
        if (!type.equals("GLUCOSE")
                && vitalSign.getContext() != null) {

            throw new ApiException("Context is only allowed for glucose");
        }
    }

    // Get Unit
    private String getUnit(String type) {

        switch (type) {

            case "BLOOD_PRESSURE":
                return "mmHg";

            case "GLUCOSE":
                return "mg/dL";

            case "WEIGHT":
                return "kg";

            case "WAIST":
                return "cm";

            case "HEART_RATE":
                return "bpm";

            default:
                throw new ApiException("Invalid vital type");
        }
    }

    // Calculate Flag
    private String calculateFlag(VitalSign vitalSign) {

        String type = vitalSign.getType();

        double value = vitalSign.getSystolic();

        // Blood Pressure
        if (type.equals("BLOOD_PRESSURE")) {

            double diastolic = vitalSign.getDiastolic();

            if (value >= 180 || diastolic >= 120) {
                return "CRITICAL";
            }

            if (value >= 130 || diastolic >= 80) {
                return "HIGH";
            }

            if (value < 90 || diastolic < 60) {
                return "LOW";
            }

            return "NORMAL";
        }

        // Glucose
        if (type.equals("GLUCOSE")) {

            if (value < 54 || value >= 300) {
                return "CRITICAL";
            }

            if (value < 70) {
                return "LOW";
            }

            if (vitalSign.getContext() != null && vitalSign.getContext().equals("FASTING") && value > 130) {

                return "HIGH";
            }

            if (vitalSign.getContext() != null && !vitalSign.getContext().equals("FASTING") && value > 180) {

                return "HIGH";
            }

            return "NORMAL";
        }

        // Heart Rate
        if (type.equals("HEART_RATE")) {

            if (value < 60) {
                return "LOW";
            }

            if (value > 100) {
                return "HIGH";
            }

            return "NORMAL";
        }

        // Weight and Waist
        return "NORMAL";
    }
}