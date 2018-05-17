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
import com.example.rvadam.pfe.Model.CoursesAccessPhotoTypes;
import com.example.rvadam.pfe.Model.MaltAdductionsPhotoTypes;
import com.example.rvadam.pfe.Model.PhotoCategories;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.ListOfPhotosSingletonManager;

import java.util.List;

/**
 * Created by rvadam on 16/05/2018.
 */

public class MaltAdductionsFragment extends Fragment {

    public MaltAdductionsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_malt_adductions_photos, container, false);

        for (MaltAdductionsPhotoTypes type : MaltAdductionsPhotoTypes.values()) {
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
            List<SpacePhoto> listToDisplay = ListOfPhotosSingletonManager.getListOfPhotosByCategoryAndType(PhotoCategories.MALT_ADDUCTIONS, String.valueOf(type));
            int rvId = getResources().getIdentifier("rv_" + String.valueOf(type).toLowerCase(), "id", getActivity().getPackageName());
            RecyclerView recyclerView = (RecyclerView) view.findViewById(rvId);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);

            ImageGalleryAdapter adapter = new ImageGalleryAdapter(getActivity(), listToDisplay);
            recyclerView.setAdapter(adapter);
        }


        return view;
    }
}
