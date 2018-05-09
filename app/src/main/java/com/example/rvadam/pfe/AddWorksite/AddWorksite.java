package com.example.rvadam.pfe.AddWorksite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by rdelfoss on 09/05/2018.
 */

public class AddWorksite extends AppCompatActivity {

    // Layout variables
    private EditText name;
    private TextView tSpinner;
    private Spinner typeSpinner;
    private EditText adresse;
    private Button createWorksite;

    // Firebase variable
    private FirebaseDatabase mDB;
    private DatabaseReference mRef;

    // Variables for worksite's fields
    private ArrayList<String> wTypes;
    private ArrayAdapter<String> adapter;
    private String wid; // Générer un ID unique pour chaque chantier
    private long wDateVIC; // Ne pas renseigner à la création
    private List<String> wEmployees; // Créer une activité de création d'employés rattachés à une entreprise et recuperer les employés concernés
    private double wLattitude; // Récupérer la latitude grace à une adresse et l'API Google Maps
    private double wLongitude; // Récupérer la longitude grace à une adresse et l'API Google Maps
    private String wName; //A renseigner
    private Map<String, String> wOtherDocuments; // renseigner la clé mais accolade vide pour valeur
    private Map<String, String> wPlanDocuments; // renseigner la clé mais accolade vide pour valeur
    private Map<String, String> wSecurityDocuments; // renseigner la clé mais accolade vide pour valeur
    private Map<String, String> wPpspsDocuments; // renseigner les clé (nom entreprise) en mais accolade vide pour valeur
    private String wType; //A renseigner

    //Worksite object to create
    private WorkSite wCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worksite);

        name = (EditText) findViewById(R.id.addName);
        tSpinner = (TextView) findViewById(R.id.addType);
        typeSpinner = (Spinner) findViewById(R.id.addTypeSpinner);
        adresse = (EditText) findViewById(R.id.addAdress);
        createWorksite = (Button) findViewById(R.id.createWorkSiteButton);

        wCreate = new WorkSite();

        mDB = FirebaseDatabase.getInstance();
        mRef = mDB.getReference("types");

        wTypes = new ArrayList<String>();

        // set type
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String t = ds.child("id").getValue(String.class);
                    wTypes.add(t);
                }
                adapter = new ArrayAdapter<String>(AddWorksite.this, android.R.layout.simple_spinner_item, wTypes);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                typeSpinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                wType = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                wType = "No type selected";
            }
        });

        // set id
        DatabaseReference newRef = mRef.push(); // create a new reference of the database with the push of a new worksite
        wCreate.setId(newRef.getKey());

        // set date of VIC
        wDateVIC = 0;

        // set employees

        // set adress

        // set name

        // set otherDocuments

        // set planDocuments

        // set securityDocuments

        // set ppspsDocuments

        // post all the worksite's fields to Firebase database
        createWorksite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(AddWorksite.this, wType, Toast.LENGTH_SHORT).show();
                Toast.makeText(AddWorksite.this, wCreate.getId().toString(), Toast.LENGTH_SHORT).show();


                //Intent intent = new Intent(AddWorksite.this, ListWorkSite.class);
                //startActivity(intent);
            }
        });

    }
}
