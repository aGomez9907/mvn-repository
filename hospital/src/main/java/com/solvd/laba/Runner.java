package com.solvd.laba;


import com.solvd.laba.exceptions.*;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;
import com.solvd.laba.person.Person;
import com.solvd.laba.person.doctors.*;
import com.solvd.laba.rooms.HospitalRoom;
import com.solvd.laba.rooms.IntensiveCareRoom;
import com.solvd.laba.rooms.PatientsRoom;
import com.solvd.laba.rooms.SurgeryRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Runner {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InvalidRoomNumberException, InvalidAgeException, NameIsEmptyException, WrongSpecialtyException, PersonNotFoundException {


        //INITIALIZATION OF THE HOSPITAL, PEOPLE AND ROOMS


        Hospital hospital = new Hospital();


        addPatient("alejo", 23, true, "fever", 83, 182, hospital);
        addPatient("robert", 29, true, "broken bone", 90, 172, hospital);
        addPatient("juana", 15, false, "examination", 50, 117, hospital);
        addPatient("pepa", 29, false, "pelvic exam", 90, 172, hospital);


        addDoctor("Smith", 30, "Family Physician", hospital);
        addDoctor("James", 39, "Pediatrician", hospital);
        addDoctor("Jones", 32, "Gynecologist", hospital);
        addDoctor("Scott", 47, "Surgeon", hospital);
        addDoctor("Schrute", 47, "Traumatologist", hospital);

        addNurse("Ramirez", 21, hospital);
        addNurse("Da Silva", 28, hospital);
        addNurse("Perez", 36, hospital);
        addNurse("Gonzalez", 36, hospital);
        addNurse("Gomez", 32, hospital);


        addPatientsRoom(1, hospital.getNurseLinkedList().get(0), hospital);
        addPatientsRoom(2, hospital.getNurseLinkedList().get(1), hospital);
        addPatientsRoom(3, hospital.getNurseLinkedList().get(2), hospital);

        addIntensiveCareRoom(4, hospital.getNurseLinkedList().get(3), hospital);

        addSurgeryRoom(5, hospital.getNurseLinkedList().get(4), hospital);


        //Custom lambda
        hospital.modifySymptoms("knee pain", symptoms -> hospital.getPatient("alejo").setSymptoms(symptoms));

        //lambda used as getter
        hospital.getNurseAge(hospital.getNurseLinkedList().get(0), Person::getAge);


        //OPTIONS MENU
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
                    hospital.getDiagnostic(patientName);
                    break;
                case 3:
                    addDoctor(hospital);
                    break;
                case 4:
                    addNurse(hospital);
                    break;
                case 5: //print patient info
                    LOGGER.info("Enter the name of the patient: ");
                    String patientName2 = scanner.next();
                    LOGGER.info(hospital.getPatient(patientName2).toString());
                    break;
                case 6: //lambda to print all today's appointments
                   LOGGER.info("Today appointments are: " + hospital.listAppointmentsOfToday((b) -> b.stream()
                            .filter(appointment -> Objects.equals(appointment.getDate(), LocalDate.now())).collect(Collectors.toList()))
                            .toString());
                    break;

                case 7:
                    i = false;
            }
        }

    }

    static void printMenu() {
        LOGGER.info("Menu:\n" + "0.Show menu\n" + "1.Create new patient \n" + "2.Get diagnostic.\n" + "3.Add new doctor.\n" + "4.Add new nurse.\n" + "5.Print patient's info.\n" + "6.Print today appointments.\n" + "7.Quit.");

    }

    //used to add patient from menu
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

    // used to add patient to arrayList of patient
    public static void addPatient(String name, int age, boolean isMale, String symptoms, int weight, int height, Hospital hospital) throws InvalidAgeException, NameIsEmptyException {
        try {
            Patient p = new Patient(name, age, isMale, symptoms, weight, height);
            hospital.newPatient(p);
        } catch (InvalidAgeException | NameIsEmptyException e) {
            LOGGER.error("Caught exception " + e);
        }
    }

    //used to add doctor from menu
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

    // used to add doctor to arrayList of doctor
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
                case "surgeon":
                    Surgeon s = new Surgeon(name, age);
                    hospital.newDoctor(s);
                    break;
                case "traumatologist":
                    Traumatologist t = new Traumatologist(name, age);
                    hospital.newDoctor(t);
                    break;
                default:
                    throw new WrongSpecialtyException();
            }
        } catch (InvalidAgeException | NameIsEmptyException e) {
            LOGGER.error("Caught exception " + e);
        }
    }

    // used to add nurse from menu
    public static void addNurse(Hospital hospital) throws InvalidAgeException, NameIsEmptyException {
        String name;
        int age;


        LOGGER.info("Enter nurse's name: ");
        name = scanner.next();
        LOGGER.info("Enter age: ");
        age = scanner.nextInt();
        addNurse(name, age, hospital);
    }

    // used to add nurse to linkedList of nurse
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
            HospitalRoom r = new PatientsRoom(roomNumber, nurse);
            hospital.newHospitalRoom(r);
        } catch (InvalidRoomNumberException e) {
            LOGGER.error("Caught exception " + e);
        }
    }

    public static void addIntensiveCareRoom(int roomNumber, Nurse nurse, Hospital hospital) throws InvalidRoomNumberException {
        try {
            HospitalRoom r = new IntensiveCareRoom(roomNumber, nurse);
            hospital.newHospitalRoom(r);
        } catch (InvalidRoomNumberException e) {
            LOGGER.error("Caught exception " + e);
        }
    }


    public static void addSurgeryRoom(int roomNumber, Nurse nurse, Hospital hospital) throws InvalidRoomNumberException {
        try {
            HospitalRoom sr = new SurgeryRoom(roomNumber, nurse);
            hospital.newHospitalRoom(sr);
        } catch (InvalidRoomNumberException e) {
            LOGGER.error("Caught exception " + e);
        }
    }

    // to trigger some EXCEPTIONS

    //addPatient("",29,false, "pregnant",90,172, hospital);//NameIsEmptyException
    //addPatient("pepa",-29,false, "pregnant",90,172, hospital);//InvalidAgeException
    //hospital.getRoomArraylist().add(new PatientsRoom(-1, hospital.getNurseLinkedList().get(0))); //InvalidRoomNumberException
}
