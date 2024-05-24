package com.docs.drugs.Docs.Drugs.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.docs.drugs.Docs.Drugs.Repository.DoctorRepository;
import com.docs.drugs.Docs.Drugs.model.DoctorModel;

import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DoctorModel doctor = doctorRepository.findByEmail(email);
        if (doctor == null) {
            throw new UsernameNotFoundException("Doctor not found");
        }
        return new org.springframework.security.core.userdetails.User(doctor.getEmail(), doctor.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_DOCTOR")));
    }
}
