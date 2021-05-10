package com.example.fhircondition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deletePatient extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_patient);



        TextView deletePatientByNameField = (TextView) findViewById(R.id.deletePatientByNameField);
        Button deletePatientButton = (Button) findViewById(R.id.deletePatientButton);
        Button backPatientButton = (Button) findViewById(R.id.backPatientButton);


        deletePatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = deletePatientByNameField.getText().toString();
                deleteByName(name);//esetleges megerősítés a törlési kérelemről???
            }
        });



        backPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), anamnesisActivity.class);
                startActivity(startIntent);
            }
        });
    }

    public void deleteByName(String name){
        DatabaseReference dbrf = FirebaseDatabase.getInstance().getReference("PersonDTO").child(name);
        dbrf.removeValue();
        NotificationManager.deletePatient(deletePatient.this, name);
    }
}
