
package com.example.capstone3.Repository;

import com.example.capstone3.Model.MedicationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationScheduleRepository
        extends JpaRepository<MedicationSchedule, Long> {

    List<MedicationSchedule> findByUserId(Long userId);

    List<MedicationSchedule> findByUserIdAndIsActiveTrue(Long userId);

}

