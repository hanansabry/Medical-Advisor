package com.android.medicaladvisor.initialsymptoms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.medicaladvisor.Injection;
import com.android.medicaladvisor.R;
import com.android.medicaladvisor.backend.RetrievingRepository;
import com.android.medicaladvisor.models.InitialSymptom;
import com.android.medicaladvisor.symptomdetails.SymptomDetailsScreen;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class InitialSymptomScreen extends AppCompatActivity implements RetrievingRepository.RetrievingRepositoryCallback<InitialSymptom> {

    private InitialSymptomPresenter presenter;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_symptom);

        nextButton = findViewById(R.id.next_button);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        presenter = new InitialSymptomPresenter(Injection.provideInitialSymptomsRepository(), Injection.provideDiagnosisSharedPref(sharedPreferences));
        String categoryId = presenter.getSelectedCategoryId();
        presenter.getInitialSymptomsForSelectedCategory(categoryId, this);
    }

    public void onBackClicked(View view) {
        onBackPressed();
    }

    public void onNextClicked(View view) {
        startActivity(new Intent(this, SymptomDetailsScreen.class));
    }

    @Override
    public void onRetrievingDataSuccessfully(final ArrayList<InitialSymptom> list) {
        ArrayAdapter<String> initialSymptomsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1);
        for (InitialSymptom symptom : list) {
            initialSymptomsAdapter.add(symptom.getName());
        }

        Spinner initialSymptomsSpinner = findViewById(R.id.initial_symptoms_spinner);
        initialSymptomsSpinner.setAdapter(initialSymptomsAdapter);
        initialSymptomsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InitialSymptom selectedSymptom = list.get(position);
                presenter.setDiagnosisInitialSymptom(selectedSymptom);
                nextButton.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onRetrievingDataFailed(String errmsg) {
        Toast.makeText(this, errmsg, Toast.LENGTH_LONG).show();
    }
}
