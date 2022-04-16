package com.example.mymood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class loginActivity extends AppCompatActivity {

    Button createAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        createAcc = (Button) findViewById(R.id.accountCreation);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccActivity();
            }
        });
    }

    public void createAccActivity() {

        // Create itent for the Create Account Acitivity
        Intent intent = new Intent(this, createAccountActivity.class);
        startActivity(intent);
    }

}