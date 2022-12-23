package com.solvd.laba.rooms;

import com.solvd.laba.enums.RoomType;
import com.solvd.laba.exceptions.InvalidRoomNumberException;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;


public class PatientsRoom extends HospitalRoom {

    private Patient patient1;
    private Patient patient2;
    private Nurse nurse;

    public PatientsRoom(int roomNumber, Nurse nurse) throws InvalidRoomNumberException {
        super(2, 1, roomNumber, RoomType.PATIENTS_ROOM);
        this.nurse = nurse;
        this.patient1 = null;
        this.patient2 = null;
    }

    public Patient getPatient1() {
        return patient1;
    }

    public void setPatient1(Patient patient1) {
        this.patient1 = patient1;
    }

    public Patient getPatient2() {
        return patient2;
    }

    public void setPatient2(Patient patient2) {
        this.patient2 = patient2;
    }


    public Nurse getNurse() {
        return nurse;
    }
}
