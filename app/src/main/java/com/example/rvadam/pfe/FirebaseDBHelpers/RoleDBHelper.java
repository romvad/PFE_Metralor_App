package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.util.Log;

import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.Role;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 * Created by rdelfoss on 16/05/2018.
 */

public class RoleDBHelper {
    private static final String TAG = "RoleDBHelper";
    private DatabaseReference myRoleRef;

    private static ListPeopleActivity listPeopleActivity;

    public RoleDBHelper(String node, ListPeopleActivity listPeopleActivity) {
        this.myRoleRef = FirebaseDatabase.getInstance().getReference(node);
        RoleDBHelper.listPeopleActivity = listPeopleActivity;
    }

    // Read
    public void retrieveRole() {
        myRoleRef.addValueEventListener(new ValueEventListener() {
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
        Map<String, String> mapRoles = currentStatesPeopleList.getRolesMap();

        Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();

        for (DataSnapshot snapshot : dataList) {
            Role role = snapshot.getValue(Role.class);
            //Log.i(TAG, "Role name : " + Role.getTitle());
            assert role != null;
            mapRoles.put(role.getId(), role.getTitle());
        }
        listPeopleActivity.refreshListOfPeople();
    }

}

