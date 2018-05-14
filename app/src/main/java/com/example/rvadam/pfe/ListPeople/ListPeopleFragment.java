package com.example.rvadam.pfe.ListPeople;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.People.PeopleActivity;
import com.example.rvadam.pfe.People.PeopleFragment;
import com.example.rvadam.pfe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 11/05/2018.
 */

public class ListPeopleFragment extends Fragment {
    private static final String TAG = "Get people from DB";

    private static People innerPeople;
    private FloatingActionButton buttonAdd;
    private ListView mListView;
    private FirebaseDatabase mDB;
    private DatabaseReference mRef;
    private ArrayList<People> list;
    private PeopleCustomAdapter adapter;
    private People people;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_people, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getActivity().findViewById(R.id.listViewPeople);
        buttonAdd = (FloatingActionButton) getActivity().findViewById(R.id.buttonAddPeople);

        people = new People();
        list = new ArrayList<People>();

        mDB = FirebaseDatabase.getInstance();
        mRef = mDB.getReference("people");

        // Create an array adapter for the list view
        adapter = new PeopleCustomAdapter(getActivity(), list);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    people = ds.getValue(People.class);
                    list.add(people);
                }
                mListView.setAdapter(adapter);

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        int itemPosition = i;
                        People peopleSelected = (People) mListView.getItemAtPosition(itemPosition);
                        PeopleActivity.setpeople(peopleSelected);
                        //Toast.makeText(getActivity(), peopleSelected.getFirstname().toString(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), PeopleActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}