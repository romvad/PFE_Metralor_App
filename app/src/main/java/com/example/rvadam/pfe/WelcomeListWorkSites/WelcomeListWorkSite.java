package com.example.rvadam.pfe.WelcomeListWorkSites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.WorkSite.WorkSiteActivity;
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

    private static final String TAG = "Get worksites from DB";

    private ListView mListView;
    private FirebaseDatabase mDB;
    private DatabaseReference mRef;
    private ArrayList<WorkSite> list;
    private CustomAdapter adapter;
    private WorkSite workSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_list_work_site);

        mListView = (ListView) findViewById(R.id.listViewWorkSites);

        workSite = new WorkSite();

        mDB = FirebaseDatabase.getInstance();
        mRef = mDB.getReference("workSites");
        list = new ArrayList<WorkSite>();
        adapter = new CustomAdapter(WelcomeListWorkSite.this, list);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    workSite = ds.getValue(WorkSite.class);
                    list.add(workSite);
                }
                mListView.setAdapter(adapter);

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        int itemPosition = i;
                        WorkSite worksiteSelected = (WorkSite) mListView.getItemAtPosition(itemPosition);
                        WorkSiteActivity.setWorkSite(worksiteSelected);

                        //Toast.makeText(WelcomeListWorkSite.this, worksiteSelected.getName().toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(WelcomeListWorkSite.this, WorkSiteActivity.class);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}