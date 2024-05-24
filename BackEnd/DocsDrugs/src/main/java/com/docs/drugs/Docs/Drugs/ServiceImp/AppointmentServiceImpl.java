package com.docs.drugs.Docs.Drugs.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docs.drugs.Docs.Drugs.Repository.AppointmentRepository;
import com.docs.drugs.Docs.Drugs.Service.AppointmentService;
import com.docs.drugs.Docs.Drugs.model.AppointmentModel;

@Service
public class AppointmentServiceImpl implements AppointmentService {


    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public String saveAppointment(AppointmentModel appointment) {
        appointmentRepository.save(appointment);
        return "Appointment Booked Successfully";
    }
}
