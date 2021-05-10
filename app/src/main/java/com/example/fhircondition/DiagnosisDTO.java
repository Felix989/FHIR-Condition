package com.example.fhircondition;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DiagnosisDTO {
    String ConditionName;
    boolean isAcute; //nem régóta
    boolean isChornic; //régóta beteg
    String DiagnosisTime;
    boolean DrugNeeded;
    boolean NeedsHospitalization;
    boolean BloodTaken; //ásványi anyagok, típus, vesefunkciók, pajzsmirigy funkció, tumor, gyulladás, hormonok
    boolean UrineTaken; //ásványi anyagok, típus, vesefunkciók, pajzsmirigy funkció, tumor, gyulladás, hormonok
    boolean TherapyNeeded;
    boolean RehabiliatatonNeeded;
    boolean ControlNeeded;
    boolean SurgeryNeeded;
    boolean isInfectious;
    boolean hasCovid;
    String CovidTestType;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DiagnosisDTO(String conditionName,
                        boolean isAcute,
                        boolean isChornic,
                        boolean drugNeeded,
                        boolean needsHospitalization,
                        boolean bloodTaken,
                        boolean urineTaken,
                        boolean therapyNeeded,
                        boolean rehabiliatatonNeeded,
                        boolean controlNeeded,
                        boolean surgeryNeeded,
                        boolean isInfectious,
                        boolean hasCovid,
                        String covidTestType) {
        ConditionName = conditionName;
        this.isAcute = isAcute;
        this.isChornic = isChornic;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DiagnosisTime = dtf.format(now);

        DrugNeeded = drugNeeded;
        NeedsHospitalization = needsHospitalization;
        BloodTaken = bloodTaken;
        UrineTaken = urineTaken;
        TherapyNeeded = therapyNeeded;
        RehabiliatatonNeeded = rehabiliatatonNeeded;
        ControlNeeded = controlNeeded;
        SurgeryNeeded = surgeryNeeded;
        this.isInfectious = isInfectious;
        this.hasCovid = hasCovid;
        CovidTestType = covidTestType;
    }

    public String getConditionName() {
        return ConditionName;
    }

    public void setConditionName(String conditionName) {
        ConditionName = conditionName;
    }

    public boolean isAcute() {
        return isAcute;
    }

    public void setAcute(boolean acute) {
        isAcute = acute;
    }

    public boolean isChornic() {
        return isChornic;
    }

    public void setChornic(boolean chornic) {
        isChornic = chornic;
    }

    public String getDiagnosisTime() {
        return DiagnosisTime;
    }

    public void setDiagnosisTime(String diagnosisTime) {
        DiagnosisTime = diagnosisTime;
    }

    public boolean isDrugNeeded() {
        return DrugNeeded;
    }

    public void setDrugNeeded(boolean drugNeeded) {
        DrugNeeded = drugNeeded;
    }

    public boolean isNeedsHospitalization() {
        return NeedsHospitalization;
    }

    public void setNeedsHospitalization(boolean needsHospitalization) {
        NeedsHospitalization = needsHospitalization;
    }

    public boolean isBloodTaken() {
        return BloodTaken;
    }

    public void setBloodTaken(boolean bloodTaken) {
        BloodTaken = bloodTaken;
    }

    public boolean isUrineTaken() {
        return UrineTaken;
    }

    public void setUrineTaken(boolean urineTaken) {
        UrineTaken = urineTaken;
    }


    public boolean isTherapyNeeded() {
        return TherapyNeeded;
    }

    public void setTherapyNeeded(boolean therapyNeeded) {
        TherapyNeeded = therapyNeeded;
    }

    public boolean isRehabiliatatonNeeded() {
        return RehabiliatatonNeeded;
    }

    public void setRehabiliatatonNeeded(boolean rehabiliatatonNeeded) {
        RehabiliatatonNeeded = rehabiliatatonNeeded;
    }


    public boolean isControlNeeded() {
        return ControlNeeded;
    }

    public void setControlNeeded(boolean controlNeeded) {
        ControlNeeded = controlNeeded;
    }

    public boolean isSurgeryNeeded() {
        return SurgeryNeeded;
    }

    public void setSurgeryNeeded(boolean surgeryNeeded) {
        SurgeryNeeded = surgeryNeeded;
    }

    public boolean isInfectious() {
        return isInfectious;
    }

    public void setInfectious(boolean infectious) {
        isInfectious = infectious;
    }

    public boolean isHasCovid() {
        return hasCovid;
    }

    public void setHasCovid(boolean hasCovid) {
        this.hasCovid = hasCovid;
    }

    public String getCovidTestType() {
        return CovidTestType;
    }

    public void setCovidTestType(String covidTestType) {
        CovidTestType = covidTestType;
    }
}
