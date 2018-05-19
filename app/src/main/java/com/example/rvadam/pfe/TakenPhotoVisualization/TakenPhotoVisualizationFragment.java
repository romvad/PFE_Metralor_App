package com.example.rvadam.pfe.TakenPhotoVisualization;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.rvadam.pfe.R;

/**
 * Created by rvadam on 19/05/2018.
 */

public class TakenPhotoVisualizationFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView;
    private Button uploadButton;
    private Button backToCameraButton;

    public TakenPhotoVisualizationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView=(ImageView) view.findViewById(R.id.takenPhoto);
        uploadButton= (Button) view.findViewById(R.id.uploadTakenPhotoButton);
        backToCameraButton=(Button) view.findViewById(R.id.backToCameraButton);

        uploadButton.setOnClickListener(this);
        backToCameraButton.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.uploadTakenPhotoButton:
                break;
            case R.id.backToCameraButton:
                break;
            default:;
        }

    }
}
