package com.teleconsulting.demo.repository;

import com.teleconsulting.demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByPhoneNumber(String phoneNumber);
    Optional<Doctor> findById(Long id);

}
