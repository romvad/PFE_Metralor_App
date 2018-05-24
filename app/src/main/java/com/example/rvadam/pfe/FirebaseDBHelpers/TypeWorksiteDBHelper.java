package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.util.Log;

import com.example.rvadam.pfe.AddWorksite.AddWorksiteFragment;
import com.example.rvadam.pfe.Model.CurrentStatesWorksitesList;
import com.example.rvadam.pfe.Model.Type;
import com.example.rvadam.pfe.Model.WorkSite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 17/05/2018.
 */

public class TypeWorksiteDBHelper {
    private static final String TAG = "TypeWorksiteDBHelper";
    private DatabaseReference myTypeRef;

    private static AddWorksiteFragment addWorksiteFragment;

    public TypeWorksiteDBHelper(String node, AddWorksiteFragment addWorksiteFragment) {
        this.myTypeRef = FirebaseDatabase.getInstance().getReference(node);
        TypeWorksiteDBHelper.addWorksiteFragment = addWorksiteFragment;
    }

    // Read
    public void retrieveType() {
        myTypeRef.addValueEventListener(new ValueEventListener() {
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
        CurrentStatesWorksitesList currentStatesWorksitesList = CurrentStatesWorksitesList.getInstance();
        ArrayList<String> wTypes = (ArrayList<String>) currentStatesWorksitesList.getTypes();

        Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
        wTypes.clear();

        for (DataSnapshot snapshot : dataList) {
            Type type = snapshot.getValue(Type.class);
            assert type != null;
            wTypes.add(type.getId());
        }
        addWorksiteFragment.setMySpinnerAdapter();
    }

    public void pushDBRefToGetId(WorkSite wCreate) {
        DatabaseReference newRef = myTypeRef.push();
        wCreate.setId(newRef.getKey());
    }
}

