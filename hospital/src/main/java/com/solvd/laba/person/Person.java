package com.solvd.laba.person;

import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;

public abstract class Person {


    private String name;
    private int age;


    public Person() {

    }

    public Person(String name, int age) throws InvalidAgeException, NameIsEmptyException {

        if (age < 0) {
            throw new InvalidAgeException("Age must be higher or equal than 0.");
        }
        if (name.isEmpty()) {
            throw new NameIsEmptyException("Name cannot be empty.");
        }
        this.name = name;
        this.age = age;


    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    //METHODS
    // The idea here is to provide methods for people to start working e.g. at 8am and stop at 17pm


    public void startWork() {
    }

    public void stopWork() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
