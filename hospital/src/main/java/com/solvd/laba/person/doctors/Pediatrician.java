package com.solvd.laba.person.doctors;

import com.solvd.laba.enums.Medicine;
import com.solvd.laba.enums.Specialty;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.interfaces.IGivePrescription;
import com.solvd.laba.person.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pediatrician extends Doctor {
    private final static Logger LOGGER = LogManager.getLogger();


    public Pediatrician() {
    }

    public Pediatrician(String name, int age) throws InvalidAgeException, NameIsEmptyException {
        super(name, age, Specialty.PEDIATRICIAN);
    }


    public double measureHeight(Patient patient) {
        return patient.getHeight();
    }

    public double measureWeight(Patient patient) {
        return patient.getWeight();
    }


    public boolean getDiagnostic(Patient p) {
        if (p.getAge() >= 18) {
            LOGGER.info("Not a kid, cannot be diagnosed.");
            return false;
        }
        revision();

        LOGGER.info("The diagnosis is: ");
        switch (p.getSymptoms().toLowerCase()) {
            case "fever":
                if (measureTemperature() > 37) {
                    LOGGER.info("Patient need to rest and take medicine.");
                    LOGGER.info("Prescription: "+ IGivePrescription.getPrescription(Medicine.IBUPROFEN, 8));
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
