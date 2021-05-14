package com.example.fhircondition;

import android.content.Context;
import android.widget.Toast;

import static com.example.fhircondition.anamnesisActivity.person;

public class NotificationManager {

    public static void inputIsNull(Context cont) {
        Toast.makeText(cont, "Every input has to be filled!", Toast.LENGTH_LONG).show();
    }

    public static void succesfullLogin(Context cont) {
        Toast.makeText(cont, "Logged in successfully!", Toast.LENGTH_LONG).show();
    }

    public static void cantLogIn(Context cont) {
        Toast.makeText(cont, "Couldn't log in!", Toast.LENGTH_LONG).show();
    }

    public static void dataTemporalSave(Context cont) {
        if(!MainActivity.skipped) {
            Toast.makeText(cont, "Data is saved temporarly!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(cont, "Data isn't saved, because you are not logged in!", Toast.LENGTH_LONG).show();
        }
    }

    public static void dataPermanentSave(Context cont) {
        if(!MainActivity.skipped){
            Toast.makeText(cont, "Data has been pushed to the server!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(cont, "Data isn't saved, because you are not logged in!", Toast.LENGTH_LONG).show();
        }

    }

    public static void userRegistered(Context cont) {
        Toast.makeText(cont, "User is registered successfully!", Toast.LENGTH_LONG).show();
    }

    public static void registerError(Context cont) {
        Toast.makeText(cont, "User couldn't be created!", Toast.LENGTH_LONG).show();
    }

    public static void deletePatient(Context cont, String name) {
        Toast.makeText(cont, "Patient '" + name + "' was deleted!", Toast.LENGTH_LONG).show();
    }

    //statikus metódusból nem lehet hívni, úgyhogy ezt majd később ki kell javítani...
    public static void cantPushToDatabaseBecauseOfAuthentication(Context cont) {
        Toast.makeText(cont, "Cannot push data to database, because you are not logged in!", Toast.LENGTH_LONG).show();
    }

//    public static void bmiAlert(Context cont) {
//        int bmi = (person.weight / (person.height * person.height));
//        String msg = "Person's BMI.: " + bmi;
//        if (bmi < 18) {
//            msg += " | Underweight";
//        } else if (bmi > 25) {
//            msg += " | Normal";
//        } else {
//            msg += " | Obese";
//        }
//        Toast.makeText(cont, msg, Toast.LENGTH_LONG).show();
//    }
}
