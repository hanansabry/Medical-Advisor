package com.android.medicaladvisor.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.medicaladvisor.R;
import com.android.medicaladvisor.categories.SelectCategoryScreen;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize views
        final View welcomeLayout = findViewById(R.id.welcome_layout);
        final ProgressBar progressBar = findViewById(R.id.progress_bar);
        final Button startButton = findViewById(R.id.start_button);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                welcomeLayout.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);
            }
        },1000);
    }

    public void onStartClicked(View view) {
        startActivity(new Intent(this, SelectCategoryScreen.class));
    }
}
