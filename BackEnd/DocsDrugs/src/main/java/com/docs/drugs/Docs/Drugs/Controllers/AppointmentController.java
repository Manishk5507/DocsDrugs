package com.docs.drugs.Docs.Drugs.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.docs.drugs.Docs.Drugs.Service.AppointmentService;
import com.docs.drugs.Docs.Drugs.model.AppointmentModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

// @RestController
// @RequestMapping("/api/appointments")
// public class AppointmentController {

//     @Autowired
//     private AppointmentService appointmentService;

//     @PostMapping("/create")
//     public String createAppointment(@RequestParam Map<String, String> formData) {
//         String email = formData.get("email");
//         String password = formData.get("password");
//         String patientName = formData.get("patientName");
//         String patientNumber = formData.get("patientNumber");
//         String patientGender = formData.get("patientGender");
//         String appointmentTime = formData.get("appointmentTime");
//         String preferredMode = formData.get("preferredMode");

//         AppointmentModel appointment = new AppointmentModel(email, password, patientName, patientNumber, patientGender, appointmentTime, preferredMode);
//         return appointmentService.saveAppointment(appointment);
//     }
// }


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<String> createAppointment(@RequestParam Map<String, String> formData,
                                                    @RequestHeader("Authorization") String token) {
        try {
            // Decode the token
            String tokenWithoutBearer = token.replace("Bearer ", "");
            Claims claims = Jwts.parser()
                                .setSigningKey("your_secret_key") // Use your secret key here
                                .parseClaimsJws(tokenWithoutBearer)
                                .getBody();

            String email = claims.get("email", String.class);
            String password = claims.get("password", String.class);

            String patientName = formData.get("patientName");
            String patientNumber = formData.get("patientNumber");
            String patientGender = formData.get("patientGender");
            String appointmentTime = formData.get("appointmentTime");
            String preferredMode = formData.get("preferredMode");

            AppointmentModel appointment = new AppointmentModel(email, password, patientName, patientNumber, patientGender, appointmentTime, preferredMode);
            appointmentService.saveAppointment(appointment);

            return ResponseEntity.status(HttpStatus.CREATED).body("Appointment Scheduled Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error scheduling appointment: " + e.getMessage());
        }
    }
}