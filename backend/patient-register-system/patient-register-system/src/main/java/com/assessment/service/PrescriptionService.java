package com.assessment.service;

import com.assessment.common.payload.PaginationResponse;
import com.assessment.payload.request.PrescriptionFilterRequest;
import com.assessment.payload.request.PrescriptionRequest;
import com.assessment.payload.respnse.PrescriptionResponse;

public interface PrescriptionService {
    PaginationResponse<PrescriptionResponse> getPrescriptions(PrescriptionFilterRequest dto);

    void savePrescription(PrescriptionRequest request);

    void updatePrescription(Long id, PrescriptionRequest request);

    void deletePrescription(Long id);

    PrescriptionResponse getPrescriptionById(Long id);

}
