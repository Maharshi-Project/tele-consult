package com.teleconsulting.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private Long id;

    @Column(name = "patient_name")
    @NotNull
    private String name;

    @Column(name = "patient_gender")
    @NotNull
    private String gender;

    @Column(name = "patient_phoneNumber", unique = true)
    @NotNull
    private String phoneNumber;
}
