package com.assessment.payload.respnse;

import java.time.LocalDate;

public record PrescriptionResponse(
        Long id,
        LocalDate prescriptionDate,
        String patientName,
        Integer patientAge,
        String patientGender,
        String diagnosis,
        String medicines,
        LocalDate nextVisitDate,
        String phoneNumber,
        Long doctorId
) {}

