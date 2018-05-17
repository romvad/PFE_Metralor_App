package com.example.rvadam.pfe.WorkSitePhotoGallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rvadam.pfe.Adapters.ImageGalleryAdapter;
import com.example.rvadam.pfe.Model.PhotoCategories;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.ListOfPhotosSingletonManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvadam on 16/05/2018.
 */

public class GeneralViewFragment extends Fragment {

    public GeneralViewFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_general_view_photos, container, false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ImageGalleryAdapter adapter = new ImageGalleryAdapter(getActivity(), ListOfPhotosSingletonManager.getListOfPhotosByCategoryAndType(PhotoCategories.GENERAL_VIEW_ACCESS,""));
        recyclerView.setAdapter(adapter);

        return view;
    }


}
