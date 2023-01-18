package com.solvd.laba;

import com.solvd.laba.enums.RoomType;
import com.solvd.laba.enums.Specialty;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.exceptions.PersonNotFoundException;
import com.solvd.laba.interfaces.IAssignRoom;
import com.solvd.laba.lambdas.ChangeSymptomsConsumer;
import com.solvd.laba.linkedlist.GenericLinkedList;
import com.solvd.laba.person.Nurse;
import com.solvd.laba.person.Patient;
import com.solvd.laba.person.doctors.Doctor;
import com.solvd.laba.person.doctors.Surgeon;
import com.solvd.laba.rooms.HospitalRoom;
import com.solvd.laba.rooms.IntensiveCareRoom;
import com.solvd.laba.rooms.PatientsRoom;
import com.solvd.laba.rooms.SurgeryRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hospital implements IAssignRoom {

    private static final Logger LOGGER = LogManager.getLogger();
    private ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
    private ArrayList<Doctor> doctorArraylist = new ArrayList<>();

    private ArrayList<Patient> patientsArraylist = new ArrayList<>();
    private GenericLinkedList<Nurse> nurseLinkedList = new GenericLinkedList<>();
    private ArrayList<HospitalRoom> roomArraylist = new ArrayList<>();


    public void newPatient(Patient patient) throws InvalidAgeException, NameIsEmptyException {
        patientsArraylist.add(patient);
    }

    public void newDoctor(Doctor doctor) {
        doctorArraylist.add(doctor);
    }

    public void newHospitalRoom(HospitalRoom r) {
        roomArraylist.add(r);
    }


    public void newNurse(Nurse nurse) throws InvalidAgeException, NameIsEmptyException {
        nurseLinkedList.add(nurse);
    }

    public void modifySymptoms(String symptoms, ChangeSymptomsConsumer<String> modifier) throws PersonNotFoundException {
        modifier.accept(symptoms);
        LOGGER.info("The symptoms were changed.");
    }


    public void assignRoom(Patient patient, Surgeon surgeon) {

        for (HospitalRoom r : roomArraylist) {
            if (r.getRoomType() == RoomType.SURGERY_ROOM) {
                SurgeryRoom sr = (SurgeryRoom) r;
                if (sr.getPatient() == null) {
                    sr.getNurse().disinfectRoom();
                    sr.getNurse().makeBed();
                    sr.getNurse().prepareIVSolution();

                    sr.setPatient(patient);
                    LOGGER.info("Patient set in room N°" + sr.getRoomNumber() + " and is ready to get surgery with" +
                            "doctor " + surgeon.getName());
                    return;
                } else {
                    LOGGER.info("Currently there is no room available in surgery room N°" + sr.getRoomNumber());
                }
            }
        }
        LOGGER.info("Currently there is no surgery room available. Please wait until one is free " +
                "or create a new room.");
    }


    public void assignRoom(Patient patient, boolean isIntensive) {

        for (HospitalRoom r : roomArraylist) {
            if (!isIntensive) {
                if (r.getRoomType() == RoomType.PATIENTS_ROOM) {
                    PatientsRoom pr = (PatientsRoom) r;
                    if (pr.getPatient1() == null) {

                        pr.getNurse().disinfectRoom();
                        pr.getNurse().makeBed();
                        pr.getNurse().prepareIVSolution();

                        pr.setPatient1(patient);
                        LOGGER.info("Patient set in room N°" + pr.getRoomNumber());
                        return;
                    } else if (pr.getPatient2() == null) {

                        pr.getNurse().disinfectRoom();
                        pr.getNurse().makeBed();
                        pr.getNurse().prepareIVSolution();

                        pr.setPatient2(patient);
                        LOGGER.info("Patient set in room N°" + pr.getRoomNumber());
                        return;
                    } else {
                        LOGGER.info("No bed available in room N°" + pr.getRoomNumber());
                    }
                }

            } else {
                if (r.getRoomType() == RoomType.INTENSIVE_CARE_ROOM) {
                    IntensiveCareRoom ir = (IntensiveCareRoom) r;
                    if (ir.getPatient() == null) {

                        ir.getNurse().disinfectRoom();
                        ir.getNurse().makeBed();
                        ir.getNurse().prepareIVSolution();
                        ir.setPatient(patient);

                        LOGGER.info("Patient set in room N°" + ir.getRoomNumber());
                        return;
                    } else {
                        LOGGER.info("No bed available in room N°" + ir.getRoomNumber());
                    }
                }
            }
        }
        LOGGER.info("Currently there is no surgery room available. Please wait until one is free " +
                "or create a new room.");

    }

    public void setAppointment(Appointment appointment) {

        int last = appointmentArrayList.size() - 1;
        if (last < 0) {
            appointmentArrayList.add(appointment);
        } else {
            appointmentArrayList = (ArrayList<Appointment>) appointmentArrayList.stream()
                    .sorted(Comparator.comparing(Appointment::getDate))
                    .collect(Collectors.toList());
            appointment.setDate(appointmentArrayList.get(last).getDate().plusDays(1));
            appointmentArrayList.add(appointment);
        }
        LOGGER.info(appointmentArrayList.toString());
    }

    public Patient getPatient(String name) throws PersonNotFoundException {
        Patient p = patientsArraylist.stream()
                .filter(patient -> name.equals(patient.getName()))
                .findAny()
                .orElse(null);
        if (p != null) {
            return p;
        } else throw new PersonNotFoundException("Patient name not found.");
    }

    public Doctor getDoctorPerSpecialty(Specialty specialty) throws PersonNotFoundException {
        Doctor doc = doctorArraylist.stream()
                .filter(doctor -> specialty.equals(doctor.getSpecialty()))
                .findAny()
                .orElse(null);

        if (doc != null) {
            return doc;
        } else throw new PersonNotFoundException("Specialty not found.");
    }

    public void getDiagnostic(String patient) throws PersonNotFoundException {
        getDiagnostic(getPatient(patient));
    }


    public void getNurseAge(Nurse nurse, Function<Nurse, Integer> function) {
        int age = function.apply(nurse);
        LOGGER.info("The nurse " + nurse.getName() + " is " + age + " years old.");
    }

    public List<Appointment> listAppointmentsOfToday(Function<ArrayList<Appointment>, List<Appointment>> function){
        return function.apply(appointmentArrayList);
    }

    public void getDiagnostic(Patient patient) throws PersonNotFoundException {
        LocalDate ld = LocalDate.now();
        Doctor doc = null;
        boolean putInHospital = false;

        switch (patient.getSymptoms()) {
            case "fever", "examination":
                if (patient.getAge() >= 18) {
                    doc = getDoctorPerSpecialty(Specialty.FAMILY_PHYSICIAN);
                } else {
                    doc = getDoctorPerSpecialty(Specialty.PEDIATRICIAN);
                }
                break;
            case "pelvic exam", "vomit":
                doc = getDoctorPerSpecialty(Specialty.GYNECOLOGIST);
                break;

            case "pregnant":
                doc = getDoctorPerSpecialty(Specialty.GYNECOLOGIST);
                assignRoom(patient, true);
                doc.getDiagnostic(patient);
                return;

            case "broken bone", "knee pain":
                doc = getDoctorPerSpecialty(Specialty.TRAUMATOLOGIST);
                putInHospital = doc.getDiagnostic(patient);
                if (putInHospital) {
                    Surgeon s = (Surgeon) getDoctorPerSpecialty(Specialty.SURGEON);
                    assignRoom(patient, s);
                    LOGGER.info("Surgery started.");

                    if (s.getSurgery()) {
                        assignRoom(patient, true);
                    } else {
                        patientsArraylist.remove(patient);
                    }

                    return;
                }
                break;

            default:
                LOGGER.info("We cannot get a diagnosis for those symptoms.");
                return;


        }

        putInHospital = doc.getDiagnostic(patient);
        if (putInHospital) {
            assignRoom(patient, false);
        } else {
            LOGGER.info("Appointment set to " + ld.plusDays(7) + " with doctor " + doc.getName());
            Appointment appointment = new Appointment(ld.plusDays(7), doc, patient);
            setAppointment(appointment);
        }


    }


    public GenericLinkedList<Nurse> getNurseLinkedList() {
        return nurseLinkedList;
    }

    public ArrayList<Appointment> getAppointmentArrayList() {
        return appointmentArrayList;
    }
}


