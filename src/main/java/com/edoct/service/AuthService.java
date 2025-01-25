package com.edoct.service;

import com.edoct.dto.AdminLoginRequest;
import com.edoct.dto.AdminLoginResponse;
import com.edoct.dto.LoginRequest;
import com.edoct.dto.LoginResponse;
import com.edoct.entity.Administrator;
import com.edoct.entity.Doctorant;
import com.edoct.repository.AdministratorRepository;
import com.edoct.repository.DoctorantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
public class AuthService {
    private final DoctorantRepository doctorantRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;

    @Autowired
    public AuthService(DoctorantRepository doctorantRepository, PasswordEncoder passwordEncoder, AdministratorRepository administratorRepository) {
        this.doctorantRepository = doctorantRepository;
        this.passwordEncoder = passwordEncoder;
        this.administratorRepository = administratorRepository;
    }

    public LoginResponse login(LoginRequest request) {
        log.info("Attempting login for CIN: {}", request.getCin());

        Doctorant doctorant = doctorantRepository.findByCin(request.getCin())
                .orElseThrow(() -> {
                    log.error("Doctorant not found with CIN: {}", request.getCin());
                    return new UsernameNotFoundException("Doctorant not found with CIN: " + request.getCin());
                });

        if (!passwordEncoder.matches(request.getPassword(), doctorant.getPassword())) {
            log.error("Invalid password for CIN: {}", request.getCin());
            throw new BadCredentialsException("Invalid password");
        }

        return LoginResponse.builder()
                .cin(doctorant.getCin())
                .nom(doctorant.getNom())
                .prenom(doctorant.getPrenom())
                .build();
    }

    public AdminLoginResponse loginAdministrator(AdminLoginRequest request) {
        Administrator administrator = administratorRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Administrator not found"));

        if (!passwordEncoder.matches(request.getPassword(), administrator.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return AdminLoginResponse.builder()
                .email(administrator.getEmail())
                .role(administrator.getRole())
                .build();
    }
}