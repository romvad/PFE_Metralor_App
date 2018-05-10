package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.Model.FileStatus;
import com.example.rvadam.pfe.WorkSiteListOfDocuments.TabsOfListOfDocumentsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rvadam on 08/05/2018.
 */

public class FirebaseDBDocumentsHelpers {

    private static final String TAG = "FirebaseDBHelpers";

    private static ArrayList<Document> result=new ArrayList<Document>(); //for methods returning list of documents
    private static boolean snapShotBrowseFinished=false;

    //References to all the document categories
    private static FirebaseDatabase database= FirebaseDatabase.getInstance();
    private static DatabaseReference otherDocRef;
    private static DatabaseReference securityDocRef;
    private static DatabaseReference planDocRef;
    private static DatabaseReference ppspsDocRef;
    private static TabsOfListOfDocumentsActivity activityCalling;

    private boolean otherDocsBrowsed=false;
    private boolean securityDocsBrowsed=false;
    private boolean planDocsBrowsed=false;
    private boolean ppspsDocsBrowsed=false;

    public FirebaseDBDocumentsHelpers(String workSiteID, final TabsOfListOfDocumentsActivity activityCalling) {
        this.otherDocRef=database.getReference("workSites/"+workSiteID+"/otherDocuments");
        this.securityDocRef=database.getReference("workSites/"+workSiteID+"/securityDocuments");
        this.planDocRef=database.getReference("workSites/"+workSiteID+"/planDocuments");
        this.ppspsDocRef=database.getReference("workSites/"+workSiteID+"/ppspsDocuments");
        this.activityCalling=activityCalling;

        otherDocRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Document> result= new ArrayList<Document>();

                Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
                Iterator<DataSnapshot> it = dataList.iterator();
                Log.i(TAG," iterator "+it.hasNext());
                while (it.hasNext()) {
                    DataSnapshot snap = it.next();
                    String keyStr = snap.getKey();
                    String str = snap.getValue(String.class);
                    Log.i(TAG, "key value "+keyStr + " " + str);

                    FileStatus status;

                    if (str.equals(" ") || str.equals("")) {
                        status = FileStatus.NOT_UPLOADED;
                    } else {
                        status = FileStatus.UPLOADED;
                    }

                    Document doc = new Document(keyStr, str, status, "-");
                    Log.i(TAG,"before doc addition");
                    result.add(doc);
                    Log.i(TAG,"after doc addition");
                }

                activityCalling.setOtherDocumentsList(result);
                otherDocsBrowsed=true;

                if(otherDocsBrowsed && securityDocsBrowsed && planDocsBrowsed && ppspsDocsBrowsed){
                    activityCalling.refreshFragments();
                    otherDocsBrowsed=false;
                    securityDocsBrowsed=false;
                    ppspsDocsBrowsed=false;
                    planDocsBrowsed=false;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        securityDocRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Document> result= new ArrayList<Document>();

                Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
                Iterator<DataSnapshot> it = dataList.iterator();
                Log.i(TAG," iterator "+it.hasNext());
                while (it.hasNext()) {
                    DataSnapshot snap = it.next();
                    String keyStr = snap.getKey();
                    String str = snap.getValue(String.class);
                    Log.i(TAG, "key value "+keyStr + " " + str);

                    FileStatus status;

                    if (str.equals(" ") || str.equals("")) {
                        status = FileStatus.NOT_UPLOADED;
                    } else {
                        status = FileStatus.UPLOADED;
                    }

                    Document doc = new Document(keyStr, str, status, "-");
                    Log.i(TAG,"before doc addition");
                    result.add(doc);
                    Log.i(TAG,"after doc addition");
                }

                activityCalling.setSecurityDocumentsList(result);
                securityDocsBrowsed=true;

                if(otherDocsBrowsed && securityDocsBrowsed && planDocsBrowsed && ppspsDocsBrowsed){
                    activityCalling.refreshFragments();
                    otherDocsBrowsed=false;
                    securityDocsBrowsed=false;
                    ppspsDocsBrowsed=false;
                    planDocsBrowsed=false;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        planDocRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Document> result= new ArrayList<Document>();

                Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
                Iterator<DataSnapshot> it = dataList.iterator();
                Log.i(TAG," iterator "+it.hasNext());
                while (it.hasNext()) {
                    DataSnapshot snap = it.next();
                    String keyStr = snap.getKey();
                    String str = snap.getValue(String.class);
                    Log.i(TAG, "key value "+keyStr + " " + str);

                    FileStatus status;

                    if (str.equals(" ") || str.equals("")) {
                        status = FileStatus.NOT_UPLOADED;
                    } else {
                        status = FileStatus.UPLOADED;
                    }

                    Document doc = new Document(keyStr, str, status, "-");
                    Log.i(TAG,"before doc addition");
                    result.add(doc);
                    Log.i(TAG,"after doc addition");
                }

                activityCalling.setPlanDocumentsList(result);
                planDocsBrowsed=true;

                if(otherDocsBrowsed && securityDocsBrowsed && planDocsBrowsed && ppspsDocsBrowsed){
                    activityCalling.refreshFragments();
                    otherDocsBrowsed=false;
                    securityDocsBrowsed=false;
                    ppspsDocsBrowsed=false;
                    planDocsBrowsed=false;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        ppspsDocRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Document> result= new ArrayList<Document>();

                Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
                Iterator<DataSnapshot> it = dataList.iterator();
                Log.i(TAG," iterator "+it.hasNext());
                while (it.hasNext()) {
                    DataSnapshot snap = it.next();
                    String keyStr = snap.getKey();
                    String str = snap.getValue(String.class);
                    Log.i(TAG, "key value "+keyStr + " " + str);

                    FileStatus status;

                    if (str.equals(" ") || str.equals("")) {
                        status = FileStatus.NOT_UPLOADED;
                    } else {
                        status = FileStatus.UPLOADED;
                    }

                    Document doc = new Document(keyStr, str, status, "-");
                    Log.i(TAG,"before doc addition");
                    result.add(doc);
                    Log.i(TAG,"after doc addition");
                }
                //snapShotBrowseFinish=true;

                /*while(!(isSnapShotBrowseFinished())){

                }*/
                activityCalling.setPpspsDocumentsList(result);
                ppspsDocsBrowsed=true;

                if(otherDocsBrowsed && securityDocsBrowsed && planDocsBrowsed && ppspsDocsBrowsed){
                    activityCalling.refreshFragments();
                    otherDocsBrowsed=false;
                    securityDocsBrowsed=false;
                    ppspsDocsBrowsed=false;
                    planDocsBrowsed=false;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public static ArrayList<Document> getResult() {
        return result;
    }

    public static boolean isSnapShotBrowseFinished() {
        return snapShotBrowseFinished;
    }

    public void setSnapShotBrowseFinished(boolean snapShotBrowseFinished) {
        this.snapShotBrowseFinished = snapShotBrowseFinished;
    }

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

    public static ArrayList<Document> getListOfDocuments(String typeOfDocuments, String workSiteID) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("workSites/" + workSiteID + "/" + typeOfDocuments);
Log.i(TAG,"type "+typeOfDocuments);

getResult().clear();

final boolean snapShotBrowseFinish=false;

      // ArrayList<Document> result = new ArrayList<Document>();
        myRef.addValueEventListener(new ValueEventListener() {
             //result = new ArrayList<Document>();
             ArrayList<Document> result = new ArrayList<Document>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG,"datasnapshot "+dataSnapshot);
                Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
                Iterator<DataSnapshot> it = dataList.iterator();
                Log.i(TAG," iterator "+it.hasNext());
                while (it.hasNext()) {
                    DataSnapshot snap = it.next();
                    String keyStr = snap.getKey();
                    String str = snap.getValue(String.class);
                    Log.i(TAG, "key value "+keyStr + " " + str);

                    FileStatus status;

                    if (str.equals(" ") || str.equals("")) {
                        status = FileStatus.NOT_UPLOADED;
                    } else {
                        status = FileStatus.UPLOADED;
                    }

                    Document doc = new Document(keyStr, str, status, "-");
                    Log.i(TAG,"before doc addition");
                    result.add(doc);
                    Log.i(TAG,"after doc addition");
                }
                //snapShotBrowseFinish=true;

                /*while(!(isSnapShotBrowseFinished())){

                }*/


            }

//return getResult();
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        Log.i(TAG,"getResult size "+getResult().size());


        for (Document d: getResult()){
            Log.i(TAG,"doc "+d);
        }
        Log.i(TAG, "ref result"+ result.toString() + "type "+ typeOfDocuments);
       return result;
    }
}