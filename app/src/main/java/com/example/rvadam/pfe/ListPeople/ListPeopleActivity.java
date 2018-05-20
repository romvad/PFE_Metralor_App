package com.example.rvadam.pfe.ListPeople;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rvadam.pfe.FirebaseDBHelpers.CompanyDBHelper;
import com.example.rvadam.pfe.FirebaseDBHelpers.PeopleDBHelper;
import com.example.rvadam.pfe.FirebaseDBHelpers.RoleDBHelper;
import com.example.rvadam.pfe.R;

/**
 * Created by rdelfoss on 11/05/2018.
 */

public class ListPeopleActivity extends AppCompatActivity {
    private static final String TAG = "ListPeopleActivity";

    // Create an instance of ListPeopleFragment
    ListPeopleFragment listPeopleFragment = new ListPeopleFragment();
    PeopleDBHelper peopleDBHelper;
    CompanyDBHelper companyDBHelper;
    RoleDBHelper roleDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);

        // Call the diferents DBHelper that we need
        peopleDBHelper = new PeopleDBHelper("people/", this);
        peopleDBHelper.retrievePeople();

        companyDBHelper = new CompanyDBHelper("companies/", this);
        companyDBHelper.retrieveCompany();

        roleDBHelper = new RoleDBHelper("roles/", this);
        roleDBHelper.retrieveRole();

        // Check whether the activity is using the layout version with the fragment_container FrameLayout
        if (findViewById(R.id.listPeopleFragment_container) != null) {

            // If we're being restored from a previous state, then we don't need to do anything and should return
            if (savedInstanceState != null) {
                return;
            }

            // In case this activity was started with special instructions from an Intent, pass the Intent's extras to the fragment as arguments
            listPeopleFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.listPeopleFragment_container, listPeopleFragment).commit();
        }

    }

    public void refreshListOfPeople() {
        if (listPeopleFragment.getAdapter() != null) {
            listPeopleFragment.getAdapter().notifyDataSetChanged();
        }
    }

}