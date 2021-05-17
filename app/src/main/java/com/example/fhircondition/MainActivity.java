package com.example.fhircondition;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
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
    private static boolean locationPermissionGranted = false;
    public final int PERMISSIONS_REQUEST_ENABLE_GPS = 9002;
    public final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9003;

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

        getLocationPermission();

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


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isMapsEnabled(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

}

