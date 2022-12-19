package com.solvd.laba.person;

import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;

import java.util.Objects;

public class Patient extends Person {


    private boolean putInHospital;
    private String symptoms;
    private int weight;
    private int height;

    private boolean isMale;

    public Patient(String name, int age, boolean isMale, String symptoms, int weight, int height) throws InvalidAgeException, NameIsEmptyException {
        super(name, age);
        this.weight = weight;
        this.height = height;
        this.symptoms = symptoms;
        this.isMale = isMale;
    }


    public void setPutInHospital(boolean putInHospital) {
        this.putInHospital = putInHospital;
    }


    public boolean isMale() {
        return isMale;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return putInHospital == patient.putInHospital && weight == patient.weight && height == patient.height && isMale == patient.isMale && Objects.equals(symptoms, patient.symptoms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(putInHospital, symptoms, weight, height, isMale);
    }

    @Override
    public String toString() {
        return "Patient" +"\n"+
                "symptoms: " + symptoms  +"\n"+
                "weight: " + weight +"\n"+
                "height:" + height +"\n"+
                "male:" + isMale ;
    }
}
