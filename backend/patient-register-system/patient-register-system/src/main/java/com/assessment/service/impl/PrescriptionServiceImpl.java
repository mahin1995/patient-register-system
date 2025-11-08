package com.assessment.service.impl;

import com.assessment.common.exception.AppException;
import com.assessment.common.payload.PaginationResponse;
import com.assessment.entity.Prescription;
import com.assessment.mapper.PrescriptionMapper;
import com.assessment.payload.request.PrescriptionFilterRequest;
import com.assessment.payload.request.PrescriptionRequest;
import com.assessment.payload.respnse.PrescriptionResponse;
import com.assessment.repository.PrescriptionRepository;
import com.assessment.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;


    @Override
    public PaginationResponse<PrescriptionResponse> getPrescriptions(PrescriptionFilterRequest dto) {
        Page<Prescription> result =
                prescriptionRepository.findByPrescriptionDateBetween(dto.fromDate(), dto.toDate(), dto.paginationRequest().toPageRequest());

        return PaginationResponse.
                <PrescriptionResponse>builder()
                .content(result.stream().map(prescription -> PrescriptionMapper.toResponse(prescription)).toList())
                .pageNumber(result.getNumber())
                .totalPages(result.getTotalPages())
                .pageSize(result.getSize())
                .totalElements(result.getTotalElements())
                .build();

    }

    @Override
    public void savePrescription(PrescriptionRequest request) {
        Prescription entity = PrescriptionMapper.toEntity(request);
        prescriptionRepository.save(entity);
    }

    @Override
    public void updatePrescription(Long id, PrescriptionRequest request) {
        var prescription = prescriptionRepository.findById(id).orElseThrow(() -> new AppException("Prescription Not Found", HttpStatus.NOT_FOUND.value()));
        PrescriptionMapper.updateEntity(prescription, request);
        prescriptionRepository.save(prescription);
    }

    @Override
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }

    @Override
    public PrescriptionResponse getPrescriptionById(Long id) {
        var result = prescriptionRepository.findById(id).orElseThrow(() -> new AppException("Prescription Not Found", HttpStatus.NOT_FOUND.value()));
        return PrescriptionMapper.toResponse(result);
    }
}
