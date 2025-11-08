package com.assessment.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "UserName Must Be given")
        @NotEmpty(message = "UserName Must Be given")
        String username,
        @NotNull(message = "Password Must Be given")
        @NotEmpty(message = "Password Must Be given")
        String password
) {
}
