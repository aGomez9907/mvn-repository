package com.solvd.laba;


import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.InvalidRoomNumberException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.exceptions.WrongSpecialtyException;
import com.solvd.laba.person.Doctors.Doctor;
import com.solvd.laba.person.Doctors.FamilyPhysician;
import com.solvd.laba.person.Doctors.Gynecologist;
import com.solvd.laba.person.Doctors.Pediatrician;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;
import com.solvd.laba.room.rooms.IntensiveCareRoom;
import com.solvd.laba.room.rooms.PatientsRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Runner {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws InvalidRoomNumberException, InvalidAgeException, NameIsEmptyException, WrongSpecialtyException {


        //INITIALIZATION OF THE PEOPLE AND ROOMS


        Hospital hospital = new Hospital();


        addPatient("alejo", 23, true, "fever", 83, 182, hospital);
        addPatient("robert", 29, true, "broken bone", 90, 172, hospital);
        addPatient("juana", 15, false, "examination", 50, 117, hospital);
        addPatient("pepa", 29, false, "pregnant", 90, 172, hospital);


        addDoctor("Smith", 30, "FamilyPhysician", hospital);
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


        //Testing if logger works as intended

        LOGGER.info(hospital.getPatientsArraylist().get(0).getName());
        LOGGER.info("This is an info message");
        LOGGER.info("Another info message");


        //MENU STILL NOT FINISHED


       /* boolean i = true;
        while (i) {
            printMenu();
            System.out.println("Enter an option: ");
            int option = scanner.nextInt();

            switch (option) {

                case 0: //PRINT OPTIONS
                    printMenu();
                    break;

                case 1:  //ADD NEW PATIENT
                    addPatient();
                    break;

                case 2: //GET DIAGNOSTIC
                    System.out.println("Enter the name of the patient: ");
                    String patientName2 = scanner.next();
                    if (Hospital.getPatient(patientName2) <= 0) {
                        IDiagnostic.getDiagnostic(Hospital.getPatient(patientName2));

                    } else System.out.println("The patient couldn't be found.");
                    break;
                case 3: //SET APPOINTMENT
                    System.out.println("Enter the name of the patient: ");
                    String patientName3 = scanner.next();
                    System.out.println("Enter the name of the doctor: ");
                    String doctorName = scanner.next();
                    System.out.println("Enter the date for the appointment (yyyy-mm-dd): ");
                    String date = scanner.next();

                    if (Hospital.getPatient(patientName3) <= 0 && Hospital.getDoctor(doctorName) <= 0) {
                        Hospital.setAppointment(Hospital.getPatient(patientName3), Hospital.getDoctor(doctorName), date);

                    } else System.out.println("The patient or doctor couldn't be found.");
                    break;
                case 4:
                    i = false;
            }
        }*/

    }

//    static void printMenu() {
//        System.out.println("Menu:\n" +
//                "0.Show menu\n" +
//                "1.Create new patient \n" +
//                "2.Get diagnostic.\n" +
//                "3.Search appointments.\n" +
//                "4.Quit.");
//    }
//
//    static void addPatient(){
//        String patientName1;
//        int age;
//        String nationality;
//        String symptoms;
//        int weight;
//        int height;
//        boolean isMale;
//
//        System.out.println("Enter new patient name: ");
//        patientName1 = scanner.next();
//        System.out.println("Enter patient age: ");
//        age = scanner.nextInt();
//        System.out.println("Is male? true/false");
//        isMale = scanner.nextBoolean();
//        System.out.println("Enter nationality: ");
//        nationality = scanner.next();
//        System.out.println("Enter weight: ");
//        weight = scanner.nextInt();
//        System.out.println("Enter height: ");
//        height = scanner.nextInt();
//        System.out.println("Enter symptoms: ");
//        symptoms = scanner.next();
//
//        Hospital.newPatient(patientName1, age, isMale, nationality, symptoms, weight, height);
//    }


    public static void addPatient(String name, int age, boolean isMale, String symptoms, int weight, int height, Hospital hospital) throws InvalidAgeException, NameIsEmptyException {
        try {
            Patient p = new Patient(name, age, isMale, symptoms, weight, height);
            hospital.newPatient(p);
        } catch (InvalidAgeException | NameIsEmptyException e) {
            LOGGER.error("Caught exception " + e);
        }
    }


    public static void addDoctor(String name, int age, String specialty, Hospital hospital) throws InvalidAgeException, NameIsEmptyException, WrongSpecialtyException {
        try {
            switch (specialty.toLowerCase()) {
                case "familyphysician":
                    FamilyPhysician f = new FamilyPhysician(name,age);
                    hospital.newDoctor(f);
                    break;
                case "gynecologist":
                    Gynecologist g = new Gynecologist(name,age);
                    hospital.newDoctor(g);
                    ;
                    break;
                case "pediatrician":
                    Pediatrician p = new Pediatrician(name,age);
                    hospital.newDoctor(p);
                    break;
                default:
                    throw new WrongSpecialtyException();
            }
        } catch (InvalidAgeException | NameIsEmptyException e) {
            LOGGER.error("Caught exception " + e);
        }
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
