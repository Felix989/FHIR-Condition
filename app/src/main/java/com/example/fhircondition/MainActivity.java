package com.example.fhircondition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public FirebaseAuth myAuth;
    private NotificationHandler myNotificationHandler;
    public static boolean skipped = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAuth = FirebaseAuth.getInstance();
        myNotificationHandler = new NotificationHandler(MainActivity.this);

        EditText un = (EditText) findViewById(R.id.username);
        EditText pw = (EditText) findViewById(R.id.password);
        TextView ln = (TextView) findViewById(R.id.login_button);
        Button rb = (Button) findViewById(R.id.register_button);
        Button sb = (Button) findViewById(R.id.skipButton);

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loggedInIntent = new Intent(MainActivity.this, anamnesisActivity.class);
                myNotificationHandler.send("You are not logged in!");
                skipped = true;
//                NotificationHandler.unauthorized();
                YoYo.with(Techniques.Flash).duration(700).repeat(1).playOn(sb);
                final Handler handler = new Handler();//declaring a handler
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(loggedInIntent);
                    }
                }, 1700);
            }
        });

        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getun = un.getText().toString();
                String getpw = pw.getText().toString();

                try {
                    myAuth.signInWithEmailAndPassword(getun, getpw).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //csaba@email.com
                                //csaba123
                                //Toast.makeText(MainActivity.this, "User was logged in!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                myNotificationHandler.send("Logged in!");
                                NotificationManager.succesfullLogin(MainActivity.this);
                                myAuth.updateCurrentUser(myAuth.getCurrentUser());
                                skipped = false;
                                YoYo.with(Techniques.RubberBand).duration(700).repeat(1).playOn(ln);
//                            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);

                                final Handler handler = new Handler();//declaring a handler
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startNewIntent();
                                    }
                                }, 1700);
                            } else {
//                            Toast.makeText(MainActivity.this, "User was not logged in!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                NotificationManager.cantLogIn(MainActivity.this);
//                            myAuth.signOut();
                            }
                        }
                    });
                } catch (Exception e) {
                    NotificationManager.cantLogIn(MainActivity.this);
                    YoYo.with(Techniques.Shake).duration(700).repeat(1).playOn(ln);
                }


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

    private void startNewIntent() {
        Intent loggedInIntent = new Intent(MainActivity.this, anamnesisActivity.class);
        NotificationManager.succesfullLogin(MainActivity.this);
        startActivity(loggedInIntent);
    }
}

