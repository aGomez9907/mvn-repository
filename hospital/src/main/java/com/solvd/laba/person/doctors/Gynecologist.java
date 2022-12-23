package com.solvd.laba.person.doctors;

import com.solvd.laba.enums.Specialty;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.interfaces.IGetExam;
import com.solvd.laba.person.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gynecologist extends Doctor {

    private static Logger LOGGER = LogManager.getLogger();
    private String specialty = "Gynecologist";

    public Gynecologist() {
    }

    public Gynecologist(String name, int age) throws InvalidAgeException, NameIsEmptyException {
        super(name, age, Specialty.GYNECOLOGIST);
    }


    public void birth() {
        LOGGER.info("The baby has been born.");
    }

    public boolean pelvicExam() {
        return IGetExam.getExam();
    }

    public boolean pregnancyTest() {
        return IGetExam.getExam();
    }

    @Override
    public boolean getDiagnostic(Patient p) {
        if (p.isMale()) {
            LOGGER.info("The patient is male. Cannot be diagnosed.");
            return false;
        }
        revision();

        LOGGER.info("The diagnosis is: ");
        switch (p.getSymptoms().toLowerCase()) {

            case "pelvic exam":
                if (pelvicExam()) {
                    LOGGER.info("Everything is fine, can go home.");
                    return false;
                } else {
                    LOGGER.info("Exams went bad, need to stay at hospital");
                    return true;
                }

            case "vomit":
                if (pregnancyTest()) {
                    LOGGER.info("Congratulations you are pregnant.");
                    return false;
                } else {
                    LOGGER.info("Not pregnant, something you ate was bad.");
                }
                return false;
            case "pregnant":
                birth();
                return true;


        }
        return false;
    }

}

