package com.android.medicaladvisor.initialdiagnosis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.medicaladvisor.Injection;
import com.android.medicaladvisor.R;
import com.android.medicaladvisor.backend.DiagnosisRepository;
import com.android.medicaladvisor.medicalcenters.ShowAvailableMedicalCenters;
import com.android.medicaladvisor.models.Diagnosis;

import androidx.appcompat.app.AppCompatActivity;

public class InitialDiagnosis extends AppCompatActivity implements DiagnosisRepository.DiagnosisCallback {

    private InitialDiagnosisPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_diagnosis);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        presenter = new InitialDiagnosisPresenter(Injection.provideDiagnosisRepository(), Injection.provideDiagnosisSharedPref(sharedPreferences));
        presenter.detectDiagnosis(this);
        setComplaintSummaryContents();
    }

    private void setComplaintSummaryContents() {
        TextView categoryTextView = findViewById(R.id.category_textview);
        TextView initialSymptomTextView = findViewById(R.id.initial_symptoms_textview);
        TextView symptomDetailTextView = findViewById(R.id.symptom_detail_textview);

        categoryTextView.setText(String.format(getString(R.string.speciality_value), presenter.getSelectedCategoryName()));
        initialSymptomTextView.setText(String.format(getString(R.string.initial_symptom_value), presenter.getSelectedInitialSymptomName()));
        symptomDetailTextView.setText(String.format(getString(R.string.symptom_detail_value), presenter.getSelectedSymptomDetailName()));
    }

    public void onBackClicked(View view) {
        onBackPressed();
    }

    public void onNextClicked(View view) {
        startActivity(new Intent(this, ShowAvailableMedicalCenters.class));
    }

    @Override
    public void onDiagnosisDetected(Diagnosis diagnosis) {
        TextView diagnosisTextView = findViewById(R.id.diagnosis_textview);
        diagnosisTextView.setText(diagnosis.getName());
        presenter.saveDiagnosis(diagnosis);
    }

    @Override
    public void onDiagnosisDetectedFailed(String errmsg) {
        Toast.makeText(this, errmsg, Toast.LENGTH_LONG).show();
    }
}
