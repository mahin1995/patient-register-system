package com.assessment.common.payload;

import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String refreshToken;
    @Builder.Default
    private String type = "Bearer";
    private List<String> roles;
    private Map<String, Integer> userAccessPermissions;


}
