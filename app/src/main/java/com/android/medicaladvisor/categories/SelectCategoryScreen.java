package com.android.medicaladvisor.categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.medicaladvisor.R;
import com.android.medicaladvisor.initialsymptoms.InitialSymptomScreen;

import androidx.appcompat.app.AppCompatActivity;

public class SelectCategoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

    public void onBackClicked(View view) {
        onBackPressed();
    }

    public void onNextClicked(View view) {
        startActivity(new Intent(this, InitialSymptomScreen.class));
    }
}
