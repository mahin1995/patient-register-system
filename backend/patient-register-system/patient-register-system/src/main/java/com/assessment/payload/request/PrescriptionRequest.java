package com.assessment.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrescriptionRequest(
        @NotNull LocalDate prescriptionDate,
        @NotBlank String patientName,
        @NotNull @Min(0) @Max(150) Integer patientAge,
        @NotBlank String patientGender,
        String diagnosis,
        String medicines,
        LocalDate nextVisitDate
) {}
