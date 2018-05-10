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
import com.example.rvadam.pfe.operationTests.SampleDocuments;

import java.util.List;

/**
 * Created by rvadam on 06/05/2018.
 */

public class OtherDocumentsFragment extends ListFragment {

    private static final String TAG ="OtherDocumentsFragment" ;
    List<Document> otherDocumentsList;
    CustomDocumentsListAdapter adapter;

    public OtherDocumentsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
         //bundle.getParcelableArrayList("otherList");
        otherDocumentsList= SampleDocuments.getSampleOtherDocument();
        String idWorkSite="-LBw-rNjtmo9G90LUU2Z";
        //otherDocumentsList= FirebaseDBDocumentsHelpers.getListOfDocuments("otherDocuments",idWorkSite);


        View view = inflater.inflate(R.layout.fragment_other_documents, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity()!=null){
            Log.i(TAG,"activité "+getActivity());
            adapter = new CustomDocumentsListAdapter(getActivity(), R.layout.item_list_documents, otherDocumentsList, DocumentTypes.OTHER_DOCUMENTS);
            setListAdapter(adapter);
        }
    }

    public CustomDocumentsListAdapter getAdapter() {
        return adapter;
    }




}
