package com.solvd.laba.person.Doctors;

import com.solvd.laba.Hospital;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.interfaces.IDiagnostic;
import com.solvd.laba.interfaces.IGetExam;
import com.solvd.laba.person.Patient;
import com.solvd.laba.person.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Doctor extends Person implements IGetExam, IDiagnostic {

    private static final Logger LOGGER = LogManager.getLogger();

    //CONSTRUCTOR
    public Doctor() {

    }

    public Doctor(String name, int age) throws InvalidAgeException, NameIsEmptyException {
        super(name, age);
    }


    //METHODS



    public void revision() {
        LOGGER.info("Doctor " + super.getName() + " is examining the patient.");
    }

    public double measureTemperature() {
        return (Math.random() * (42 - 33) + 33);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public double measureHeight(Patient patient) {
        return patient.getHeight();
    }

    @Override
    public double measureWeight(Patient patient) {
        return patient.getWeight();
    }

    @Override
    public void getDiagnostic(Patient p, Hospital hospital) {

        revision();

        LOGGER.info("The diagnosis is: ");
        if ("fever".equals(p.getSymptoms().toLowerCase())) {
            if (measureTemperature() > 37) {
                LOGGER.info("Patient need to rest and ibuprofen every 8 hours.");
            } else if (measureTemperature() < 34) {
                LOGGER.info("Patient has hypothermia, need to warm up.");
            } else LOGGER.info("Everything fine.");
            //            case "headache":
//
//            case "vomit":
//
//
//            case "broken bone":
//
//            case "decompensation":
        } else {
            LOGGER.info("We cannot get a diagnosis for those symptoms.");
        }
    }

}

