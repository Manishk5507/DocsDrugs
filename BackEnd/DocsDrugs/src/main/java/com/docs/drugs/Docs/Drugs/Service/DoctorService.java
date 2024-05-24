package com.docs.drugs.Docs.Drugs.Service;

import java.util.List;

import com.docs.drugs.Docs.Drugs.model.DoctorModel;

public interface DoctorService {
    DoctorModel saveDoctor(DoctorModel doctor);
    List<DoctorModel> getAllDoctors();
    DoctorModel findByEmail(String email);
}
