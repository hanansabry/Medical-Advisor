package com.android.medicaladvisor.categories;

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
import com.android.medicaladvisor.initialsymptoms.InitialSymptomScreen;
import com.android.medicaladvisor.models.Category;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class SelectCategoryScreen extends AppCompatActivity implements RetrievingRepository.RetrievingRepositoryCallback<Category> {

    private CategoriesPresenter presenter;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        nextButton = findViewById(R.id.next_button);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        presenter = new CategoriesPresenter(Injection.provideCategoriesRepository(), Injection.provideDiagnosisSharedPref(sharedPreferences));
        presenter.retrieveCategories(this);
    }

    private void initializeCategoriesSpinner(final ArrayList<Category> categories) {
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1);
        for (Category category : categories) {
            categoriesAdapter.add(category.getName());
        }
        Spinner categoriesSpinner = findViewById(R.id.categories_spinner);
        categoriesSpinner.setAdapter(categoriesAdapter);
        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category selectedCategory = categories.get(position);
                presenter.setDiagnosisCategory(selectedCategory);
                nextButton.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onBackClicked(View view) {
        onBackPressed();
    }

    public void onNextClicked(View view) {
        startActivity(new Intent(this, InitialSymptomScreen.class));
    }

    @Override
    public void onRetrievingDataSuccessfully(ArrayList<Category> list) {
        initializeCategoriesSpinner(list);
    }

    @Override
    public void onRetrievingDataFailed(String errmsg) {
        Toast.makeText(this, errmsg, Toast.LENGTH_LONG).show();
    }
}
