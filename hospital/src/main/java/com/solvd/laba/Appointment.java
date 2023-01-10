package com.solvd.laba;

import com.solvd.laba.person.Patient;
import com.solvd.laba.person.doctors.Doctor;

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

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public String toString() {
        return "\nAppointment: \n" +
                "date=" + date +"\n" +
                "doctor=" + doctor.getName() +"\n" +
                "patient=" + patient.getName();
    }
}
