package com.teleconsulting.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "patient_name")
    @NotNull
    private String patientname;
    @Column(name = "doc_name")
    @NotNull
    private String doctorname;
    @Column(name = "time")
    @NotNull
    private Time apttime;
    @Column(name = "apt_date")
    @NotNull
    private Date dptdate;

}
