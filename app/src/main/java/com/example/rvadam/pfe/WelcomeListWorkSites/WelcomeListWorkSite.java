package com.example.rvadam.pfe.WelcomeListWorkSites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.rvadam.pfe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 04/05/2018.
 */

public class WelcomeListWorkSite extends AppCompatActivity {

    private static final String TAG = "READ WORKSITES FROM DB";

    // Android layout
    private ListView mListView;

    // Firebase var
    private DatabaseReference mDatabase;

    // Array List
    private ArrayList<WorkSite> lWorksites = new ArrayList<WorkSite>();
    private CustomArrayAdapterWorkSite adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_list_work_site);

        mDatabase = FirebaseDatabase.getInstance().getReference("workSites");
        adapter = new CustomArrayAdapterWorkSite(WelcomeListWorkSite.this, (ArrayList<WorkSite>) lWorksites);

        mListView = (ListView) findViewById(R.id.listViewWorkSites);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                WorkSite workSite = dataSnapshot.getValue(WorkSite.class);
                lWorksites.add(workSite);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}