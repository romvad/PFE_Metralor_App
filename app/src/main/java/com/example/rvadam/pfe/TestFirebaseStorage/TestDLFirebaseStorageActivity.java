package com.example.rvadam.pfe.TestFirebaseStorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rvadam.pfe.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TestDLFirebaseStorageActivity extends AppCompatActivity {
    FirebaseStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dlfirebase_storage);

         storage = FirebaseStorage.getInstance();
       /* StorageReference gsReference = storage.getReferenceFromUrl("gs://pfe-metralor.appspot.com/photo metralor.png");
        ImageView imageView = (ImageView) findViewById(R.id.imageFile);

        // Load the image using Glide
        Glide.with(this /* context *//*)
                .using(new FirebaseImageLoader())
                .load(gsReference)
                .into(imageView);*/
    }
}
