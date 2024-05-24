package com.docs.drugs.Docs.Drugs.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docs.drugs.Docs.Drugs.Repository.DoctorRepository;
import com.docs.drugs.Docs.Drugs.Service.DoctorService;
import com.docs.drugs.Docs.Drugs.model.DoctorModel;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public DoctorModel saveDoctor(DoctorModel doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorModel> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public DoctorModel findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }
}
