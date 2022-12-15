package com.solvd.laba.interfaces;

import com.solvd.laba.Hospital;
import com.solvd.laba.person.Patient;

public interface IDiagnostic extends IGetExam {


    void getDiagnostic(Patient patient, Hospital hospital);

}
