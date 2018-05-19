package com.example.rvadam.pfe.TakenPhotoVisualization;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rvadam.pfe.Login.LoginFragment;
import com.example.rvadam.pfe.R;

public class TakenPhotoVisualizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_photo_visualization);

       /* FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        TakenPhotoVisualizationFragment takenPhotoVisualizationFragment = new TakenPhotoVisualizationFragment();
        ft.replace(R.id.takenPhotoVisualizationFragment_container, takenPhotoVisualizationFragment).commit();*/

    }
}
