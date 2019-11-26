package com.android.medicaladvisor.medicalcenters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.medicaladvisor.Injection;
import com.android.medicaladvisor.R;
import com.android.medicaladvisor.backend.MedicalCentersRepository;
import com.android.medicaladvisor.backend.RetrievingRepository;
import com.android.medicaladvisor.models.MedicalCenter;
import com.android.medicaladvisor.models.Person;
import com.android.medicaladvisor.models.Region;
import com.android.medicaladvisor.summary.SummaryActivity;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowAvailableMedicalCenters extends AppCompatActivity implements MedicalCentersRepository.MedicalCentersCallback {

    private MedicalCentersPresenter presenter;
    private EditText civilIdEditText;
    private TextView regionTextView;
    private MedicalCentersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_medical_centers);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        presenter = new MedicalCentersPresenter(
                this,
                Injection.providePersonsRepository(),
                Injection.provideMedicalCentersRepository(),
                Injection.provideRegionsRepository(),
                Injection.provideDiagnosisSharedPref(sharedPreferences)
        );

        civilIdEditText = findViewById(R.id.civil_id_edittext);
        regionTextView = findViewById(R.id.region_textview);
        setupMedicalCentersRecyclerView();
    }

    public void onSearchClicked(View view) {
        regionTextView.setText("");
        adapter.bindMedicalCenters(new ArrayList<MedicalCenter>());
        String civilId = civilIdEditText.getText().toString();
        if (civilId.isEmpty()) {
            civilIdEditText.setError("Please enter Civil Id");
        } else {
            presenter.getPersonByCivilId(civilId, new RetrievingRepository.RetrievingRepositoryCallback<Person>() {
                @Override
                public void onRetrievingDataSuccessfully(ArrayList<Person> list) {
                    Person person = list.get(0);
                    presenter.getRegionById(String.valueOf(person.getRegionId()), new RetrievingRepository.RetrievingRepositoryCallback<Region>() {
                        @Override
                        public void onRetrievingDataSuccessfully(ArrayList<Region> list) {
                            Region region = list.get(0);
                            presenter.setRegionName(region.getName());
                            regionTextView.setText(String.format(getString(R.string.region_name_value), region.getName()));
                        }

                        @Override
                        public void onRetrievingDataFailed(String errmsg) {
                            regionTextView.setText("");
                            Toast.makeText(ShowAvailableMedicalCenters.this, errmsg, Toast.LENGTH_LONG).show();
                        }
                    });
                    presenter.getAvailableMedicalCentersInRegion(person.getRegionId(), ShowAvailableMedicalCenters.this);
                }

                @Override
                public void onRetrievingDataFailed(String errmsg) {
                    Toast.makeText(ShowAvailableMedicalCenters.this, errmsg, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void setupMedicalCentersRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.available_centers_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicalCentersAdapter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRetrievingAvailableMedicalCenters(ArrayList<MedicalCenter> list) {
        adapter.bindMedicalCenters(list);
    }

    @Override
    public void onRetrievingMedicalCentersFailed(String errmsg) {
        Toast.makeText(this, errmsg, Toast.LENGTH_LONG).show();
    }

    public void onMedicalCenterClicked(MedicalCenter medicalCenter) {
        presenter.setSelectedMedicalCenterName(medicalCenter.getName());
        startActivity(new Intent(this, SummaryActivity.class));
    }

    public void setPresenter(MedicalCentersPresenter medicalCentersPresenter) {
        presenter = medicalCentersPresenter;
    }

    public void onBackClicked(View view) {
        onBackPressed();
    }
}
