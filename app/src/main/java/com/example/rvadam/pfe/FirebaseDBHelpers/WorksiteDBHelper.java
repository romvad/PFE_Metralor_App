package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rvadam.pfe.ListWorkSites.ListWorkSiteActivity;
import com.example.rvadam.pfe.Model.CurrentStatesWorksitesList;
import com.example.rvadam.pfe.Model.WorkSite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rdelfoss on 17/05/2018.
 */

public class WorksiteDBHelper {
    private static final String TAG = "WorksiteDBHelper";
    private DatabaseReference myWorksitesRef;

    private static ListWorkSiteActivity listWorkSiteActivity;

    public WorksiteDBHelper(String node, ListWorkSiteActivity listWorkSiteActivity) {
        this.myWorksitesRef = FirebaseDatabase.getInstance().getReference(node);
        WorksiteDBHelper.listWorkSiteActivity = listWorkSiteActivity;
    }

    // Write
    public void pushWorksite(WorkSite workSite) {
        DatabaseReference newRef = myWorksitesRef.push();
        workSite.setId(newRef.getKey());
        newRef.setValue(workSite).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    // Update
    public void updateVIC(WorkSite workSite, long vic){
        DatabaseReference databaseReference = myWorksitesRef.child(workSite.getId());
        Map<String,Object> childMap= new HashMap<String, Object>();
        childMap.put("dateVIC",vic);
        databaseReference.updateChildren(childMap);
    }

    // Read
    public void retrieveWorksites() {
        myWorksitesRef.addValueEventListener(new ValueEventListener() {
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
        List<WorkSite> listWorksites = currentStatesWorksitesList.getCurrentWorksitesList();

        Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
        listWorksites.clear();

        for (DataSnapshot snapshot : dataList) {
            WorkSite workSite = snapshot.getValue(WorkSite.class);
            listWorksites.add(workSite);

        }
        listWorkSiteActivity.refreshListOfWorksites();
    }

}
