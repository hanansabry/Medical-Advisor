package com.android.medicaladvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SymptomDetailsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_details);
    }

    public void onBackClicked(View view) {
        onBackPressed();
    }

    public void onNextClicked(View view) {
        startActivity(new Intent(this, InitialDiagnosis.class));
    }
}
