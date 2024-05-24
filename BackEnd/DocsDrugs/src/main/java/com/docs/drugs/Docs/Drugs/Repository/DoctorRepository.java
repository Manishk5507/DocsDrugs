package com.docs.drugs.Docs.Drugs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docs.drugs.Docs.Drugs.model.DoctorModel;

public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {
    DoctorModel findByEmail(String email);
}
