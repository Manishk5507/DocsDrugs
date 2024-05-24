package com.docs.drugs.Docs.Drugs.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docs.drugs.Docs.Drugs.Repository.PatientRepository;
import com.docs.drugs.Docs.Drugs.Service.PatientService;
import com.docs.drugs.Docs.Drugs.model.PatientModel;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientModel savePatient(PatientModel patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<PatientModel> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public PatientModel findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
}
