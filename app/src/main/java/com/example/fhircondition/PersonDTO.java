package com.example.fhircondition;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO {//patient | subject
    int age; //onset-age
    String name;
    int height;
    int weight;
    boolean gender; // 0 male | 1 female
    boolean isDeleted;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Nullable
    public List<ConditionDTO> getConditions() {
        return conditions;
    }

    public void setConditions(@Nullable List<ConditionDTO> conditions) {
        this.conditions = conditions;
    }

    public boolean isHasAllergies() {
        return hasAllergies;
    }

    public void setHasAllergies(boolean hasAllergies) {
        this.hasAllergies = hasAllergies;
    }

    public boolean isHasMedicine() {
        return hasMedicine;
    }

    public void setHasMedicine(boolean hasMedicine) {
        this.hasMedicine = hasMedicine;
    }

    @Nullable
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(@Nullable String bloodType) {
        this.bloodType = bloodType;
    }

    @Nullable
    List<ConditionDTO> conditions;
    @Nullable
    List<DiagnosisDTO> diagnosis;

    @Nullable
    public List<DiagnosisDTO> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(@Nullable List<DiagnosisDTO> diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Nullable
    boolean hasAllergies;
    @Nullable
    boolean hasMedicine;
    @Nullable
    String bloodType;

    public PersonDTO(){
        age = 0;
        name = "";
        height = 0;
        weight = 0;
        gender = false;
        hasAllergies = false;
        hasMedicine = false;
        bloodType = "";
        isDeleted = true;
    }
}
