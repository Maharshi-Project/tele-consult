package com.teleconsulting.demo.service;

import com.teleconsulting.demo.repository.DoctorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private  final DoctorRepository doctorRepository;

    public UserDetailsServiceImp(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doctorRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found!!"));
    }
}
