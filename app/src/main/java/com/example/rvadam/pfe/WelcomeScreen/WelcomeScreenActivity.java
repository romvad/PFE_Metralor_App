package com.example.rvadam.pfe.WelcomeScreen;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rvadam.pfe.R;

/**
 * Created by rdelfoss on 09/05/2018.
 */

public class WelcomeScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        if (findViewById(R.id.welcomeScreenFragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            WelcomeScreenFragment welcomeScreenFragment = new WelcomeScreenFragment();
            welcomeScreenFragment.setArguments(getIntent().getExtras());
            ft.add(R.id.welcomeScreenFragment_container, welcomeScreenFragment).commit();
        }

    }
}
