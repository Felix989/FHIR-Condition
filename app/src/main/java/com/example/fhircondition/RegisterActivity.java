package com.example.fhircondition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth myAuth;
    private NotificationHandler myNotificationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerlayout);
        EditText registerUserName = (EditText) findViewById(R.id.registerUserName);
        EditText registerEmail = (EditText) findViewById(R.id.registerEmail);
        EditText registerPassword = (EditText) findViewById(R.id.registerPassword);
        Button registeringButton = (Button) findViewById(R.id.registeringButton);
        myAuth = FirebaseAuth.getInstance();


        registeringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String run = registerUserName.getText().toString();
                String rem = registerEmail.getText().toString();
                String rpw = registerPassword.getText().toString();
                myAuth.createUserWithEmailAndPassword(rem, rpw).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            myNotificationHandler.send("Registered!");
                            NotificationManager.userRegistered(RegisterActivity.this);
                            Intent startIntent = new Intent(getApplicationContext(), anamnesisActivity.class);
                            startActivity(startIntent);
                        } else {
                            NotificationManager.registerError(RegisterActivity.this);
                        }
                    }
                });
            }
        });
    }
}
