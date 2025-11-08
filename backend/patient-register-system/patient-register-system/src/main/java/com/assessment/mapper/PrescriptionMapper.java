package com.assessment.mapper;

import com.assessment.entity.Prescription;
import com.assessment.payload.request.PrescriptionRequest;
import com.assessment.payload.respnse.PrescriptionResponse;

public class PrescriptionMapper {
    public static Prescription toEntity(PrescriptionRequest dto) {
        Prescription prescription = new Prescription();
        prescription.setPrescriptionDate(dto.prescriptionDate());
        prescription.setPatientName(dto.patientName());
        prescription.setPatientAge(dto.patientAge());
        prescription.setPatientGender(dto.patientGender());
        prescription.setDiagnosis(dto.diagnosis());
        prescription.setMedicines(dto.medicines());
        prescription.setNextVisitDate(dto.nextVisitDate());
        return prescription;
    }

    // Update existing entity from DTO
    public static void updateEntity(Prescription prescription, PrescriptionRequest dto) {
        prescription.setPrescriptionDate(dto.prescriptionDate());
        prescription.setPatientName(dto.patientName());
        prescription.setPatientAge(dto.patientAge());
        prescription.setPatientGender(dto.patientGender());
        prescription.setDiagnosis(dto.diagnosis());
        prescription.setMedicines(dto.medicines());
        prescription.setNextVisitDate(dto.nextVisitDate());

    }
    public static PrescriptionResponse toResponse(Prescription prescription) {
        return new PrescriptionResponse(
                prescription.getId(),
                prescription.getPrescriptionDate(),
                prescription.getPatientName(),
                prescription.getPatientAge(),
                prescription.getPatientGender(),
                prescription.getDiagnosis(),
                prescription.getMedicines(),
                prescription.getNextVisitDate(),
                prescription.getPhoneNumber(),
                prescription.getDoctorId()
        );
    }
}
