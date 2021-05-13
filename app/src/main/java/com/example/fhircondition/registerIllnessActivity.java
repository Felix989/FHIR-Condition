package com.example.fhircondition;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

public class registerIllnessActivity extends AppCompatActivity {
    public static DiagnosisDTO diag;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerillness);

        EditText Illnes_Name_Field = (EditText) findViewById(R.id.IllnesNameField);
        EditText covid_TestType_Field = (EditText) findViewById(R.id.covidTestTypeField);
        RadioButton is_Chronic_Button = (RadioButton) findViewById(R.id.isChronicButton);
        RadioButton is_Acuteis_Button = (RadioButton) findViewById(R.id.isAcuteisButton);
        Switch hospitalizationSwitch = (Switch) findViewById(R.id.hospitalizationSwitch);
        Switch drug_Switch = (Switch) findViewById(R.id.drugSwitch);
        Switch therapy_Switch = (Switch) findViewById(R.id.therapySwitch);
        Switch regah_Switch = (Switch) findViewById(R.id.regahSwitch);
        Switch covid_Switch = (Switch) findViewById(R.id.covidSwitch);
        Switch blood_Switch = (Switch) findViewById(R.id.bloodSwitch);
        Switch urine_Switch = (Switch) findViewById(R.id.urineSwitch);
        Switch control_Switch = (Switch) findViewById(R.id.controlSwitch);
        Switch surgery_Switch = (Switch) findViewById(R.id.surgerySwitch);
        Switch infectious_Switch = (Switch) findViewById(R.id.infectiousSwitch);
        Button next_Button = (Button) findViewById(R.id.nextButton);

        next_Button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                diag = new DiagnosisDTO(Illnes_Name_Field.getText().toString(),
                        is_Acuteis_Button.isChecked(),
                        is_Chronic_Button.isChecked(),
                        drug_Switch.isChecked(),
                        hospitalizationSwitch.isChecked(),
                        blood_Switch.isChecked(),
                        urine_Switch.isChecked(),
                        therapy_Switch.isChecked(),
                        regah_Switch.isChecked(),
                        control_Switch.isChecked(),
                        surgery_Switch.isChecked(),
                        infectious_Switch.isChecked(),
                        covid_Switch.isChecked(),
                        covid_TestType_Field.getText().toString());
                anamnesisActivity.addDiagnosisToPerson(diag);
                System.out.println(diag);
//                anamnesisActivity.pushToServer();
                NotificationManager.dataTemporalSave(registerIllnessActivity.this);
                startNewIntent();
            }

        });


    }//onCreate vége

    private void startNewIntent(){
        Intent loggedInIntent = new Intent(registerIllnessActivity.this, ListAllPatients.class);
        startActivity(loggedInIntent);
    }
}
