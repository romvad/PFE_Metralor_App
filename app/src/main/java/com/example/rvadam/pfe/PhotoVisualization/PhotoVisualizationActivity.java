package com.example.rvadam.pfe.PhotoVisualization;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rvadam.pfe.AppGlideModule.GlideApp;
import com.example.rvadam.pfe.CameraFunctionality.CameraFunctionalityActivity;
import com.example.rvadam.pfe.Login.LoginFragment;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.WorkSitePhotoGallery.TabsOfPhotosActivity;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PhotoVisualizationActivity extends AppCompatActivity {

    public static final String EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";
    private static final String TAG="PhotoVisualizationActivity";
    protected static final String SELECTED_PHOTO="selected photo";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_visualization);

        //SpacePhoto spacePhoto = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);
        Bundle bundle1=getIntent().getBundleExtra(EXTRA_SPACE_PHOTO);
        final SpacePhoto spacePhoto=bundle1.getParcelable(EXTRA_SPACE_PHOTO);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PhotoVisualizationFragment photoVisualizationFragment = new PhotoVisualizationFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(SELECTED_PHOTO,spacePhoto);
        photoVisualizationFragment.setArguments(bundle);
        ft.replace(R.id.photoVisualizationFragment_container, photoVisualizationFragment).commit();

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_previous_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TabsOfPhotosActivity.class);
                intent.putExtra(TabsOfPhotosActivity.ID_WORKSITE,spacePhoto.getmIdWorkSite());
                startActivity(intent);
            }
        });



    }

    protected AppCompatActivity getActivity(){
        return this;
    }
}
