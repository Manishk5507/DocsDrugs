package com.docs.drugs.Docs.Drugs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@AllArgsConstructor
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Email should not be Null")
	@Column(unique= true, nullable=false)
    private String email;
    private String password;
    private String patientName;
    private String patientNumber;
    private String patientGender;
    private String appointmentTime;
    private String preferredMode;

    public AppointmentModel() {}

    public AppointmentModel(String email, String password, String patientName, String patientNumber, String patientGender, String appointmentTime, String preferredMode) {
        this.email = email;
        this.password = password;
        this.patientName = patientName;
        this.patientNumber = patientNumber;
        this.patientGender = patientGender;
        this.appointmentTime = appointmentTime;
        this.preferredMode = preferredMode;
    }

    
}