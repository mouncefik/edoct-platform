package com.edoct.service;

import com.edoct.entity.Doctorant;
import com.edoct.exception.DoctorantNotFoundException;
import com.edoct.exception.DuplicateDoctorantException;
import com.edoct.repository.DoctorantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DoctorantServiceImpl implements DoctorantService {

    private final DoctorantRepository doctorantRepository;

    @Override
    public Doctorant findByCin(String cin) throws DoctorantNotFoundException {
        return doctorantRepository.findByCin(cin)
                .orElseThrow(() -> new DoctorantNotFoundException("Doctorant not found with CIN: " + cin));
    }

    @Override
    public List<Doctorant> findAll() {
        return doctorantRepository.findAll();
    }

    @Override
    public Doctorant save(Doctorant doctorant) {
        if (doctorantRepository.existsByCin(doctorant.getCin())) {
            throw new DuplicateDoctorantException("Doctorant already exists with CIN: " + doctorant.getCin());
        }
        return doctorantRepository.save(doctorant);
    }

    @Override
    public void deleteByCin(String cin) {
        Doctorant doctorant = findByCin(cin);
        doctorantRepository.delete(doctorant);
    }
}