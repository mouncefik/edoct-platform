package com.edoct.service;

import com.edoct.entity.Administrator;
import com.edoct.entity.Role;
import com.edoct.exception.AdministratorNotFoundException;
import com.edoct.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Administrator findByEmail(String email) {
        return administratorRepository.findByEmail(email)
                .orElseThrow(() -> new AdministratorNotFoundException("Administrator not found with Email: " + email));
    }

    public Administrator createAdmin(String email, String nom, String prenom, String password) {
        Administrator admin = Administrator.builder()
                .email(email)
                .nom(nom)
                .prenom(prenom)
                .password(passwordEncoder.encode(password))
                .role(Role.ADMIN)
                .build();
        return administratorRepository.save(admin);
    }
}