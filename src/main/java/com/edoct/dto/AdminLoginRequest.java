package com.edoct.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminLoginRequest {
    private String email;
    private String password;
}
