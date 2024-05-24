package com.docs.drugs.Docs.Drugs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docs.drugs.Docs.Drugs.model.AppointmentModel;

public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {

    
}
