package com.solvd.laba;

import com.solvd.laba.enums.Specialty;
import com.solvd.laba.exceptions.InvalidAgeException;
import com.solvd.laba.exceptions.NameIsEmptyException;
import com.solvd.laba.exceptions.PersonNotFoundException;
import com.solvd.laba.exceptions.WrongSpecialtyException;
import com.solvd.laba.interfaces.IAssignRoom;
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
import java.util.LinkedList;

public class Hospital implements IAssignRoom {

    private static final Logger LOGGER = LogManager.getLogger();
    private ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
    private ArrayList<Doctor> doctorArraylist = new ArrayList<>();

    private ArrayList<Patient> patientsArraylist = new ArrayList<>();
    private LinkedList<Nurse> nurseLinkedList = new LinkedList<>();
    private ArrayList<HospitalRoom> roomArraylist = new ArrayList<>();


    public void newPatient(Patient patient) throws InvalidAgeException, NameIsEmptyException {
        patientsArraylist.add(patient);
    }

    public void newDoctor(Doctor doctor) throws WrongSpecialtyException {
        doctorArraylist.add(doctor);
    }

    public void newHospitalRoom(HospitalRoom r) {
        roomArraylist.add(r);
    }


    public void newNurse(Nurse nurse) throws InvalidAgeException, NameIsEmptyException {
        nurseLinkedList.add(nurse);
    }

    public Patient getPatient(String name) throws PersonNotFoundException {
        for (Patient p : patientsArraylist) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new PersonNotFoundException(); //added exception
    }

    public Doctor getDoctor(String name) throws PersonNotFoundException {
        for (Doctor d : doctorArraylist) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        throw new PersonNotFoundException(); //added exception
    }


    public Doctor getDoctorPerSpecialty(Specialty specialty) throws PersonNotFoundException {
        for (Doctor d : doctorArraylist) {

            if (d.getSpecialty().equals(specialty)) {
                return d;
            }
        }
        throw new PersonNotFoundException();
    }

    public void getDiagnostic(String patient) throws PersonNotFoundException {
        getDiagnostic(getPatient(patient));
    }

    public void getDiagnostic(Patient patient) throws PersonNotFoundException {
        LocalDate ld = LocalDate.now();


        switch (patient.getSymptoms()) {
            case "fever", "examination":
                if (patient.getAge() >= 18) {
                    Doctor fp = getDoctorPerSpecialty(Specialty.FAMILYPHISICIAN);
                    boolean putInHospitalFP = fp.getDiagnostic(patient);
                    if (putInHospitalFP) {
                        assignRoom(patient, false);
                    } else {
                        Appointment appointment = new Appointment(ld.plusDays(7), fp, patient);
                        setAppointment(appointment);   //need to update setAppointment to add time and
                    }                                                           //to check if the appointment is taken.
                } else {
                    Doctor p = getDoctorPerSpecialty(Specialty.PEDIATRICIAN);
                    boolean putInHospitalFP = p.getDiagnostic(patient);
                    if (putInHospitalFP) {
                        assignRoom(patient, false);
                    } else {
                        Appointment appointment = new Appointment(ld.plusDays(7), p, patient);
                        setAppointment(appointment);
                    }
                }
                break;
            case "pelvic exam", "vomit":
                Doctor g = getDoctorPerSpecialty(Specialty.GYNECOLOGIST);
                boolean putInHospitalG = g.getDiagnostic(patient);
                if (putInHospitalG) {
                    assignRoom(patient, false);
                } else {
                    Appointment appointment = new Appointment(ld.plusDays(7), g, patient);
                    setAppointment(appointment);
                }
                break;

            case "pregnant":
                Doctor g2 = getDoctorPerSpecialty(Specialty.GYNECOLOGIST);
                assignRoom(patient, true);
                g2.getDiagnostic(patient);
                break;

            case "broken bone", "knee pain":
                Doctor t = getDoctorPerSpecialty(Specialty.TRAUMATOLOGIST);
                boolean putInHospitalT = t.getDiagnostic(patient);
                if (putInHospitalT) {
                    Surgeon s = (Surgeon) getDoctorPerSpecialty(Specialty.SURGEON);
                    assignRoom(patient, s);
                    if(s.getSurgery()){
                        assignRoom(patient, true);
                    }
                } else {
                    Appointment appointment = new Appointment(ld.plusDays(7), t, patient);
                    setAppointment(appointment);
                }
                break;

            default:
                LOGGER.info("We cannot get a diagnosis for those symptoms.");
                break;


        }
    }

    public void setAppointment(Appointment appointment) {
        appointmentArrayList.add(appointment);
    }

    public LinkedList<Nurse> getNurseLinkedList() {
        return nurseLinkedList;
    }

    public ArrayList<HospitalRoom> getRoomArraylist() {
        return roomArraylist;
    }

    public ArrayList<Appointment> getAppointmentArrayList() {
        return appointmentArrayList;
    }

    public ArrayList<Doctor> getDoctorArraylist() {
        return doctorArraylist;
    }

    public ArrayList<Patient> getPatientsArraylist() {
        return patientsArraylist;
    }



    public void assignRoom(Patient patient, Surgeon surgeon){
        for (HospitalRoom r : roomArraylist) {
            if (r.getClass().getSimpleName().equals("SurgeryRoom")){
                SurgeryRoom sr = (SurgeryRoom) r;
                if(sr.getPatient() == null){
                    sr.getNurse().disinfectRoom();
                    sr.getNurse().makeBed();
                    sr.getNurse().prepareIVSolution();

                    sr.setPatient(patient);
                    LOGGER.info("Patient set in room N°" + sr.getRoomNumber()+" and is ready to get surgery with" +
                            "doctor "+ surgeon.getName());
                }
            }
        }

    }

    public void assignRoom(Patient patient, boolean isIntensive) {

        for (HospitalRoom r : roomArraylist) {
            if (!isIntensive) {
                if (r.getClass().getSimpleName().equals("PatientsRoom")) {
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
                    } else LOGGER.info("No bed available in room N°" + pr.getRoomNumber());
                }

            } else {
                if (r.getClass().getSimpleName().equals("IntensiveCareRoom")) {
                    IntensiveCareRoom ir = (IntensiveCareRoom) r;
                    if (ir.getPatient() == null) {

                        ir.getNurse().disinfectRoom();
                        ir.getNurse().makeBed();
                        ir.getNurse().prepareIVSolution();
                        ir.setPatient(patient);

                        LOGGER.info("Patient set in room N°" + ir.getRoomNumber());
                        return;
                    } else LOGGER.info("No bed available in room N°" + ir.getRoomNumber());
                }
            }
        }

    }


}


