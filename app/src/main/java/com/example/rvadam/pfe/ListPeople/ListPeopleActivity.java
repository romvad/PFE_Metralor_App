package com.example.rvadam.pfe.ListPeople;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rvadam.pfe.People.PeopleFragment;
import com.example.rvadam.pfe.R;

/**
 * Created by rdelfoss on 11/05/2018.
 */

public class ListPeopleActivity extends AppCompatActivity implements ListPeopleFragment.OnPeopleSelectedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.listPeopleFragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ListPeopleFragment
            ListPeopleFragment listPeopleFragment = new ListPeopleFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            listPeopleFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.listPeopleFragment_container, listPeopleFragment).commit();
        }

    }

    @Override
    public void onPeopleSelected(int position) {
        // The user selected the Person from the ListPeopleFragment

        // Capture the article fragment from the activity layout
        PeopleFragment peopleFragment = (PeopleFragment) getFragmentManager().findFragmentById(R.id.azerty);

        if (peopleFragment != null) {
            // If peopleFragment is available, we're in two-pane layout...

            // Call a method in the PeopleFragment to update its content
            peopleFragment.updatePeopleView(position);
        } else {
            // Otherwise, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected person
            PeopleFragment newFragment = new PeopleFragment();
            Bundle args = new Bundle();
            args.putInt(PeopleFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.peopleFragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
    }
}