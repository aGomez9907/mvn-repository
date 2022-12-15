package com.solvd.laba.room.rooms;

import com.solvd.laba.exceptions.InvalidRoomNumberException;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntensiveCareRoom extends HospitalRoom {

    private static final Logger LOGGER = LogManager.getLogger();
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

    public void makeBed() {
        LOGGER.info("Bed ready for patient.");
    }

    public void disinfectRoom() {
        LOGGER.info("Room ready for patient.");
    }

    public void prepareIVSolution() {
        LOGGER.info("IV solution ready for patient.");
    }

    public Nurse getNurse() {
        return nurse;
    }
}
