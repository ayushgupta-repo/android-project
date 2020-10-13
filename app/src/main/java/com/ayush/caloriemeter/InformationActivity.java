package com.ayush.caloriemeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class InformationActivity extends AppCompatActivity {

    private TextView SPEED;
    private TextView INTENSITY;
    private TextView OUTPUT;
    private long weight;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docRef = db.collection("Details").document("User01");

    private static final String TAG = "InformationActivity";
    private static final String KEY_FOOTSTEPS = "footsteps";
    private static final String KEY_SPEED = "speed";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Intent retrieve = getIntent();

        String WEIGHT = retrieve.getStringExtra(MainActivity.WEIGHT);

        weight = Long.parseLong(WEIGHT);

        SPEED = findViewById(R.id.speed);
        INTENSITY = findViewById(R.id.intensity);
        OUTPUT = findViewById(R.id.output);

        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            String speed = documentSnapshot.getString(KEY_SPEED);
                            String footsteps = documentSnapshot.getString(KEY_FOOTSTEPS);

                            long s = Long.parseLong(speed);
                            long MET = (long) (0.85*s);
                            long calories = (long) (0.0715 * MET * weight);
                            
                            String i = Long.toString(MET);
                            String c = Long.toString(calories);
                            
                            SPEED.setText(speed);
                            INTENSITY.setText(i);
                            OUTPUT.setText(c);
                        }
                        else
                        {
                            Toast.makeText(InformationActivity.this, "Document doesn't exists...", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InformationActivity.this, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}
