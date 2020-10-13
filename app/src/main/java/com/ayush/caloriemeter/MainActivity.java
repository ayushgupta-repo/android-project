package com.ayush.caloriemeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText EditText1;
    public static final String WEIGHT = "com.ayush.caloriemeter.WEIGHT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View view){

        EditText1 = findViewById(R.id.weight);

        long weight = Long.parseLong(EditText1.getText().toString());

        Intent showInformation = new Intent(MainActivity.this, InformationActivity.class);

        String nextMessage1 = Long.toString(weight);

        showInformation.putExtra(WEIGHT, nextMessage1);

        startActivity(showInformation);
    }
}
