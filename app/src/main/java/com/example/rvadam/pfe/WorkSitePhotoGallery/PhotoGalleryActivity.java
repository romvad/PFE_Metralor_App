package com.example.rvadam.pfe.WorkSitePhotoGallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rvadam.pfe.Adapters.ImageGalleryAdapter;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.R;

public class PhotoGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ImageGalleryAdapter adapter = new ImageGalleryAdapter(this, SpacePhoto.getSpacePhotos());
        recyclerView.setAdapter(adapter);
    }
}
