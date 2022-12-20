package com.solvd.laba;

import com.solvd.laba.person.doctors.Doctor;
import com.solvd.laba.person.Patient;

import java.time.LocalDate;

public class Appointment {
    private LocalDate date;
    private Doctor doctor;
    private Patient patient;

    public Appointment(LocalDate date, Doctor doctor, Patient patient) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}
