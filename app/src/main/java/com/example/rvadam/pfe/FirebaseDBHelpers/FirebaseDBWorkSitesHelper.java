package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.util.Log;

import com.example.rvadam.pfe.Model.Constants;
import com.example.rvadam.pfe.Model.CurrentStatesWorkSites;
import com.example.rvadam.pfe.Model.WorkSite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.List;

/**
 * Created by rvadam on 12/05/2018.
 */

public class FirebaseDBWorkSitesHelper {

    private static final String TAG="DBWorkSiteHelper";

    public static void getListOfWorkSites(){
        FirebaseDatabase database= FirebaseDatabase.getInstance();

        //Flag for all worksites retrievement reset to false because we juste call the method for getting all the worksites
        Constants.getInstance().setListOfWorksitesRetrieveFromDB(false);
        //DB reference to the worksites
        DatabaseReference ref=database.getReference("workSites/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               List<WorkSite> listofWorksite= CurrentStatesWorkSites.getInstance().getCurrentWorkSites();
                Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
                Iterator<DataSnapshot> it = dataList.iterator();

                while (it.hasNext()){
                    //To change, just a test with one specific worksite reference
                    DataSnapshot snapshot=it.next();
                    String worksiteKey=snapshot.getKey();

                    if(worksiteKey.equals("-LBw-rNjtmo9G70LUU2Z")){
                        WorkSite workSite=snapshot.getValue(WorkSite.class);
                        //CurrentStatesWorkSites.getInstance().getCurrentWorkSites().add(workSite);
                        Log.i(TAG,"other doc getter "+workSite.getOtherDocuments().toString());
                        listofWorksite.add(workSite);
                    }

                }
                //We set the flag meaning that all the worksites have been retrived from db (falg is read by WorkSitesManager.getWorksiteById
                Constants.getInstance().setListOfWorksitesRetrieveFromDB(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
