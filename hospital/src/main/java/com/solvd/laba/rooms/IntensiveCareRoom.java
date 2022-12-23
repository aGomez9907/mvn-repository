package com.solvd.laba.rooms;

import com.solvd.laba.exceptions.InvalidRoomNumberException;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;


public class IntensiveCareRoom extends HospitalRoom {
    private Patient patient;

    private Nurse nurse;

    public IntensiveCareRoom(int roomNumber, Nurse nurse) throws InvalidRoomNumberException {
        super(1, 3, roomNumber);
        this.nurse = nurse;
        this.patient = null;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Nurse getNurse() {
        return nurse;
    }
}
