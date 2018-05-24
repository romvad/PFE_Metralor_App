package com.example.rvadam.pfe.DocumentVisualization;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rvadam.pfe.R;

public class DocumentVisualizationActivity extends AppCompatActivity {

    public static final String ID_WORKSITE ="ID_WORKSITE";
    public static final String FB_STORAGE_RELATIVE_PATH = "FB_STORAGE_RELATIVE_PATH";
    public static final String DOCUMENT_NAME = "DOCUMENT_NAME";
    public static final String DOCUMENT_FILE_NAME ="DOCUMENT_FILE_NAME" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_visualization);

        Intent intent = getIntent();
        String idWorkSite= intent.getExtras().getString(DocumentVisualizationActivity.ID_WORKSITE);
        String docName = intent.getExtras().getString(DocumentVisualizationActivity.DOCUMENT_NAME);
        String docFile = intent.getExtras().getString(DocumentVisualizationActivity.DOCUMENT_FILE_NAME);
        String fbPath = intent.getExtras().getString(DocumentVisualizationActivity.FB_STORAGE_RELATIVE_PATH);

        Bundle args = new Bundle();
        args.putString(DocumentVisualizationActivity.ID_WORKSITE,idWorkSite);
        args.putString(DocumentVisualizationActivity.DOCUMENT_NAME,docName);
        args.putString(DocumentVisualizationActivity.DOCUMENT_FILE_NAME,docFile);
        args.putString(DocumentVisualizationActivity.FB_STORAGE_RELATIVE_PATH,fbPath);
        DocumentVisualizationFragment fragment = new DocumentVisualizationFragment();
        fragment.setArguments(args);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.documentVisualizationFragment_container, fragment).commit();
    }
}
