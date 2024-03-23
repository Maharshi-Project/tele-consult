package com.teleconsulting.demo.service;

import com.teleconsulting.demo.model.AuthenticationResponse;
import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.repository.DoctorRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(DoctorRepository doctorRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Doctor request){
        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setGender(request.getGender());
        doctor.setPhoneNumber(request.getPhoneNumber());
        doctor.setPassword((passwordEncoder.encode(request.getPassword())));
        doctor.setEmail(request.getEmail());
        doctor.setRole(request.getRole());
        doctor = doctorRepository.save(doctor);

        String token = jwtService.generateToken(doctor);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(Doctor request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Doctor doctor = doctorRepository.findByEmail(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(doctor);

        return new AuthenticationResponse(token);
    }

}
