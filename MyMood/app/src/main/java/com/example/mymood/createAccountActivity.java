package com.example.mymood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class createAccountActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    //Fire base
    String refURL = "https://mymood-84274-default-rtdb.firebaseio.com/";
    FirebaseDatabase database = FirebaseDatabase.getInstance(refURL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton goBack;
        TextView dobBtn;
        Button confirmAccCreation;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        goBack = findViewById(R.id.createAccount_back);
        goBack.setOnClickListener((View v) -> toLogin());

        confirmAccCreation = findViewById(R.id.confirmAccountCreation);
        confirmAccCreation.setOnClickListener((View v) -> createAcc());

        dobBtn = findViewById(R.id.dobBtn);
        dobBtn.setOnClickListener((View v) -> showDatePickerDialog());

    }

    private void createAcc() {

        // Error  msg boxes
        TextView badUsername = findViewById(R.id.badUsername);
        TextView badPassword = findViewById(R.id.badPassword);

        badUsername.setText("");
        badPassword.setText("");

        // Code for obtaining values inputted
        EditText usernameBox = findViewById(R.id.createUsername);
        String username = (String) usernameBox.getText().toString();

        EditText passwordBox = findViewById(R.id.createPassword);
        String password = (String) passwordBox.getText().toString();

        TextView dobBox = findViewById(R.id.selectedDOB);
        String dob = (String) dobBox.getText().toString();

        // Add credential checking and display correct error msg
        if (!username.equals("") && !password.equals("")){

            // Check username against database to see if its already been created
            DatabaseReference myRef = database.getReference("users");
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(username).exists()){
                        // Username exists
                        badUsername.setText("This username has been taken");
                    } else  {
                        // Username Doesn't exist
                        badUsername.setText("");
                        continueAccCreation(username, password, dob);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else {
            if (username.equals("")){
                // Display "must input username"
                badUsername.setText("Please enter a username!");
            }
            if (password.equals("")){
                // Display "must input password"
                badPassword.setText("Please enter a password!");
            }
        }

        /*
        System.out.println("username: " +  username);
        System.out.println("password: " +  password);
        System.out.println("dob: " +  dob);
        */

    }

    private void continueAccCreation(String thisUser, String thisPass, String thisDob) {
        DatabaseReference myRef = database.getReference("users");
        myRef.child(thisUser).child("dob").setValue(thisDob);
        myRef.child(thisUser).child("password").setValue(thisPass);
        myRef.child(thisUser).child("username").setValue(thisUser);
        toLogin();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }

    private void toLogin(){

        // Create intent for the Login Activity
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        TextView dateText = findViewById(R.id.selectedDOB);
        String dob;
        dob = day + "/" + (month+1) + "/" + year;
        dateText.setText(dob);
    }
}