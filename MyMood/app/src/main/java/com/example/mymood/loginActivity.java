package com.example.mymood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    Button createAcc;
    Button login;


    private final String refURL = "https://mymood-84274-default-rtdb.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        createAcc = findViewById(R.id.accountCreation);
        createAcc.setOnClickListener((View v) -> createAccActivity());

        login = findViewById(R.id.login);
        login.setOnClickListener((View v) -> {
            //Firebase Stuff (Checks etc)
            EditText userNameBox = findViewById(R.id.username);
            String username = userNameBox.getText().toString();

            EditText passwordBox = findViewById(R.id.password);
            String password = passwordBox.getText().toString();

            if (!TextUtils.isEmpty(username)){
                FirebaseDatabase database = FirebaseDatabase.getInstance(refURL);
                DatabaseReference myRef = database.getReference(username);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String realPassword = dataSnapshot.getValue(String.class);
                        Log.d("DBPassword", "Value is: " + realPassword);

                        if (password.equals(realPassword)){
                            Log.d("Match", "The Username and password Match");

                            // Add code here to take to next page
                            // Also add code for remember User


                        } else {
                            Log.d("No Match", "The Username and password do not Match");

                            // Add code for correct error message to be shown
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("Login Error", "Failed to read value.", error.toException());
                    }
                });
            }
        });

    }

    public void createAccActivity() {

        // Create intent for the Create Account Activity
        Intent intent = new Intent(this, createAccountActivity.class);
        startActivity(intent);
    }

}