package com.example.rvadam.pfe.WorkSiteListOfDocuments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rvadam.pfe.FirebaseDBHelpers.FirebaseDBWorkSitesHelper;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.WriteImagesInPDF.WriteImagesInPDFActivity;

public class FakeListWorksitesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_list_worksites);

        Button button=(Button) findViewById(R.id.buttonToListsofDocuments);
        Button button1=(Button) findViewById(R.id.buttonToListsofPhotos);
        Button button2=(Button) findViewById(R.id.buttonToPrintPhotos);
        //Retrivement of worksites list, normally called in List of Worksites activity
        FirebaseDBWorkSitesHelper.getListOfWorkSites();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testTabsOfListOfDocumentsActivityCall= new Intent(getActivity(), com.example.rvadam.pfe.WorkSiteListOfDocuments.TabsOfListOfDocumentsActivity.class);
                startActivity(testTabsOfListOfDocumentsActivityCall);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tabsOfPhotosActivityCall= new Intent(getActivity(), com.example.rvadam.pfe.WorkSitePhotoGallery.TabsOfPhotosActivity.class);
                startActivity(tabsOfPhotosActivityCall);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeImagesInPDFActivityCall= new Intent(getActivity(), WriteImagesInPDFActivity.class);
                startActivity(writeImagesInPDFActivityCall);
            }
        });
    }

    public AppCompatActivity getActivity(){
        return this;
    }
}
