package com.solvd.laba.rooms;

import com.solvd.laba.exceptions.InvalidRoomNumberException;

import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;



public class SurgeryRoom extends HospitalRoom{

    private Nurse nurse;
    private Patient patient;
    public SurgeryRoom(int roomNumber, Nurse nurse) throws InvalidRoomNumberException {
        super(1, 2, roomNumber);
        this.nurse = nurse;
        this.patient = null;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public Patient getPatient() {
        return patient;
    }

    public Nurse getNurse() {
        return nurse;
    }

}
