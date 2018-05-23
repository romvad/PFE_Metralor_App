package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.util.Log;

import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.WorkSite.WorkSiteActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by rdelfoss on 14/05/2018.
 */

public class PeopleDBHelper {
    private static final String TAG = "PeopleDBHelper";
    private DatabaseReference myPeopleRef;

    private static ListPeopleActivity listPeopleActivity;
    private static WorkSiteActivity workSiteActivity;

    public PeopleDBHelper(String node, ListPeopleActivity listPeopleActivity) {
        this.myPeopleRef = FirebaseDatabase.getInstance().getReference(node);
        PeopleDBHelper.listPeopleActivity = listPeopleActivity;
    }

    public PeopleDBHelper(String people, WorkSiteActivity workSiteActivity) {
        this.myPeopleRef = FirebaseDatabase.getInstance().getReference(people);
        PeopleDBHelper.workSiteActivity = workSiteActivity;
    }

    // Read
    public void retrievePeopleBis() {
        myPeopleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchDataBis(dataSnapshot);
                Log.i(TAG, "azertyuiopqsdfghjklm");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

    }

    private void fetchDataBis(DataSnapshot dataSnapshot) {
        CurrentStatesPeopleList currentStatesPeopleList = CurrentStatesPeopleList.getInstance();
        List<People> listPeople = currentStatesPeopleList.getCurrentPeopleList();

        Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
        listPeople.clear();

        for (DataSnapshot snapshot : dataList) {
            People people = snapshot.getValue(People.class);
            listPeople.add(people);

        }
        workSiteActivity.refreshListOfPeopleForWorksite();
    }

    // Read
    public void retrievePeople() {
        myPeopleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

    }

    private void fetchData(DataSnapshot dataSnapshot) {
        CurrentStatesPeopleList currentStatesPeopleList = CurrentStatesPeopleList.getInstance();
        List<People> listPeople = currentStatesPeopleList.getCurrentPeopleList();

        Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
        listPeople.clear();

        for (DataSnapshot snapshot : dataList) {
            People people = snapshot.getValue(People.class);
            listPeople.add(people);

        }
        listPeopleActivity.refreshListOfPeople();
    }

}
