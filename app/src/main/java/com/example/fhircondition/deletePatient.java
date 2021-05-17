package com.example.fhircondition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class deletePatient extends AppCompatActivity {
    public static TextView deletePatientByNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_patient);


        deletePatientByNameField = (TextView) findViewById(R.id.deletePatientByNameField);
        TextView showPersonInfoTextbox = (TextView) findViewById(R.id.showPersonInfoTextbox);
        Button deletePatientButton = (Button) findViewById(R.id.deletePatientButton);
        Button backPatientButton = (Button) findViewById(R.id.backPatientButton);
        Button logOutButton = (Button) findViewById(R.id.logOutButton);
        Button getPatientByIdentificator = (Button) findViewById(R.id.getPatientByIdentificator);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

        deletePatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = deletePatientByNameField.getText().toString();
                deleteByName(name);//esetleges megerősítés a törlési kérelemről???
            }
        });

        getPatientByIdentificator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = deletePatientByNameField.getText().toString();
                String res = "";
                res = anamnesisActivity.getPatient(name);
//              showPersonInfoTextbox.setText("For the result check the console!");
                //System.out.println("TESTPRINT : " + anamnesisActivity.returnedname);
                //showPersonInfoTextbox.setText(anamnesisActivity.returnedname);
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


    public void deleteByName(String name) {
        DatabaseReference dbrf = FirebaseDatabase.getInstance().getReference("FHIRCondition").child(name);

        FirebaseFirestore db;
        FirebaseFirestore fireStore;
        fireStore = FirebaseFirestore.getInstance();
        db = fireStore.getInstance();
        DocumentReference patient = db.collection("FHIRCondition").document(name);
        db.collection("FHIRCondition").document(name).delete();//latest implementation of deleting
        db.collection("PersonDTO").document(name).delete();//latest implementation of deleting


        dbrf.removeValue();
        NotificationManager.deletePatient(deletePatient.this, name);

    }
}
