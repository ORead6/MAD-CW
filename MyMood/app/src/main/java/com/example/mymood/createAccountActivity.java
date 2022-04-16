package com.example.mymood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class createAccountActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton confirmAccCreation;
        TextView dobBtn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        confirmAccCreation = findViewById(R.id.createAccount_back);
        confirmAccCreation.setOnClickListener((View v) -> toLogin());

        dobBtn = findViewById(R.id.dobBtn);
        dobBtn.setOnClickListener((View v) -> showDatePickerDialog());

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