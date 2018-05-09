package com.example.rvadam.pfe.operationTests;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rvadam.pfe.Model.WorkSite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by rvadam on 07/05/2018.
 */

public class testFirebaseDB {

    private static final String TAG="testFirebaseDB";

    public static void testInsertion(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("workSites");


        /*//test with all fields filled
        List<String> employees=new ArrayList<>();
        Date date=new Date();
        //Timestamp time=new Timestamp(date.getTime());
        employees.add("MLKJ");
        employees.add("azertyui");
        Map<String,String> otherDocs=new HashMap<String, String>();
        otherDocs.put("carteAmers", "amers.pdf ");
        otherDocs.put("etudeSol" , "sol.pdf ");
        otherDocs.put("ficheLoc" , " ");
        otherDocs.put("noteCalcul" , "");
        otherDocs.put("predimMassif" , " ");

        Map<String,String> secuDocs=new HashMap<String, String>();
        secuDocs.put("APD" ," ");
        secuDocs.put("PGC" , " ");
        secuDocs.put("VTDI" , " ");

        Map<String,String> planDocs=new HashMap<String, String>();
        planDocs.put("APD" ," ");
        planDocs.put("APS" , " ");
        planDocs.put("Serrurerie" , " ");

        Map<String,String> ppspsDocs=new HashMap<String, String>();

        //myRef.setValue("Hello, World!");
        //mDatabase.child("numbers").push().setValue(1);
        WorkSite w1=new WorkSite(date.getTime(),employees,46.9,7.8,"thechantier",otherDocs,planDocs,secuDocs,ppspsDocs,"T1");
        //Log.i(TAG,"ajotu fire db "+mDatabase.child("\"workSites\""));
        DatabaseReference newRef=myRef.push();
        w1.setId(newRef.getKey());
        newRef.setValue(w1).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                Log.i(TAG, "onComplete");
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Log.i(TAG, "onCancelled");
            }
        });*/

        //Test update worksite
        //WorkSite w2=null;
        //myRef.child("-LBw-rNjtmo9G90LUU2Z").addListenerForSingleValueEvent(new ValueEventListener() {
        /*final DatabaseReference refOtherDocs=myRef.child("-LBw-rNjtmo9G90LUU2Z/otherDocuments");
        refOtherDocs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //WorkSite w2= dataSnapshot.getValue(WorkSite.class);
                //Log.i(TAG,w2.getOtherDocuments().get("ficheLoc"));
                //w2.getOtherDocuments().put("ficheLoc","mlkjhgf.pdf");
                //Log.i(TAG,dataSnapshot.toString());
                Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
                Iterator<DataSnapshot> it = dataList.iterator();
                while (it.hasNext()) {
                    DataSnapshot snap=it.next();
                    String keyStr=snap.getKey();
                    String str= snap.getValue(String.class);
                    Log.i(TAG,keyStr+" "+str);

                    if(keyStr.equals("carteAmers")){
                        //refOtherDocs.push().setValue("carteAmers","tamers.pdf");
                        Map<String,Object> childMap= new HashMap<String, Object>();
                        childMap.put("carteAmersghj","tamers.pdf");
                        refOtherDocs.updateChildren(childMap);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference ppspsRef= myRef.child("-LBw-rNjtmo9G90LUU2Z/ppspsDocuments");

        HashMap<String,Object> map  = new HashMap<String, Object>();
        map.put("eee","gfhjkl");
        ppspsRef.updateChildren(map);

        ppspsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG,"nb ppsps "+ dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
