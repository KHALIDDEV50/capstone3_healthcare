package com.example.capstone3.Repository;


import com.example.capstone3.Model.HealthProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthProfileRepository extends JpaRepository<HealthProfile, Integer> {

    HealthProfile findHealthProfileById(Integer id);

    HealthProfile findHealthProfileByUserId(Integer userId);

    boolean existsByUserId(Integer userId);
}