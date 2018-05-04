package com.example.rvadam.pfe.WelcomeListWorkSites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.WorkSite.WorkSiteActivity;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 04/05/2018.
 */

public class WelcomeListWorkSite extends AppCompatActivity {
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome_list_work_site);
        mListView = (ListView) findViewById(R.id.listViewWorkSites);

        final ArrayList<WorkSite> workSites = WorkSites.getWorkSites();

        CustomArrayAdapterWorkSite adapter = new CustomArrayAdapterWorkSite(WelcomeListWorkSite.this, workSites);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                WorkSite workSite = workSites.get(position);
                WorkSiteActivity.setInnerWorkSite(workSite);

                Intent intent = new Intent(WelcomeListWorkSite.this, WorkSiteActivity.class);
                startActivity(intent);
            }
        });

    }

}
