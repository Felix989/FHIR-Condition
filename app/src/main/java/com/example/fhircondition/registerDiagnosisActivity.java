package com.example.fhircondition;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Random;

public class registerDiagnosisActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

//    private static FirebaseFirestore fireStore;
//    private CollectionReference collection;
    Spinner spinner;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registercondition);

        Switch clinical_StatusToggle = (Switch) findViewById(R.id.clinicalStatusToggle);
        Switch verification_StatusSwitch = (Switch) findViewById(R.id.verificationStatusSwitch);
        Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        RadioButton severity_Low_Button = (RadioButton) findViewById(R.id.severityLowButton);
        RadioButton severity_Medium_Button = (RadioButton) findViewById(R.id.severityMediumButton);
        RadioButton severity_High_Button = (RadioButton) findViewById(R.id.severityHighButton);
        EditText body_Site_Field = (EditText) findViewById(R.id.bodySiteField);
        EditText medical_Notes_Field = (EditText) findViewById(R.id.medicalNotesField);
        CheckBox encounter_CheckBox = (CheckBox) findViewById(R.id.encounterCheckBox);
        Button Register_Condition_Register_Button = (Button) findViewById(R.id.RegisterConditionRegisterButton);


//        fireStore = FirebaseFirestore.getInstance();
//        collection = fireStore.collection("ConditionDTO");

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Head");
        arrayList.add("Neck");
        arrayList.add("Body");
        arrayList.add("Left Hand");
        arrayList.add("Right Hand");
        arrayList.add("Left Shoulder");
        arrayList.add("Right Shoulder");
        arrayList.add("Left Elbow");
        arrayList.add("Right Elbow");
        arrayList.add("Left Wrist");
        arrayList.add("Right Wrist");
        arrayList.add("Abdomen");
        arrayList.add("Right Leg");
        arrayList.add("Left Leg");
        arrayList.add("Right Knee");
        arrayList.add("Left Knee");
        arrayList.add("Right Foot");
        arrayList.add("Left Foot");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.categorySpinner);
        spinner.setOnItemSelectedListener(this);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);


        Register_Condition_Register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean clinstat = clinical_StatusToggle.isChecked();
                boolean verif = verification_StatusSwitch.isChecked();
                String categor = String.valueOf(spinner.getSelectedItem());
                String sev = "";
                if (severity_Low_Button.isChecked()) {
                    sev = "Low";
                } else if (severity_Medium_Button.isChecked()) {
                    sev = "Medium";
                } else if (severity_High_Button.isChecked()) {
                    sev = "High";
                }
                String bodysi = body_Site_Field.getText().toString();
                String notes = medical_Notes_Field.getText().toString();
                boolean hasenc = encounter_CheckBox.isChecked();

//                    public ConditionDTO(boolean clinical_status, int verification_status, String categ, String severi, String location, boolean enco, String mn) {

                ConditionDTO condition = new ConditionDTO(clinstat, verif, categor, sev, bodysi, hasenc, notes, anamnesisActivity.person_identification_number);
                anamnesisActivity.addConditionToPerson(condition);
                startNewIntent();

            }
        });
    }//onCreate vége

    public String onSeverityButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str = "";
        switch (view.getId()) {
            case R.id.severityLowButton:
                if (checked)
                    str = "Low";
                return str;
            case R.id.severityMediumButton:
                if (checked)
                    str = "Medium";
                return str;
            case R.id.severityHighButton:
                if (checked)
                    str = "High";
                return str;
        }
        return str;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void startNewIntent(){
        Intent loggedInIntent = new Intent(registerDiagnosisActivity.this, registerIllnessActivity.class);
        startActivity(loggedInIntent);
    }

}
