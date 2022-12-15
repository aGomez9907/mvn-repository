package com.solvd.laba.room;


import com.solvd.laba.Hospital;
import com.solvd.laba.person.Patient;
import com.solvd.laba.room.rooms.HospitalRoom;
import com.solvd.laba.room.rooms.IntensiveCareRoom;
import com.solvd.laba.room.rooms.PatientsRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class AssignRoom {

    private static final Logger LOGGER = LogManager.getLogger();

    private static int i = 0;

    public static void assignRoom(Hospital hospital, Patient patient, boolean isIntensive) {


        ArrayList<HospitalRoom> roomArrayList = hospital.getRoomArraylist();


        for (HospitalRoom r : roomArrayList) {
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


