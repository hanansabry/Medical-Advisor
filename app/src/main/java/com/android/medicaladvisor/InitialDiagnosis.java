package com.android.medicaladvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InitialDiagnosis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_diagnosis);
    }

    public void onBackClicked(View view) {
        onBackPressed();
    }

    public void onNextClicked(View view) {
        startActivity(new Intent(this, ShowMedicalCenters.class));
    }
}
