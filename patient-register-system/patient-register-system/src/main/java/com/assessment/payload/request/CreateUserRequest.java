package com.assessment.payload.request;

public record CreateUserRequest(
        String userName,
        String password,
        String firstName,
        String lastName,
        Long role
) {
}
