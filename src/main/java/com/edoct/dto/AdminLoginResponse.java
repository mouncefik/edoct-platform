package com.edoct.dto;

import com.edoct.entity.Role;
import lombok.*;

@Data
@Builder
public class AdminLoginResponse {
    private String token;
    private String email;
    private Role role;
}