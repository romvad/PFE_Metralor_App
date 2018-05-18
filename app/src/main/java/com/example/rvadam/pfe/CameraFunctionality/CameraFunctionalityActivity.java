package com.example.rvadam.pfe.CameraFunctionality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rvadam.pfe.R;

public class CameraFunctionalityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_functionality);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, CameraFragment.newInstance())
                .commit();
    }
}
