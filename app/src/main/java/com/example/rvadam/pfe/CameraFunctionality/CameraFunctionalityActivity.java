package com.example.rvadam.pfe.CameraFunctionality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rvadam.pfe.PhotoVisualization.PhotoVisualizationFragment;
import com.example.rvadam.pfe.R;

public class CameraFunctionalityActivity extends AppCompatActivity {

    public static final String FB_STORAGE_PATH ="FB_STORAGE_PATH" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_functionality);

        String photoName=getIntent().getStringExtra(PhotoVisualizationFragment.PHOTO_NAME_KEY);
        CameraFragment cameraFragment=new CameraFragment();
        Bundle bundle=new Bundle();
        bundle.putString(PhotoVisualizationFragment.PHOTO_NAME_KEY,photoName);
        cameraFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, cameraFragment)
                .commit();
    }
}
