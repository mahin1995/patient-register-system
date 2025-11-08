package com.assessment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(
        name = "prescriptions"
)
@Getter
@Setter
public class Prescription extends BaseEntity<Long>{

    @Column(nullable = false)
    private LocalDate prescriptionDate;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private Integer patientAge;

    @Column(nullable = false)
    private String patientGender; // "Male", "Female", etc.

    @Column(length = 500)
    private String diagnosis;

    @Column(length = 1000)
    private String medicines;

    private LocalDate nextVisitDate;

    @Column
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private Doctor doctor;

    @Column(name = "doctor_id")
    private Long doctorId;



}
