package com.edoct.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "CIN is required")
    private String cin;

    @NotEmpty(message = "Password is required")
    private String password;
}