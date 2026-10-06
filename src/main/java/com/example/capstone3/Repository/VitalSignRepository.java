package com.example.capstone3.Repository;

import com.example.capstone3.Model.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalSignRepository
        extends JpaRepository<VitalSign, Integer> {

    // Get Vital Sign By ID
    VitalSign findVitalSignById(Integer id);

    // Get Vital Signs By User ID
    List<VitalSign> findAllByUser_IdOrderByMeasuredAtDesc(Integer userId);
}