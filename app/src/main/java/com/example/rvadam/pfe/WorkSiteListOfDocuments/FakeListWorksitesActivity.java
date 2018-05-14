package com.example.rvadam.pfe.WorkSiteListOfDocuments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rvadam.pfe.FirebaseDBHelpers.FirebaseDBWorkSitesHelper;
import com.example.rvadam.pfe.R;

public class FakeListWorksitesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_list_worksites);

        Button button=(Button) findViewById(R.id.buttonToListsofDocuments);
        //Retrivement of worksites list, normally called in List of Worksites activity
        FirebaseDBWorkSitesHelper.getListOfWorkSites();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testTabsOfListOfDocumentsActivityCall= new Intent(getActivity(), com.example.rvadam.pfe.WorkSiteListOfDocuments.TabsOfListOfDocumentsActivity.class);
                startActivity(testTabsOfListOfDocumentsActivityCall);
            }
        });
    }

    public AppCompatActivity getActivity(){
        return this;
    }
}
