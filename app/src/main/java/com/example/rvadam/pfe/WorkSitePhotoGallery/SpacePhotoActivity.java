package com.example.rvadam.pfe.WorkSitePhotoGallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rvadam.pfe.AppGlideModule.GlideApp;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SpacePhotoActivity extends AppCompatActivity {

    public static final String EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";
    private ImageView mImageView;
    private static final String TAG="SpacePhotoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_photo);

        mImageView = (ImageView) findViewById(R.id.image);
        SpacePhoto spacePhoto = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);

        StorageReference ref= FirebaseStorage.getInstance().getReference();
        String relativePath= spacePhoto.getmFBStorageUrl();
        StorageReference fullRef=ref.child(relativePath);
        Log.i(TAG,"storage ref "+fullRef);

        GlideApp.with(getApplicationContext())
                .load(fullRef)
                .error(R.mipmap.ic_cloud_off_red)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);
    }
}
