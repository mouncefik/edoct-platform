package com.edoct.service;

import com.edoct.entity.Doctorant;
import com.edoct.exception.DoctorantNotFoundException;
import com.edoct.repository.DoctorantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DoctorantService {
    Doctorant findByCin(String cin) throws DoctorantNotFoundException;
    List<Doctorant> findAll();
    Doctorant save(Doctorant doctorant);
    void deleteByCin(String cin);
}
