package com.example.rvadam.pfe.People;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rvadam.pfe.R;

/**
 * Created by rdelfoss on 13/05/2018.
 */

public class PeopleActivity extends AppCompatActivity {
    private static final String TAG = "PeopleActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        if (findViewById(R.id.peopleFragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            PeopleFragment peopleFragment = new PeopleFragment();
            peopleFragment.setArguments(getIntent().getExtras());
            ft.add(R.id.peopleFragment_container, peopleFragment).commit();
        }

    }
}
