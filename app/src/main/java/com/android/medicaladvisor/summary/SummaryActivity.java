package com.android.medicaladvisor.summary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.medicaladvisor.DiagnosisSharedPreferences;
import com.android.medicaladvisor.Injection;
import com.android.medicaladvisor.R;
import com.android.medicaladvisor.intro.IntroActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    private DiagnosisSharedPreferences diagnosisSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        diagnosisSharedPreferences = Injection.provideDiagnosisSharedPref(sharedPreferences);
        initializeViews();
    }

    private void initializeViews() {
        TextView diagnosisTextView = findViewById(R.id.diagnosis_textview);
        TextView regionTextView = findViewById(R.id.region_textview);
        TextView medicalCenterTextView = findViewById(R.id.medical_center_textview);

        diagnosisTextView.setText(diagnosisSharedPreferences.getDiagnosisName());
        regionTextView.setText(diagnosisSharedPreferences.getRegionName());
        medicalCenterTextView.setText(diagnosisSharedPreferences.getSelectedMedicalCenterName());
    }

    public void onBackClicked(View view) {
        finish();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
