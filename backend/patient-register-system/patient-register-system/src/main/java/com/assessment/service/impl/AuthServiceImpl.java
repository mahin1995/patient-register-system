package com.assessment.service.impl;

import com.assessment.common.exception.AppException;
import com.assessment.common.security.JwtUtils;
import com.assessment.common.security.service.UserDetailsImpl;
import com.assessment.entity.User;
import com.assessment.payload.request.CreateUserRequest;
import com.assessment.payload.request.LoginRequest;
import com.assessment.common.payload.JwtResponse;
import com.assessment.repository.RoleRepository;
import com.assessment.repository.UserRepository;
import com.assessment.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.assessment.common.utils.UlidService.getUlid;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Override
    public void createUser(CreateUserRequest dto) {

        if (userRepository.existsByUserName(dto.userName())) {
            throw new AppException("User already exist", HttpStatus.REQUEST_TIMEOUT.value());
        }

        User user = new User();
        user.setUserName(dto.userName());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setPassword(passwordEncoder.encode(dto.password()));

        var userRole = roleRepository.findById(dto.role())
                .orElseThrow(() -> new AppException("Role not found", HttpStatus.NOT_FOUND.value()));
        user.setRole(userRole);

        userRepository.save(user);
    }

    @Override
    public JwtResponse login(LoginRequest dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = jwtUtils.generateToken(userDetails.getUsername(), roles);
        String refreshToken = generateRefreshToken(userDetails.getUsername());
        return JwtResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .roles(roles)
                .build();
    }
    @Transactional
    public String generateRefreshToken(String username) {
        String ulid = getUlid();
         userRepository.findByUserName(username).ifPresent(
                user -> {
                    user.setRefreshToken(ulid);
                    userRepository.save(user);
                }
        );
        return ulid;
    }
}
