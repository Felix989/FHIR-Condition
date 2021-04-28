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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class ListAllPatients extends AppCompatActivity {

    //    public FirebaseAuth myAuth;
    private DatabaseReference myDatabase;
    private ArrayList<PersonDTO> persons = new ArrayList<>();
    private ArrayAdapter<PersonDTO> personsAdapter;
    private CollectionReference collection;
    private static FirebaseFirestore fireStore;
    static boolean hasBeenPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpatients);

        myDatabase = FirebaseDatabase.getInstance().getReference();

        fireStore = FirebaseFirestore.getInstance();
        //collection = fireStore.collection("PersonDTO");
        CollectionReference maybeDB = fireStore.collection("PersonDTO");

        Button showDataButton = (Button) findViewById(R.id.showDataButton);
        Button register_New_Patiente = (Button) findViewById(R.id.registerNewPatiente);
        ListView patientListView = (ListView) findViewById(R.id.patientListView);

        personsAdapter = new ArrayAdapter<>(this, R.layout.listpatients, persons);
//        myDatabase.push().setValue(xxx).toString();


        showDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasBeenPressed) {
                    NotificationManager.dataPermanentSave(ListAllPatients.this);
                    anamnesisActivity.pushToServer();
                }
                hasBeenPressed = true;
            }
        });

        register_New_Patiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasBeenPressed) {
                    NotificationManager.dataPermanentSave(ListAllPatients.this);
                    anamnesisActivity.pushToServer();
                }
                hasBeenPressed = false;
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
                for (DataSnapshot data : snapshot.getChildren()) {
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
//
//        try{
//        DocumentReference docRef = fireStore.collection("PersonDTO").document(String.valueOf(anamnesisActivity.person_identification_number));
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if(document.exists()){
//                        System.out.println(document.getData());
//                    } else {
//                        System.out.println("Document doesn't exist!");
//                    }
//                } else {
//                    System.out.println("Task was unsuccessful!");
//                }
//            }
//        });}
//        catch (NullPointerException exception){
//
//        }
    }
}
