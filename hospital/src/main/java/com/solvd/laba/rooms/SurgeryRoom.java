package com.solvd.laba.rooms;

import com.solvd.laba.enums.RoomType;
import com.solvd.laba.exceptions.InvalidRoomNumberException;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;


public class SurgeryRoom extends HospitalRoom {

    private Nurse nurse;
    private Patient patient;

    public SurgeryRoom(int roomNumber, Nurse nurse) throws InvalidRoomNumberException {
        super(1, 2, roomNumber, RoomType.SURGERY_ROOM);
        this.nurse = nurse;
        this.patient = null;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public Patient getPatient() {
        return patient;
    }

    public RoomType getRoomType(){
        return RoomType.SURGERY_ROOM;
    }

    public Nurse getNurse() {
        return nurse;
    }

}
