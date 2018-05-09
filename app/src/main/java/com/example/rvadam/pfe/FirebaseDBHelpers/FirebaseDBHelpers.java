package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by rvadam on 08/05/2018.
 */

public class FirebaseDBHelpers {

    private static final String TAG = "FirebaseDBHelpers";

    public static boolean updateWorkSiteListOfDocuments(String workSiteID, String typeOfDoc, String key, final String value) {
        final boolean[] res = {false};
        //we get the ref to the worksites node
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("workSites");

        //we prepare the object to add or update on FIrebase DB
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(key, value);

        final DatabaseReference docRef = myRef.child(workSiteID + "/" + typeOfDoc);

        docRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                docRef.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i(TAG, "doc " + value + " update onComplete");
                        res[0] = true;

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "doc " + value + " update failure");
                    }
                });
                ;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        return res[0];

    }
}
