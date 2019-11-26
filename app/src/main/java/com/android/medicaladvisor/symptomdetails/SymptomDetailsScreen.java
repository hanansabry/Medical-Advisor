package com.android.medicaladvisor.symptomdetails;

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
import com.android.medicaladvisor.initialdiagnosis.InitialDiagnosis;
import com.android.medicaladvisor.models.SymptomDetail;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class SymptomDetailsScreen extends AppCompatActivity implements RetrievingRepository.RetrievingRepositoryCallback<SymptomDetail> {

    private SymptomDetailsPresenter presenter;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_details);

        nextButton = findViewById(R.id.next_button);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        presenter = new SymptomDetailsPresenter(Injection.provideSymptomDetailsRepository(), Injection.provideDiagnosisSharedPref(sharedPreferences));
        String selectedInitialSymptomId = presenter.getSelectedInitialSymptomId();
        presenter.retrieveSymptomDetailsForSelectedInitialSymptom(selectedInitialSymptomId, this);
    }

    public void onBackClicked(View view) {
        onBackPressed();
    }

    public void onNextClicked(View view) {
        startActivity(new Intent(this, InitialDiagnosis.class));
    }

    @Override
    public void onRetrievingDataSuccessfully(final ArrayList<SymptomDetail> list) {
        ArrayAdapter<String> symptomDetailsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1);
        for (SymptomDetail symptomDetail : list) {
            symptomDetailsAdapter.add(symptomDetail.getName());
        }

        Spinner symptomDetailsSpinner = findViewById(R.id.symptoms_details_spinner);
        symptomDetailsSpinner.setAdapter(symptomDetailsAdapter);
        symptomDetailsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SymptomDetail symptomDetail = list.get(position);
                presenter.setDiagnosisSymptomDetail(symptomDetail);
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
