package com.example.capstone3.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Full Name
    @Column(nullable = false, length = 100)
    private String fullName;

    // Email
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    // Phone
    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    // Password
    @Column(nullable = false)
    private String password;

    // Date of Birth
    private LocalDate dateOfBirth;

    // Gender
    @Column(nullable = false, length = 10)
    private String gender;

    // WhatsApp Opt In
    @Column(nullable = false)
    private Boolean whatsappOptIn;

    // Created At
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}