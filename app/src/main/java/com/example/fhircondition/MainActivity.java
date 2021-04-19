package com.example.fhircondition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public FirebaseAuth myAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAuth = FirebaseAuth.getInstance();

        EditText un = (EditText) findViewById(R.id.username);
        EditText pw = (EditText) findViewById(R.id.password);
        TextView ln = (TextView) findViewById(R.id.login_button);
        Button rb = (Button) findViewById(R.id.register_button);
        Button sb = (Button) findViewById(R.id.skipButton);

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loggedInIntent = new Intent(MainActivity.this, anamnesisActivity.class);
                startActivity(loggedInIntent);
            }
        });

        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getun = un.getText().toString();
                String getpw = pw.getText().toString();

                myAuth.signInWithEmailAndPassword(getun, getpw).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //csaba@email.com
                            //csaba123
                            //Toast.makeText(MainActivity.this, "User was logged in!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(MainActivity.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
                            myAuth.updateCurrentUser(myAuth.getCurrentUser());
                            final Handler handler = new Handler();//declaring a handler
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startNewIntent();
                                }
                            }, 1700);
                        } else {
//                            Toast.makeText(MainActivity.this, "User was not logged in!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(MainActivity.this, "Couldn't log in!", Toast.LENGTH_LONG).show();
//                            myAuth.signOut();
                        }
                    }
                });


            }
        });


        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(startIntent);
            }
        });




    }//oncreate vége

    private void startNewIntent(){
        Intent loggedInIntent = new Intent(MainActivity.this, anamnesisActivity.class);
        Toast.makeText(MainActivity.this, "Logged in!", Toast.LENGTH_LONG).show();
        startActivity(loggedInIntent);
    }
}

