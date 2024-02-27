package com.teleconsulting.demo.service;

import com.teleconsulting.demo.handler.NotFoundException;
import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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
        throw new NotFoundException("Doctor is not found for the id :"+id);
    }

    @Override
    public void deleteDoctor(Long id) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(id);
        if (existingDoctor.isEmpty()) {
            throw new NotFoundException("Doctor doesn't exists !!\n");
        }
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findByEmail(doctor.getEmail());
        if (existingDoctor.isPresent()) {
            throw new NotFoundException("Email already exists: " + doctor.getEmail());
        }
        return doctorRepository.save(doctor);
    }
    @Override
    public  Doctor updateDoctor(Doctor doctor){
        Optional<Doctor> existingDoctor = doctorRepository.findById(doctor.getId());
        if (existingDoctor.isPresent()) {
            return doctorRepository.save(doctor);
        }
        else {
            throw new NotFoundException("Doctor doesn't exists !!\n");
        }

    }
}
