package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.util.Log;

import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.People;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.List;

/**
 * Created by rdelfoss on 14/05/2018.
 */

public class PeopleDBHelper {

    private static ListPeopleActivity listPeopleActivity;
    private static final String TAG = "PeopleDBHelper";

    DatabaseReference mRef;

    public PeopleDBHelper(DatabaseReference mRef, ListPeopleActivity listPeopleActivity) {
        this.mRef = mRef;
        this.listPeopleActivity = listPeopleActivity;
    }

    // Read
    public void retrievePeople() {
        mRef.addValueEventListener(new ValueEventListener() {
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
        List<People> listPeople = CurrentStatesPeopleList.getInstance().getCurrentPeopleList();

        Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
        Iterator<DataSnapshot> it = dataList.iterator();

        while (it.hasNext()) {
            DataSnapshot snapshot = it.next();
            People people = snapshot.getValue(People.class);
            listPeople.add(people);
        }
        listPeopleActivity.refreshListOfPeople();
    }

}
