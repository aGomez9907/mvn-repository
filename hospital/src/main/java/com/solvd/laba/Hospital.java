package com.solvd.laba;

import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.exceptions.PersonNotFoundException;
import com.solvd.laba.exceptions.WrongSpecialtyException;
import com.solvd.laba.person.doctors.Doctor;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;
import com.solvd.laba.room.rooms.HospitalRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class Hospital {

    private static final Logger LOGGER = LogManager.getLogger();
    private ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
    private ArrayList<Doctor> doctorArraylist = new ArrayList<>();

    private ArrayList<Patient> patientsArraylist = new ArrayList<>();
    private LinkedList<Nurse> nurseLinkedList = new LinkedList<>();
    private ArrayList<HospitalRoom> roomArraylist = new ArrayList<>();


    public void newAppointment(Appointment appointment) {
        appointmentArrayList.add(appointment);
    }

    public void newPatient(Patient patient) throws InvalidAgeException, NameIsEmptyException {
        patientsArraylist.add(patient);
    }

    public void newDoctor(Doctor doctor) throws WrongSpecialtyException {
        Doctor d = new Doctor();
        doctorArraylist.add(d);
    }

    public void newHospitalRoom(HospitalRoom r) {
        roomArraylist.add(r);
    }

    public void newNurse(Nurse nurse) throws InvalidAgeException, NameIsEmptyException {
        nurseLinkedList.add(nurse);
    }

    public Patient getPatient(String name) throws PersonNotFoundException {
        for (Patient p : patientsArraylist) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new PersonNotFoundException(); //added exception
    }

    public Doctor getDoctor(String name) throws PersonNotFoundException {
        for (Doctor d : doctorArraylist) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        throw new PersonNotFoundException(); //added exception
    }


    public Doctor getDoctorPerSpecialty(String specialty) throws PersonNotFoundException {
        for (Doctor d : doctorArraylist) {
            if (d.getClass().getSimpleName().equals(specialty)) {
                return d;
            }
        }
        throw new PersonNotFoundException();
    }


    public  void setAppointment(Patient patient, Doctor doctor, LocalDate date) {
        appointmentArrayList.add(new Appointment(date, doctor, patient));
    }

    public LinkedList<Nurse> getNurseLinkedList() {
        return nurseLinkedList;
    }

    public ArrayList<HospitalRoom> getRoomArraylist() {
        return roomArraylist;
    }

    public ArrayList<Appointment> getAppointmentArrayList() {
        return appointmentArrayList;
    }

    public ArrayList<Doctor> getDoctorArraylist() {
        return doctorArraylist;
    }

    public ArrayList<Patient> getPatientsArraylist() {
        return patientsArraylist;
    }
}


