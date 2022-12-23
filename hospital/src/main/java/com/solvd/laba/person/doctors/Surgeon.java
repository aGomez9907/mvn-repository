package com.solvd.laba.person.doctors;

import com.solvd.laba.enums.Specialty;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.interfaces.IGetExam;
import com.solvd.laba.person.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Surgeon extends Doctor{

    public static final Logger LOGGER = LogManager.getLogger();

    public Surgeon(String name, int age, Specialty specialty) throws InvalidAgeException, NameIsEmptyException {
        super(name, age, Specialty.SURGEON);
    }

    @Override
    public boolean getDiagnostic(Patient p) {
        return false;
    }

    public boolean getSurgery(){
        if(IGetExam.getExam()){
            LOGGER.info("Surgery was successful");
            return true;
        }else {
            LOGGER.info("Surgery was unsuccessful. Patient died.");
            return false;
        }
    }
}
