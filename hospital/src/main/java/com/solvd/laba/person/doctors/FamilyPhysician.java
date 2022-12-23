package com.solvd.laba.person.doctors;

import com.solvd.laba.enums.Specialty;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.person.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FamilyPhysician extends Doctor {
    private static final Logger LOGGER = LogManager.getLogger();


    public FamilyPhysician() {
    }

    public FamilyPhysician(String name, int age) throws InvalidAgeException, NameIsEmptyException {
        super(name, age, Specialty.FAMILYPHISICIAN);

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
            case "fever":
                if (measureTemperature() > 37) {
                    LOGGER.info("Patient need to rest and ibuprofen every 8 hours.");
                    return false;
                } else if (measureTemperature() < 34) {
                    LOGGER.info("Patient has hypothermia, need to warm up.");
                    return true;
                } else LOGGER.info("Everything fine.");
                return false;
            case "examination":
                LOGGER.info("The patient weights " + p.getWeight() + "Kg and is " + p.getHeight() + "cm tall.");
                return false;
        }
        return false;
    }


}
