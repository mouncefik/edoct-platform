package com.edoct.service;

import com.edoct.entity.Administrator;

public interface AdministratorService {
    Administrator findByEmail(String email);
    Administrator createAdmin(String email, String nom, String prenom, String password);
}