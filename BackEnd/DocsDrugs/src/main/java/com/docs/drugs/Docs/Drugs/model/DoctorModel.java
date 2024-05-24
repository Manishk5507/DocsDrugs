package com.docs.drugs.Docs.Drugs.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;
    private String gender;

    @NotBlank(message="Email should not be Null")
	@Column(unique= true, nullable=false,updatable = false)
    private String email;
    private String password;
    private String medicalDegree;
    private String specialization;
    private String hospital;
    private String registrationNumber;
}