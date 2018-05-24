package com.example.rvadam.pfe.WorkSiteListOfDocuments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.rvadam.pfe.Adapters.CustomDocumentsListAdapter;
import com.example.rvadam.pfe.DocumentVisualization.DocumentVisualizationActivity;
import com.example.rvadam.pfe.FirebaseDBHelpers.FirebaseDBDocumentsHelpers;
import com.example.rvadam.pfe.Model.CurrentStatesWorkSites;
import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.Model.DocumentTypes;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.WorkSitesManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rvadam on 06/05/2018.
 */

public class SecurityDocumentsFragment extends ListFragment {
    List<Document> securityDocumentsList;
    CustomDocumentsListAdapter adapter;
    String idWorkSite;

    private static final String TAG="SecuDocumentsFragment";

    public SecurityDocumentsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        idWorkSite =bundle.getString("idWorkSite");
        securityDocumentsList=  WorkSitesManager.getWorkSiteById(idWorkSite).getSecurityDocuments();

        View view = inflater.inflate(R.layout.fragment_security_documents, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity()!=null) {
            Log.i(TAG, "activité " + getActivity());
            adapter = new CustomDocumentsListAdapter(getActivity(), R.layout.item_list_documents, securityDocumentsList, DocumentTypes.SECURITY_DOCUMENTS);
            setListAdapter(adapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Document doc= WorkSitesManager.getWorkSiteById(idWorkSite).getSecurityDocuments().get(position);

        Intent intent = new Intent(getActivity(), DocumentVisualizationActivity.class);
        Bundle args = new Bundle();
        args.putString(DocumentVisualizationActivity.ID_WORKSITE,idWorkSite);
        args.putString(DocumentVisualizationActivity.DOCUMENT_NAME,doc.getName());
        args.putString(DocumentVisualizationActivity.DOCUMENT_FILE_NAME,doc.getFileName());
        args.putString(DocumentVisualizationActivity.FB_STORAGE_RELATIVE_PATH,DocumentTypes.SECURITY_DOCUMENTS.name());
        intent.putExtras(args);

    }

    public CustomDocumentsListAdapter getAdapter() {
        return adapter;
    }

}
