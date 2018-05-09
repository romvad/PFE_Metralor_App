package com.example.rvadam.pfe.AddWorksite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.rvadam.pfe.R;

/**
 * Created by rdelfoss on 09/05/2018.
 */

public class AddWorksite extends AppCompatActivity {

    private EditText name;
    private EditText type;
    private EditText vic;
    private EditText adresse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worksite);

        name = (EditText) findViewById(R.id.addName);
        type = (EditText) findViewById(R.id.addType);
        vic = (EditText) findViewById(R.id.addVIC);
        adresse = (EditText) findViewById(R.id.addAdress);

    }
}
