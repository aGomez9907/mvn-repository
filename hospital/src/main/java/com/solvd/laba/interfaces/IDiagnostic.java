package com.solvd.laba.interfaces;

import com.solvd.laba.Hospital;
import com.solvd.laba.person.Patient;

public interface IDiagnostic extends IGetExam {


    boolean getDiagnostic(Patient patient);

}
