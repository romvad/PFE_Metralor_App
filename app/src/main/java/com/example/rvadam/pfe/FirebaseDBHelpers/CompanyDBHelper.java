package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.util.Log;

import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.Model.Company;
import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 * Created by rdelfoss on 16/05/2018.
 */

public class CompanyDBHelper {
    private static final String TAG = "CompanyDBHelper";
    private DatabaseReference myCompanyRef;

    private static ListPeopleActivity listPeopleActivity;

    public CompanyDBHelper(String node, ListPeopleActivity listPeopleActivity) {
        this.myCompanyRef = FirebaseDatabase.getInstance().getReference(node);
        CompanyDBHelper.listPeopleActivity = listPeopleActivity;
    }

    // Read
    public void retrieveCompany() {
        myCompanyRef.addValueEventListener(new ValueEventListener() {
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
        Map<String, String> mapCompanies = currentStatesPeopleList.getCompaniesMap();

        Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();

        for (DataSnapshot snapshot : dataList) {
            Company company = snapshot.getValue(Company.class);
            //Log.i(TAG, "Company name : " + company.getName());
            assert company != null;
            mapCompanies.put(company.getId(), company.getName());
        }
        listPeopleActivity.refreshListOfPeople();
    }

}
