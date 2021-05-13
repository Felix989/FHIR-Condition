package com.example.fhircondition;

public class FHIRCondition {

    boolean verificationStatus;
    String category;
    String severity;
    int code;
    String bodySite;
    PersonDTO subject;
    boolean encounter;
    String onsetDateTime;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBodySite() {
        return bodySite;
    }

    public void setBodySite(String bodySite) {
        this.bodySite = bodySite;
    }

    public PersonDTO getSubject() {
        return subject;
    }

    public void setSubject(PersonDTO subject) {
        this.subject = subject;
    }

    public boolean isEncounter() {
        return encounter;
    }

    public void setEncounter(boolean encounter) {
        this.encounter = encounter;
    }

    public String getOnsetDateTime() {
        return onsetDateTime;
    }

    public void setOnsetDateTime(String onsetDateTime) {
        this.onsetDateTime = onsetDateTime;
    }

    public Integer getRecorder() {
        return recorder;
    }

    public void setRecorder(Integer recorder) {
        this.recorder = recorder;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    Integer recorder;
    String stage;
    String summary;
    String assessment;
    String type;
    String evidence;
    String detail;
    String note;

    public void printEveryProperty(){
        System.out.println(assessment);
        System.out.println(bodySite);
        System.out.println(category);
        System.out.println(code);
        System.out.println(detail);
        System.out.println(encounter);
        System.out.println(evidence);
        System.out.println(note);
        System.out.println(onsetDateTime);
        System.out.println(recorder);
        System.out.println(severity);
        System.out.println(stage);
        System.out.println(bodySite);
        System.out.println(subject);
        System.out.println(summary);
        System.out.println(verificationStatus);
        System.out.println(type);
    }

    public FHIRCondition() {
        this.verificationStatus = registerDiagnosisActivity.condition.verificationStatus;
        this.category = registerDiagnosisActivity.condition.category;
        this.severity = registerDiagnosisActivity.condition.severity;
        this.code = 123;
        this.bodySite = registerDiagnosisActivity.condition.bodySite;
        this.subject = anamnesisActivity.person;
        this.encounter = registerDiagnosisActivity.condition.encounter;
        this.onsetDateTime = registerIllnessActivity.diag.getDiagnosisTime();
        this.recorder = 123;
        if(registerDiagnosisActivity.condition.clinicalStatus){
            this.stage = "Chronic";
        } else {
            this.stage = "Acute";
        }
        this.summary = "summary";
        this.assessment = "assessment";
        this.type = "type";
        this.evidence = "evidence";
        this.detail = "detail";
        this.note = registerDiagnosisActivity.condition.medicalNotes;
    }
}
