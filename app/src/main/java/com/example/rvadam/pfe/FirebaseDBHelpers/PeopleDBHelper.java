package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.WorkSite.WorkSiteActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
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
    private final Context context;
    private DatabaseReference myPeopleRef;


    public PeopleDBHelper(String node, Context context) {
        this.myPeopleRef = FirebaseDatabase.getInstance().getReference(node);
        this.context = context;
    }

    // Write
    public void pushPeople(People people) {
        DatabaseReference newRef = myPeopleRef.push();
        people.setId(newRef.getKey());
        newRef.setValue(people).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i(TAG, "onComplete");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onCancelled");
            }
        });

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
        if (context instanceof WorkSiteActivity) {
            ((WorkSiteActivity) context).refreshListOfPeopleForWorksite();
        } else if (context instanceof ListPeopleActivity) {
            ((ListPeopleActivity) context).refreshListOfPeople();
        }

    }

}
