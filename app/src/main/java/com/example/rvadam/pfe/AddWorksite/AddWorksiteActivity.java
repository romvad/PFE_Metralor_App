package com.example.rvadam.pfe.AddWorksite;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rvadam.pfe.R;

/**
 * Created by rdelfoss on 09/05/2018.
 */

public class AddWorksiteActivity extends AppCompatActivity {
    private static final String TAG = "AddWorksiteActivity";

    // Create an instance of AddWorksiteFragment
    AddWorksiteFragment addWorksiteFragment = new AddWorksiteFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worksite);

        // Check whether the activity is using the layout version with the fragment_container FrameLayout
        if (findViewById(R.id.addWorksiteFragment_container) != null) {

            // If we're being restored from a previous state, then we don't need to do anything and should return
            if (savedInstanceState != null) {
                return;
            }

            // In case this activity was started with special instructions from an Intent, pass the Intent's extras to the fragment as arguments
            addWorksiteFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.addWorksiteFragment_container, addWorksiteFragment).commit();
        }

    }


}
