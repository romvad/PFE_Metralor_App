package com.example.rvadam.pfe.DocumentVisualization;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rvadam.pfe.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by rvadam on 24/05/2018.
 */

public class DocumentVisualizationFragment extends Fragment {

    TextView docNameTextView;
    TextView docFileTextView;
    ImageView imageViewDoc;

    public DocumentVisualizationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String idWorkSite= bundle.getString(DocumentVisualizationActivity.ID_WORKSITE);
        String docName = bundle.getString(DocumentVisualizationActivity.DOCUMENT_NAME);
        String docFile = bundle.getString(DocumentVisualizationActivity.DOCUMENT_FILE_NAME);

        docNameTextView = (TextView) getActivity().findViewById(R.id.docName);
        docFileTextView = (TextView) getActivity().findViewById(R.id.docFile);
        imageViewDoc = (ImageView) getActivity().findViewById(R.id.imageViewDocument);

        StorageReference ref= FirebaseStorage.getInstance().getReference();
       // StorageReference docRef = ref.child("documents/"+idWorkSite+"/"+)

        View view = inflater.inflate(R.layout.fragment_document_visualization, container, false);

        return view;

    }
}
