package com.solvd.laba;


import com.solvd.laba.exceptions.*;
import com.solvd.laba.person.doctors.Doctor;
import com.solvd.laba.person.doctors.FamilyPhysician;
import com.solvd.laba.person.doctors.Gynecologist;
import com.solvd.laba.person.doctors.Pediatrician;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;
import com.solvd.laba.room.rooms.IntensiveCareRoom;
import com.solvd.laba.room.rooms.PatientsRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Scanner;


public class Runner {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InvalidRoomNumberException, InvalidAgeException, NameIsEmptyException, WrongSpecialtyException, PersonNotFoundException {


        //INITIALIZATION OF THE PEOPLE AND ROOMS


        Hospital hospital = new Hospital();


        addPatient("alejo", 23, true, "fever", 83, 182, hospital);
        addPatient("robert", 29, true, "broken bone", 90, 172, hospital);
        addPatient("juana", 15, false, "examination", 50, 117, hospital);
        addPatient("pepa", 29, false, "pregnant", 90, 172, hospital);


        addDoctor("Smith", 30, "Family Physician", hospital);
        addDoctor("James", 39, "Pediatrician", hospital);
        addDoctor("Jones", 32, "Gynecologist", hospital);

        addNurse("Ramirez", 21, hospital);
        addNurse("Da Silva", 28, hospital);
        addNurse("Perez", 36, hospital);
        addNurse("Gonzalez", 36, hospital);


        addPatientsRoom(1, hospital.getNurseLinkedList().get(0), hospital);
        addPatientsRoom(2, hospital.getNurseLinkedList().get(1), hospital);
        addPatientsRoom(3, hospital.getNurseLinkedList().get(2), hospital);

        addIntensiveCareRoom(4, hospital.getNurseLinkedList().get(3), hospital);


        //EXCEPTIONS

        //addPatient("",29,false, "pregnant",90,172, hospital);//NameIsEmptyException
        //addPatient("pepa",-29,false, "pregnant",90,172, hospital);//InvalidAgeException
        //hospital.getRoomArraylist().add(new PatientsRoom(-1, hospital.getNurseLinkedList().get(0))); //InvalidRoomNumberException


        //exception thrown in method hospital.getDoctor(String name). Cannot find solution yet.


        boolean i = true;
        while (i) {
            printMenu();
            LOGGER.info("Enter an option: ");
            int option = scanner.nextInt();

            switch (option) {

                case 0: //PRINT OPTIONS
                    printMenu();
                    break;

                case 1:  //ADD NEW PATIENT
                    addPatient(hospital);
                    break;

                case 2: //GET DIAGNOSTIC
                    LOGGER.info("Enter the name of the patient: ");
                    String patientName = scanner.next();
                    LOGGER.info("Enter the name of the doctor: ");
                    String doctorName = scanner.next();
                    Doctor d = hospital.getDoctor(doctorName);
                    d.getDiagnostic(hospital.getPatient(patientName), hospital);
                    break;
                case 3: //SET APPOINTMENT
                    LOGGER.info("Enter the name of the patient: ");
                    String patientName3 = scanner.next();
                    Patient p3 = hospital.getPatient(patientName3);
                    LOGGER.info("Enter the name of the doctor: ");
                    String doctorName1 = scanner.next();
                    Doctor d1 = hospital.getDoctor(doctorName1);
                    LOGGER.info("Enter the day for the appointment(dd): ");
                    int day = scanner.nextInt();
                    LOGGER.info("Enter the month for the appointment(mm): ");
                    int month = scanner.nextInt();
                    LOGGER.info("Enter the year for the appointment(yyyy): ");
                    int year = scanner.nextInt();
                    LocalDate date = LocalDate.of(year, month, day);

                    hospital.setAppointment(p3, d1, date);
                    break;

                case 4:
                    addDoctor(hospital);
                    break;
                case 5:
                    addNurse(hospital);
                    break;
                case 6: //print patient info
                    LOGGER.info("Enter the name of the patient: ");
                    String patientName2 = scanner.next();
                    LOGGER.info(hospital.getPatient(patientName2).toString());
                    break;
                case 7:
                    i = false;
            }
        }

    }

