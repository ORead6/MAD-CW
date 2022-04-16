package com.example.mymood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class createAccountActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ImageButton confirmAccCreation;
    private TextView dateText;
    private TextView dobBtn;
    private String dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        dateText = findViewById(R.id.selectedDOB);

        confirmAccCreation = (ImageButton) findViewById(R.id.createAccount_back);
        confirmAccCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });

        dobBtn = (TextView) findViewById(R.id.dobBtn);
        dobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });


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
        dob = day + "/" + (month+1) + "/" + year;
        dateText.setText(dob);
    }
}