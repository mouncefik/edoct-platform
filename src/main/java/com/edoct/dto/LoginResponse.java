package com.edoct.dto;

import com.edoct.entity.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String cin;
    private String nom;
    private String prenom;
}
