package com.edoct.repository;

import com.edoct.entity.Doctorant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorantRepository extends JpaRepository<Doctorant, Long> {
    Optional<Doctorant> findByCin(String cin);
    boolean existsByCin(String cin);
}