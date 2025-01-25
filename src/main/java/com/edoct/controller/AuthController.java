package com.edoct.controller;

import com.edoct.dto.*;
import com.edoct.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            LoginResponse errorResponse = LoginResponse.builder()
                    .token(null)
                    .cin(null)
                    .nom("Invalid username or password")
                    .prenom(null)
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }


    @PostMapping("/admin/login")
    public ResponseEntity<Map<String, Object>> administratorLogin(@RequestBody AdminLoginRequest request) {
        try {
            AdminLoginResponse response = authService.loginAdministrator(request);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("email", response.getEmail());
            successResponse.put("role", response.getRole());
            successResponse.put("token", response.getToken());

            HttpHeaders headers = createJsonHeaders();
            return new ResponseEntity<>(successResponse, headers, HttpStatus.OK);

        } catch (BadCredentialsException | UsernameNotFoundException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid email or password");

            HttpHeaders headers = createJsonHeaders();
            return new ResponseEntity<>(errorResponse, headers, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Something went wrong. Please try again later.");

            HttpHeaders headers = createJsonHeaders();
            return new ResponseEntity<>(errorResponse, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private HttpHeaders createJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
