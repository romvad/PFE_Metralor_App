package com.example.rvadam.pfe.WorkSiteListOfDocuments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rvadam.pfe.Adapters.CustomDocumentsListAdapter;
import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.Model.DocumentTypes;
import com.example.rvadam.pfe.R;

import java.util.List;

/**
 * Created by rvadam on 06/05/2018.
 */

public class SecurityDocumentsFragment extends ListFragment {
    List<Document> securityDocumentsList;
    CustomDocumentsListAdapter adapter;

    public SecurityDocumentsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        securityDocumentsList=bundle.getParcelableArrayList("securityList");

        View view = inflater.inflate(R.layout.fragment_security_documents, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new CustomDocumentsListAdapter(getActivity(), R.layout.item_list_documents, securityDocumentsList, DocumentTypes.PLAN_DOCUMENTS);
        setListAdapter(adapter);
    }

    public CustomDocumentsListAdapter getAdapter() {
        return adapter;
    }

}
