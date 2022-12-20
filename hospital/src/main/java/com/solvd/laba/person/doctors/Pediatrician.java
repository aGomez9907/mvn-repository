package com.solvd.laba.person.doctors;

import com.solvd.laba.Hospital;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.interfaces.IGetExam;
import com.solvd.laba.person.Patient;
import com.solvd.laba.room.AssignRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pediatrician extends Doctor {
    private final static Logger LOGGER = LogManager.getLogger();


    public Pediatrician() {
    }

    public Pediatrician(String name, int age) throws InvalidAgeException, NameIsEmptyException {
        super(name, age);
    }


    public double measureHeight(Patient patient) {
        return patient.getHeight();
    }

    public double measureWeight(Patient patient) {
        return patient.getWeight();
    }


    public void getDiagnostic(Patient p, Hospital hospital) {
        if (p.getAge() >= 18) {
            LOGGER.info("Not a kid, cannot be diagnosed.");
        }
        revision();

        LOGGER.info("The diagnosis is: ");
        switch (p.getSymptoms().toLowerCase()) {
            case "fever":
                if (measureTemperature() > 37) {
                    LOGGER.info("Patient need to rest and ibuprofen every 8 hours.");
                    break;
                } else if (measureTemperature() < 34) {
                    LOGGER.info("Patient has hypothermia, need to warm up.");
                    break;
                } else LOGGER.info("Everything fine.");
                break;
            case "examination":
                LOGGER.info("The patient weights " + p.getWeight() + "Kg and is " + p.getHeight() + "cm tall.");
                break;
            case "broken bone":
                if (IGetExam.getExam()) {
                    LOGGER.info("Open fracture, need to stay in hospital.");
                    AssignRoom.assignRoom(hospital, p, false);
                    break;
                } else LOGGER.info("Use a cast and get rest. Patient can go home");
                break;
            default:
                LOGGER.info("We cannot get a diagnosis for those symptoms.");
                break;


        }
    }
}
