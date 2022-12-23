package com.solvd.laba.person.doctors;

import com.solvd.laba.enums.Specialty;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.interfaces.IDiagnostic;
import com.solvd.laba.interfaces.IGetExam;
import com.solvd.laba.person.Patient;
import com.solvd.laba.person.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class Doctor extends Person implements IGetExam, IDiagnostic {

    private static final Logger LOGGER = LogManager.getLogger();
    private Specialty specialty;

    //CONSTRUCTOR
    public Doctor() {

    }

    public Doctor(String name, int age, Specialty specialty) throws InvalidAgeException, NameIsEmptyException {
        super(name, age);
        this.specialty = specialty;
    }


    //METHODS


    public Specialty getSpecialty() {
        return specialty;
    }

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
    public abstract boolean getDiagnostic(Patient p);
}




