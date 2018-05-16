package com.example.rvadam.pfe.ListPeople;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rvadam.pfe.FirebaseDBHelpers.PeopleDBHelper;
import com.example.rvadam.pfe.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rdelfoss on 11/05/2018.
 */

public class ListPeopleActivity extends AppCompatActivity {
    // Create an instance of ListPeopleFragment
    ListPeopleFragment listPeopleFragment = new ListPeopleFragment();
    private FirebaseDatabase mDB;
    private DatabaseReference mRef;
    PeopleDBHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);

        mDB = FirebaseDatabase.getInstance();
        mRef = mDB.getReference("people/");
        helper = new PeopleDBHelper(mRef, this);
        helper.retrievePeople();

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.listPeopleFragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            listPeopleFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.listPeopleFragment_container, listPeopleFragment).commit();
        }

    }

    public void refreshListOfPeople() {
        if (listPeopleFragment.getAdapter() != null) {
            listPeopleFragment.getAdapter().notifyDataSetChanged();
        }
    }

}