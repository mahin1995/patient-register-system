package com.assessment.common.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@Getter
@Setter
public class PaginationRequest {

    @Min(value = 1, message = "Page number must be greater than 0")
    private int page = 1;

    @Min(value = 1, message = "Page size must be greater than 0")
    private int size = 10;

    @JsonIgnore
    public PageRequest toPageRequest() {
        // Spring Data uses 0-based page index
        return PageRequest.of(page - 1, size);
    }
}

