package com.assessment.controller;


import com.assessment.common.payload.PaginationResponse;
import com.assessment.payload.request.PrescriptionFilterRequest;
import com.assessment.payload.request.PrescriptionRequest;
import com.assessment.payload.respnse.PrescriptionResponse;
import com.assessment.service.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Prescription Management", description = "APIs for managing prescriptions")
@RestController
@RequestMapping("api/v1/prescriptions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @Operation(
            summary = "Get prescriptions by date range",
            description = "Retrieve a paginated list of prescriptions filtered by date range (defaults to current month)."
    )
    @ApiResponse(responseCode = "200", description = "List of prescriptions returned successfully")
    @PostMapping("/list")
    public ResponseEntity<PaginationResponse<PrescriptionResponse>> getPrescriptions(
            @RequestBody @Valid PrescriptionFilterRequest request
    ) {
        return ResponseEntity.ok(prescriptionService.getPrescriptions(request));
    }

    @Operation(
            summary = "Create new prescription",
            description = "Add a new prescription entry to the system."
    )
    @ApiResponse(responseCode = "201", description = "Prescription created successfully")
    @PostMapping("/create")
    public ResponseEntity<String> createPrescription(@RequestBody PrescriptionRequest request) {
        prescriptionService.savePrescription(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Prescription created successfully");
    }

    @Operation(
            summary = "Update existing prescription",
            description = "Update details of an existing prescription by ID."
    )
    @ApiResponse(responseCode = "200", description = "Prescription updated successfully")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePrescription(
            @PathVariable Long id,
            @RequestBody PrescriptionRequest request
    ) {
        prescriptionService.updatePrescription(id, request);
        return ResponseEntity.ok("Prescription updated successfully");
    }

    @Operation(
            summary = "Delete a prescription",
            description = "Delete a prescription by ID (confirmation required in UI)."
    )
    @ApiResponse(responseCode = "204", description = "Prescription deleted successfully")
    @PatchMapping("/delete/{id}")
    public ResponseEntity<Void> deletePrescription(
            @Parameter(description = "Prescription ID", example = "1")
            @PathVariable Long id
    ) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get prescription by ID",
            description = "Retrieve a single prescription's details by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Prescription found",
            content = @Content(schema = @Schema(implementation = PrescriptionResponse.class))
    )
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<PrescriptionResponse> getPrescriptionById(
            @Parameter(description = "Prescription ID", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }
}