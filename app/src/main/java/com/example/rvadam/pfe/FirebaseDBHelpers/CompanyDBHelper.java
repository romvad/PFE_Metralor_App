package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.content.Context;
import android.util.Log;

import com.example.rvadam.pfe.AddPeople.AddPeopleActivity;
import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.Model.Company;
import com.example.rvadam.pfe.Model.CurrentStatesCompaniesList;
import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
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

public class CompanyDBHelper {
    private static final String TAG = "CompanyDBHelper";
    private final Context context;
    private DatabaseReference myCompanyRef;

    public CompanyDBHelper(String node, Context context) {
        this.myCompanyRef = FirebaseDatabase.getInstance().getReference(node);
        this.context = context;
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

        CurrentStatesCompaniesList currentStatesCompaniesList = CurrentStatesCompaniesList.getInstance();
        List<Company> listCompanies = currentStatesCompaniesList.getCurrentCompaniesList();

        Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
        mapCompanies.clear();
        listCompanies.clear();

        for (DataSnapshot snapshot : dataList) {
            Company company = snapshot.getValue(Company.class);
            assert company != null;
            listCompanies.add(company);
            mapCompanies.put(company.getId(), company.getName());
        }
        if (context instanceof ListPeopleActivity) {
            ((ListPeopleActivity) context).refreshListOfPeople();
        } else if (context instanceof AddPeopleActivity) {
            ((AddPeopleActivity) context).refresh();
        }
    }

}
