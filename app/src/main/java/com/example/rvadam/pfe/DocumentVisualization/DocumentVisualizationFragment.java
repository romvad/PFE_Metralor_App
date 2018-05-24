package com.example.rvadam.pfe.DocumentVisualization;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rvadam.pfe.AppGlideModule.GlideApp;
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
        String docCateg= bundle.getString(DocumentVisualizationActivity.DOCUMENT_CATEGORY);

        docNameTextView = (TextView) getActivity().findViewById(R.id.docName);
        docFileTextView = (TextView) getActivity().findViewById(R.id.docFile);
        imageViewDoc = (ImageView) getActivity().findViewById(R.id.imageViewDocument);

        StorageReference ref= FirebaseStorage.getInstance().getReference();
        StorageReference docRef = ref.child(idWorkSite+"documents/"+docCateg+"/"+docName+"/"+docFile);

        View view = inflater.inflate(R.layout.fragment_document_visualization, container, false);

        GlideApp.with(getActivity().getApplicationContext())
                .load(docRef)
                .placeholder(R.mipmap.ic_image_not_available)
                .error(R.mipmap.ic_image_not_available)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewDoc);

        return view;

    }
}
