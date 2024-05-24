package com.docs.drugs.Docs.Drugs.Service;

import java.util.List;

import com.docs.drugs.Docs.Drugs.model.PatientModel;

public interface PatientService {
    PatientModel savePatient(PatientModel patient);
    List<PatientModel> getAllPatients();
    PatientModel findByEmail(String email);
}
