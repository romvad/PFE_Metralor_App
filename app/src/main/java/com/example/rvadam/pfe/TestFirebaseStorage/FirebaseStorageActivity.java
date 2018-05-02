package com.example.rvadam.pfe.TestFirebaseStorage;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.WorkSite.WorkSiteActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class FirebaseStorageActivity extends AppCompatActivity {

    private static final String TAG = "FirebaseStorageActivity" ;
    FirebaseStorage storage;
    StorageReference storageRef;
    EditText editNameWorkSite;
    Button createWorkSiteButton;
    Button visuChantier;

    //List of photo categories for test
    List<String> photoCategories=new ArrayList<String>();
    List<String> typesAdductions= new ArrayList<String>();
    List<String> typesWorkSiteAccess= new ArrayList<String>();
    List<String> photosEnergie= new ArrayList<String>();
    List<String> photosTransmission= new ArrayList<String>();
    List<String> meansOfAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_storage);

        storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        storageRef = storage.getReference();
        Log.i(TAG,"storageRef "+storageRef.toString());

        //We retrieve Work site's name
        editNameWorkSite = (EditText) findViewById(R.id.nameWorkSite);

        createWorkSiteButton = (Button) findViewById(R.id.createWorkSiteButton);

        createWorkSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameWorkSite = editNameWorkSite.getText().toString();
                createWorkSite(nameWorkSite);
            }
        });

        visuChantier=(Button) findViewById(R.id.workSiteVisualizationButton);
        visuChantier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent workSiteActivityCall= new Intent(getCurrentActivity(), WorkSiteActivity.class);
                startActivity(workSiteActivityCall);
            }
        });
    }

    public Activity getCurrentActivity(){
        return this;
    }

    public void createWorkSite(String nameWorkSite){

        //We create a reference for the WorkSite and the associated tree for documents and photo categories
        StorageReference workSiteRef=storageRef.child(nameWorkSite);
        // photosRef points to "photos"
        StorageReference photosRef = workSiteRef.child("photos");
        // DocumentsRef points to "documents"
        StorageReference documentsRef = workSiteRef.child("documents");

        StorageReference categAdductionsRef= photosRef.child("adductions");
        StorageReference categAccessRef = photosRef.child("workSiteAccess");

        StorageReference typeEnergie= categAdductionsRef.child("energie");
        StorageReference typeTransmission = categAdductionsRef.child("transmission");
        StorageReference typeSiteAccess = categAccessRef.child("siteAccess");
        StorageReference typeMeansOfAccess = categAccessRef.child("meansofAccess");

        documentsRef.child("constat_huissier");

    }
}
