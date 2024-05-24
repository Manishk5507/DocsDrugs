package com.docs.drugs.Docs.Drugs.Controllers;

import java.util.List;
import java.util.Map;

// import javax.naming.AuthenticationException;

// import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.docs.drugs.Docs.Drugs.Security.JwtUtil;
import com.docs.drugs.Docs.Drugs.Service.PatientService;
import com.docs.drugs.Docs.Drugs.model.PatientModel;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerPatient(@RequestParam("fullName") String fullName,
            @RequestParam("gender") String gender,
            @RequestParam("age") String age,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        PatientModel existingPatient = patientService.findByEmail(email);
        if (existingPatient != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Patient already exist!!"));
        }

        PatientModel patient = new PatientModel();

        patient.setFullName(fullName);
        patient.setGender(gender);
        patient.setAge(age);
        patient.setEmail(email);
        patient.setPassword(bCryptPasswordEncoder.encode(password));

        patientService.savePatient(patient);

        String token = jwtUtil.generateToken(patient.getEmail());

        return ResponseEntity.ok().body(Map.of("token", token, "username", fullName));
    }

    @GetMapping("/all")
    public List<PatientModel> getAllPatients() {
        return patientService.getAllPatients();
    }

    // @PostMapping("/login")
    // public String login(@RequestParam("email") String email,
    // @RequestParam("password") String password) throws AuthenticationException {
    // org.springframework.security.core.Authentication authentication =
    // authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(email, password));
    // if (authentication.isAuthenticated()) {
    // return jwtUtil.generateToken(email);
    // } else {
    // throw new RuntimeException("Invalid credentials");
    // }
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
