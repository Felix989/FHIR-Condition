package com.example.fhircondition;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListAllPatients extends AppCompatActivity {

//    public FirebaseAuth myAuth;
    private DatabaseReference myDatabase;
    private ArrayList<PersonDTO> persons = new ArrayList<>();
    private ArrayAdapter<PersonDTO> personsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpatients);

        myDatabase = FirebaseDatabase.getInstance().getReference();

        Button showDataButton = (Button) findViewById(R.id.showDataButton);
        Button register_New_Patiente = (Button) findViewById(R.id.registerNewPatiente);
        ListView patientListView = (ListView) findViewById(R.id.patientListView);

        personsAdapter = new ArrayAdapter<>(this, R.layout.listpatients, persons);
//        myDatabase.push().setValue(xxx).toString();


        showDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesisActivity.pushToServer();
            }
        });

        register_New_Patiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anamnesisActivity.pushToServer();
                final Handler handler = new Handler();//declaring a handler
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent loggedInIntent = new Intent(ListAllPatients.this, anamnesisActivity.class);
                        startActivity(loggedInIntent);
                    }
                }, 1700);
            }
        });

        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.getChildren());
                for(DataSnapshot data: snapshot.getChildren()){
                    PersonDTO listedDTOinfo = data.getValue(PersonDTO.class);
                    persons.add(listedDTOinfo);
                    System.out.println(listedDTOinfo);
                }
                patientListView.setAdapter(personsAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Unexpected Error has occured!" + error.getCode() + " " + error.getMessage());
            }
        });
    }
}