    static void printMenu() {
        LOGGER.info("Menu:\n" +
                "0.Show menu\n" +
                "1.Create new patient \n" +
                "2.Get diagnostic.\n" +
                "3.Set new appointment.\n" +
                "4.Add new doctor.\n" +
                "5.Add new nurse.\n" +
                "6.Print patient's info.\n" +
                "7.Quit.");
    }

    public static void addPatient(Hospital hospital) throws InvalidAgeException, NameIsEmptyException {
        String name;
        int age;
        String symptoms;
        int weight;
        int height;
        boolean isMale;

        LOGGER.info("Enter patient's name: ");
        name = scanner.next();
        LOGGER.info("Enter patient age: ");
        age = scanner.nextInt();
        LOGGER.info("Is male? true/false");
        isMale = scanner.nextBoolean();
        LOGGER.info("Enter weight: ");
        weight = scanner.nextInt();
        LOGGER.info("Enter height: ");
        height = scanner.nextInt();
        LOGGER.info("Enter symptoms: ");
        symptoms = scanner.next();

        addPatient(name, age, isMale, symptoms, weight, height, hospital);
    }


    public static void addPatient(String name, int age, boolean isMale, String symptoms, int weight, int height, Hospital hospital) throws InvalidAgeException, NameIsEmptyException {
        try {
            Patient p = new Patient(name, age, isMale, symptoms, weight, height);
            hospital.newPatient(p);
        } catch (InvalidAgeException | NameIsEmptyException e) {
            LOGGER.error("Caught exception " + e);
        }
    }

    public static void addDoctor(Hospital hospital) throws InvalidAgeException, NameIsEmptyException, WrongSpecialtyException {
        String name;
        int age;
        String specialty;


        LOGGER.info("Enter doctor's name: ");
        name = scanner.next();
        LOGGER.info("Enter age: ");
        age = scanner.nextInt();
        LOGGER.info("Enter specialty: ");
        specialty = scanner.next();


        addDoctor(name, age, specialty, hospital);
    }

    public static void addDoctor(String name, int age, String specialty, Hospital hospital) throws InvalidAgeException, NameIsEmptyException, WrongSpecialtyException {
        try {
            switch (specialty.toLowerCase()) {
                case "family physician":
                    FamilyPhysician f = new FamilyPhysician(name, age);
                    hospital.newDoctor(f);
                    break;
                case "gynecologist":
                    Gynecologist g = new Gynecologist(name, age);
                    hospital.newDoctor(g);
                    ;
                    break;
                case "pediatrician":
                    Pediatrician p = new Pediatrician(name, age);
                    hospital.newDoctor(p);
                    break;
                default:
                    throw new WrongSpecialtyException();
            }
        } catch (InvalidAgeException | NameIsEmptyException e) {
            LOGGER.error("Caught exception " + e);
        }
    }

    public static void addNurse(Hospital hospital) throws InvalidAgeException, NameIsEmptyException {
        String name;
        int age;


        LOGGER.info("Enter nurse's name: ");
        name = scanner.next();
        LOGGER.info("Enter age: ");
        age = scanner.nextInt();
        addNurse(name, age, hospital);
    }

    public static void addNurse(String name, int age, Hospital hospital) throws InvalidAgeException, NameIsEmptyException {
        try {
            Nurse n = new Nurse(name, age);
            hospital.newNurse(n);
        } catch (InvalidAgeException | NameIsEmptyException e) {
            LOGGER.error("Caught exception " + e);
        }
    }

    public static void addPatientsRoom(int roomNumber, Nurse nurse, Hospital hospital) throws InvalidRoomNumberException {
        try {
            PatientsRoom r = new PatientsRoom(roomNumber, nurse);
            hospital.newHospitalRoom(r);
        } catch (InvalidRoomNumberException e) {
            LOGGER.error("Caught exception " + e);
        }
    }

    public static void addIntensiveCareRoom(int roomNumber, Nurse nurse, Hospital hospital) throws InvalidRoomNumberException {
        try {
            IntensiveCareRoom r = new IntensiveCareRoom(roomNumber, nurse);
            hospital.newHospitalRoom(r);
        } catch (InvalidRoomNumberException e) {
            LOGGER.error("Caught exception " + e);
        }
    }
}
