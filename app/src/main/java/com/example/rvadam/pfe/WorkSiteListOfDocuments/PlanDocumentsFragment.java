package com.example.rvadam.pfe.WorkSiteListOfDocuments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rvadam.pfe.Adapters.CustomDocumentsListAdapter;
import com.example.rvadam.pfe.FirebaseDBHelpers.FirebaseDBDocumentsHelpers;
import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.Model.DocumentTypes;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.WorkSitesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvadam on 06/05/2018.
 */

public class PlanDocumentsFragment extends ListFragment {

    private static final String TAG = "PlanDocumentsFragment";
    List<Document> planDocumentsList;
    CustomDocumentsListAdapter adapter;

    public PlanDocumentsFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String idWorkSite=bundle.getString("idWorkSite");
        //Retrivement of the lists of documents
        //FirebaseDBDocumentsHelpers dbDocumentsHelpers= new FirebaseDBDocumentsHelpers(idWorkSite,this);
        //securityDocumentsList= FirebaseDBHelpers.getListOfDocuments("securityDocuments",idWorkSite);
        //planDocumentsList= FirebaseDBDocumentsHelpers.getListOfDocuments("planDocuments",idWorkSite);
        //Log.i(TAG,"ref plan list "+planDocumentsList.toString());
        //ppspsDocumentsList= FirebaseDBHelpers.getListOfDocuments("ppspsDocuments",idWorkSite);
        planDocumentsList= WorkSitesManager.getWorkSiteById(idWorkSite).getOtherDocuments();

        View view = inflater.inflate(R.layout.fragment_plan_documents, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity()!=null) {
            Log.i(TAG, "activité " + getActivity());
            adapter = new CustomDocumentsListAdapter(getActivity(), R.layout.item_list_documents, planDocumentsList, DocumentTypes.PLAN_DOCUMENTS);
            setListAdapter(adapter);
        }
    }

    public CustomDocumentsListAdapter getAdapter() {
        return adapter;
    }
}
