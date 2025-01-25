package com.edoct.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctorants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctorant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cin;

    private String nom;
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.DOCTORANT;
}