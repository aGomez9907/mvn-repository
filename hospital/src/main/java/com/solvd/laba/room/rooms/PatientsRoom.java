package com.solvd.laba.room.rooms;

import com.solvd.laba.exceptions.InvalidRoomNumberException;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatientsRoom extends HospitalRoom {
    private static final Logger LOGGER = LogManager.getLogger();
    private Patient patient1;
    private Patient patient2;
    private Nurse nurse;

    public PatientsRoom(int roomNumber, Nurse nurse) throws InvalidRoomNumberException {
        super(2, 1, roomNumber);
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


    public void makeBed() {
        LOGGER.info("Nurse " + nurse.getName() + " prepared the bed for the patient.");
    }

    ;

    public void disinfectRoom() {
        LOGGER.info("Nurse " + nurse.getName() + " disinfected the room for the patient.");
    }

    public void prepareIVSolution() {
        LOGGER.info("Nurse " + nurse.getName() + " prepared the IV solution for the patient.");
    }

    public Nurse getNurse() {
        return nurse;
    }
}
