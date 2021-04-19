package com.example.fhircondition;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class anamnesisActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseUser user;
    Spinner spinner;
    private static final String LOG_TAG = anamnesisActivity.class.getName();
    private static FirebaseFirestore fireStore;
    private CollectionReference collection;
    public static PersonDTO person;// = new PersonDTO();
    public static String person_identification_number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anamnesis);


        TextView nameField = (TextView) findViewById(R.id.nameField);
        TextView ageField = (TextView) findViewById(R.id.ageField);
        TextView weightField = (TextView) findViewById(R.id.weightField);
        RadioButton maleField = (RadioButton) findViewById(R.id.maleButton);
        RadioButton femaleField = (RadioButton) findViewById(R.id.femaleButton);
        Switch allergiesField = (Switch) findViewById(R.id.allergiesSwitch);
        Switch medicineField = (Switch) findViewById(R.id.medicineSwitch);
        Button recordField = (Button) findViewById(R.id.recordButton);
        Button all_patients = (Button) findViewById(R.id.jumpToListed);


        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
//            Log.d(LOG_TAG, "User is authenticated!");
            System.out.println("User is authenticated!");
        } else {
//            Log.d(LOG_TAG, "User is not authenticated!");
            System.out.println("User is not authenticated!");
        }
        System.out.println(user);


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A+");
        arrayList.add("A-");
        arrayList.add("AB+");
        arrayList.add("AB-");
        arrayList.add("B+");
        arrayList.add("B-");
        arrayList.add("O+");
        arrayList.add("O-");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.bloodTypeSpinner);
        spinner.setOnItemSelectedListener(this);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);


        fireStore = FirebaseFirestore.getInstance();
        collection = fireStore.collection("PersonDTO");

        all_patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ListAllPatients.class);
                startActivity(startIntent);
            }
        });

        recordField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString();
                int age = Integer.parseInt(String.valueOf(ageField.getText()));
                int weight = Integer.parseInt(weightField.getText().toString());
                boolean allergic = allergiesField.isChecked();
                boolean medicine = medicineField.isChecked();
                boolean gender = false; // 0 male | 1 female
                List<ConditionDTO> condi = new ArrayList<>();
                List<DiagnosisDTO> diag = new ArrayList<>();

                if (maleField.isChecked()) {
                    gender = false;
                } else {
                    gender = true;
                }
                String bloodType = spinner.getSelectedItem().toString();
//                System.out.println(name + " " +  age+ " " +  weight+ " " +  allergic+ " " +  medicine+ " " +  gender + " " + bloodType);
                //magasságot implementálni
                //PersonDTO person = new PersonDTO(age, name, 175, weight, gender, allergic, medicine, bloodType);
                //collection.add(new PersonDTO(age, name, 175, weight, gender, allergic, medicine, bloodType));
                try {
                    person = new PersonDTO();
                    person.setAge(age);
                    person.setName(name);
                    person.setWeight(weight);
                    person.setGender(gender);
                    person.setHasAllergies(allergic);
                    person.setHasMedicine(medicine);
                    person.setBloodType(bloodType);
                    person.conditions = condi;
                    person.diagnosis = diag;
                    startNewIntent();
                } catch (NullPointerException e) {
                    Toast.makeText(anamnesisActivity.this, "Every input has to be filled!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }//onCreate vége

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static void addConditionToPerson(ConditionDTO condition) {
        person.conditions.add(condition);
    }

    public static void addDiagnosisToPerson(DiagnosisDTO diag) {
        person.diagnosis.add(diag);
    }

    public String onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str = "";
        switch (view.getId()) {
            case R.id.maleButton:
                if (checked)
                    str = "Male";
                return str;
            case R.id.femaleButton:
                if (checked)
                    str = "Female";
                return str;
        }
        return str;
    }

    public void startNewIntent() {
        Intent startIntent = new Intent(getApplicationContext(), registerDiagnosisActivity.class);
        startActivity(startIntent);
    }

    public static void pushToServer() {
        int r = new Random().nextInt(99999999); // [0...99999999]
        person_identification_number = String.valueOf(r);
        fireStore.collection("PersonDTO").document(String.valueOf(r)).set(person)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(anamnesisActivity.this, "Data has been pushed to the database!", Toast.LENGTH_LONG).show();
                            System.out.println("Data has been pushed to the database!");
//                                    rules_version = '2';
//                                    service cloud.firestore {
//                                        match /databases/{database}/documents {
//                                            match /{multiSegment=**} {
//                                                allow read, write;
//                                            }
//                                        }
//                                    }
                        } else {
                            System.out.println("Data couldn't be pushed to the database!");
                            //Toast.makeText(anamnesisActivity.this, "Data couldn't be pushed to the database!", Toast.LENGTH_LONG).show();
//                                    rules_version = '2';
//                                    service cloud.firestore {
//                                        match /databases/{database}/documents {
//                                            match /{document=**} {
//                                                allow read, write: if false;
//                                            }
//                                        }
//                                    }
                        }
                    }
                });
    }
}
