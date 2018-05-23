package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.content.Context;
import android.util.Log;

import com.example.rvadam.pfe.AddPeople.AddPeopleActivity;
import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.Model.CurrentStateRolesList;
import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.Role;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

/**
 * Created by rdelfoss on 16/05/2018.
 */

public class RoleDBHelper {
    private static final String TAG = "RoleDBHelper";
    private final Context context;
    private DatabaseReference myRoleRef;

    public RoleDBHelper(String node, Context context) {
        this.myRoleRef = FirebaseDatabase.getInstance().getReference(node);
        this.context = context;
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

        CurrentStateRolesList currentStateRolesList = CurrentStateRolesList.getInstance();
        List<Role> listRoles = currentStateRolesList.getCurrentRolesList();

        Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
        mapRoles.clear();
        listRoles.clear();

        for (DataSnapshot snapshot : dataList) {
            Role role = snapshot.getValue(Role.class);
            assert role != null;
            listRoles.add(role);
            mapRoles.put(role.getId(), role.getTitle());
        }
        if (context instanceof ListPeopleActivity) {
            ((ListPeopleActivity) context).refreshListOfPeople();
        } else if (context instanceof AddPeopleActivity) {
            ((AddPeopleActivity) context).refresh();
        }
    }

}

