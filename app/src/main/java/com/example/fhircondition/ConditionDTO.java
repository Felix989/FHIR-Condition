package com.example.fhircondition;

public class ConditionDTO {
    boolean clinicalStatus;//0 not seen by doctor, 1 seen by doctor (toggle)
    boolean verificationStatus;//has been diagnosed (toggle)
    String category;//medical condition category (spinner)
    String severity;//severity level low, medium, high (radio button)
    String bodySite;//location of the problem or the source (text) body-site
    String medicalNotes;//doctors suggestion (text)
    boolean encounter;//has had it or not (toggle)
    String PersonID;
    //code for the conidtion
    //evidence [{code}, {deatil}]
    //evidence detail
    //code for the status
    //abament-age
    //onset-date
    //onset-info
    


    public boolean isClinicalStatus() {
        return clinicalStatus;
    }

    public void setClinicalStatus(boolean clinicalStatus) {
        this.clinicalStatus = clinicalStatus;
    }

    public boolean isVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(boolean verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getBodySite() {
        return bodySite;
    }

    public void setBodySite(String bodySite) {
        this.bodySite = bodySite;
    }

    public String getMedicalNotes() {
        return medicalNotes;
    }

    public void setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    public boolean isEncounter() {
        return encounter;
    }

    public void setEncounter(boolean encounter) {
        this.encounter = encounter;
    }

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String personID) {
        PersonID = personID;
    }

    public ConditionDTO(boolean clinical_status, boolean verification_status, String categ, String severi, String location, boolean enco, String mn, String pid) {
        clinicalStatus = clinical_status;
        verificationStatus = verification_status;
        category = categ;
        severity = severi;
        bodySite = location;
        encounter = enco;
        medicalNotes = mn;
        PersonID = pid;
    }
}
