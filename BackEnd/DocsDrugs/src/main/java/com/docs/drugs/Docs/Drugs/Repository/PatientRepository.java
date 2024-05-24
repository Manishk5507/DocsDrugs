package com.docs.drugs.Docs.Drugs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docs.drugs.Docs.Drugs.model.PatientModel;

public interface PatientRepository extends JpaRepository<PatientModel, Long> {

    PatientModel findByEmail(String email);
    
}
