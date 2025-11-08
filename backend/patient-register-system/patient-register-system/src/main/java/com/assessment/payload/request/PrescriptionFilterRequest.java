package com.assessment.payload.request;

import com.assessment.common.payload.PaginationRequest;

import java.time.LocalDate;

public record PrescriptionFilterRequest(
        PaginationRequest paginationRequest,
        LocalDate fromDate,
        LocalDate toDate
) {
}
