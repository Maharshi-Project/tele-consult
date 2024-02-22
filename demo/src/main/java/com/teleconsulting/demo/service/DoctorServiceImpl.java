package com.teleconsulting.demo.service;

import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getDoctors(){
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getSingleDoctor(Long id){
        Optional<Doctor> doc =  doctorRepository.findById(id);
        if(doc.isPresent()){
            return doc.get();
        }
        throw new RuntimeException("Doctor is not found for the id :"+id);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    @Override
    public  Doctor updateDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }
}
