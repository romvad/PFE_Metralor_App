package com.example.rvadam.pfe.ListWorkSites;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rvadam.pfe.FirebaseDBHelpers.WorksiteDBHelper;
import com.example.rvadam.pfe.R;

/**
 * Created by rdelfoss on 04/05/2018.
 */

public class ListWorkSiteActivity extends AppCompatActivity {
    private static final String TAG = "ListWorkSiteActivity";

    // Create an instance of ListPeopleFragment
    ListWorkSiteFragment listWorkSiteFragment = new ListWorkSiteFragment();
    WorksiteDBHelper worksiteDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_worksites);

        // Call the diferents DBHelper that we need
        worksiteDBHelper = new WorksiteDBHelper("workSites/", this);
        worksiteDBHelper.retrieveWorksites();

        // Check whether the activity is using the layout version with the fragment_container FrameLayout
        if (findViewById(R.id.listWorksiteFragment_container) != null) {

            // If we're being restored from a previous state, then we don't need to do anything and should return
            if (savedInstanceState != null) {
                return;
            }

            // In case this activity was started with special instructions from an Intent, pass the Intent's extras to the fragment as arguments
            listWorkSiteFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.listWorksiteFragment_container, listWorkSiteFragment).commit();
        }

    }

    public void refreshListOfWorksites() {
        if (listWorkSiteFragment.getAdapter() != null) {
            listWorkSiteFragment.getAdapter().notifyDataSetChanged();
        }
    }

}