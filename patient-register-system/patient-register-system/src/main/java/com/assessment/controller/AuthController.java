package com.assessment.controller;


import com.assessment.payload.request.CreateUserRequest;
import com.assessment.payload.request.LoginRequest;
import com.assessment.common.payload.JwtResponse;
import com.assessment.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user registration and login")
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account using the provided information",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request or user already exists",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{\"error\": \"Username already taken\"}"))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid  CreateUserRequest signUpRequest) {
        authService.createUser(signUpRequest);
        return ResponseEntity.ok().body("User Create Successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) {
        JwtResponse login = authService.login(request);
        return ResponseEntity.ok(login);
    }
}
