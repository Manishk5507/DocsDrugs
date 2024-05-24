package com.docs.drugs.Docs.Drugs.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.docs.drugs.Docs.Drugs.Security.JwtUtil;
import com.docs.drugs.Docs.Drugs.Service.DoctorService;
import com.docs.drugs.Docs.Drugs.model.DoctorModel;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
// @CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerDoctor(@RequestParam("fullName") String fullName,
            @RequestParam("gender") String gender,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("medicalDegree") String medicalDegree,
            @RequestParam("specialization") String specialization,
            @RequestParam("hospital") String hospital,
            @RequestParam("registrationNumber") String registrationNumber) {


                DoctorModel existingDoctor = doctorService.findByEmail(email);
        if (existingDoctor != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Doctor already exist!!"));
        }

        DoctorModel doctor = new DoctorModel();
        doctor.setFullName(fullName);
        doctor.setGender(gender);
        doctor.setEmail(email);
        doctor.setPassword(bCryptPasswordEncoder.encode(password));
        doctor.setMedicalDegree(medicalDegree);
        doctor.setSpecialization(specialization);
        doctor.setHospital(hospital);
        doctor.setRegistrationNumber(registrationNumber);

        doctorService.saveDoctor(doctor); 

        String token = jwtUtil.generateToken(doctor.getEmail());

       return ResponseEntity.ok().body(Map.of("token", token, "username", fullName));

    }

    @GetMapping("/all")
    public List<DoctorModel> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // @GetMapping("/profile")
    // public ModelAndView getDoctorProfile() {
    //     // Redirect to Google
    //     return new ModelAndView("redirect:http://www.google.com");
    // }

    // @PostMapping("/login")
    // public boolean isValidDoctor(@RequestParam("email") String email,
    // @RequestParam("password") String password) {
    // DoctorModel doctor=doctorService.findByEmail(email);
    // if(doctor!=null && doctor.getPassword().equals(password)){
    // return true;
    // }
    // return false;

    // }

    // @PostMapping("/login")
    // public String login(@RequestParam("email") String email,
    //         @RequestParam("password") String password) throws AuthenticationException {
    //     org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(email, password));
    //     if (authentication.isAuthenticated()) {
    //         return jwtUtil.generateToken(email);
    //     } else {
    //         throw new RuntimeException("Invalid credentials");
    //     }
    // }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam("email") String email,
                                                     @RequestParam("password") String password) {
        // Authenticate user
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        // If authentication is successful, generate token and send it in response
        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(email);
            return ResponseEntity.ok().body(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }

}
