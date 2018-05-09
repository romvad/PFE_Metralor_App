package com.example.rvadam.pfe.WelcomeListWorkSites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rvadam.pfe.Model.WorkSite;
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

    private static final String TAG = "Get worksites from DB";

    private ListView mListView;
    private FirebaseDatabase mDB;
    private DatabaseReference mRef;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private WorkSite workSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_list_work_site);

        mListView = (ListView) findViewById(R.id.listViewWorkSites);

        workSite = new WorkSite();

        mDB = FirebaseDatabase.getInstance();
        mRef = mDB.getReference("workSites");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(WelcomeListWorkSite.this, R.layout.worksite_info, R.id.worksiteInfo, list);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    workSite = ds.getValue(WorkSite.class);
                    list.add(workSite.getName().toString());
                }
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}