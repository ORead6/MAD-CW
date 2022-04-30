package com.example.mymood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class moodSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_selector);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            System.out.println(extras.getString("users"));
        }
    }
}