package com.solvd.laba.person.doctors;

import com.solvd.laba.enums.Specialty;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.interfaces.IGetExam;
import com.solvd.laba.person.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Traumatologist extends Doctor {


    private static final Logger LOGGER = LogManager.getLogger();


    public Traumatologist() {
    }

    public Traumatologist(String name, int age) throws InvalidAgeException, NameIsEmptyException {
        super(name, age, Specialty.TRAUMATOLOGIST);

    }

    public double measureHeight(Patient patient) {
        return patient.getHeight();
    }

    public double measureWeight(Patient patient) {
        return patient.getWeight();
    }


    public boolean getDiagnostic(Patient p) {

        revision();

        LOGGER.info("The diagnosis is: ");
        switch (p.getSymptoms().toLowerCase()) {
            case "broken bone":
                if (IGetExam.getExam()) {
                    LOGGER.info("Open fracture, need to stay in hospital and get surgery.");
                    return true;
                } else LOGGER.info("Use a cast and get rest. Patient can go home");
                return false;
            case "knee pain":
                if (IGetExam.getExam()) {
                    LOGGER.info("The knee needs surgery.");
                    return true;
                } else LOGGER.info("Take medicine and come back in a week. ");
                return false;


        }
        return false;
    }
}
