package com.example.rvadam.pfe.AddWorksite;

import android.Manifest;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rvadam.pfe.FirebaseDBHelpers.TypeWorksiteDBHelper;
import com.example.rvadam.pfe.Model.CurrentStatesWorksitesList;
import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdelfoss on 17/05/2018.
 */

public class AddWorksiteFragment extends Fragment {
    private static final String TAG = "AddWorksiteFragment";

    ArrayList<String> listOfTypes;
    TypeWorksiteDBHelper typeWorksiteDBHelper;
    ArrayAdapter<String> spinnerAdapter;
    private String name;
    private String type;
    private String adressNumber;
    private String adressRoad;
    private String adressCode;
    Spinner mWorksiteType;
    EditText mWorksiteName;
    EditText mWorksiteAdressNumber;
    EditText mWorksiteAdressRoad;
    EditText mWorksiteAdressCode;
    Button createWorksite;
    double lat;
    double lng;

    //Worksite object to create
    private WorkSite wCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_worksite, container, false);

        // Call the DBHelper that we need
        typeWorksiteDBHelper = new TypeWorksiteDBHelper("types/", this);
        typeWorksiteDBHelper.retrieveType();

        return v;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWorksiteName = (EditText) getActivity().findViewById(R.id.editWorksiteName);
        mWorksiteType = (Spinner) getActivity().findViewById(R.id.addTypeSpinner);
        mWorksiteAdressNumber = (EditText) getActivity().findViewById(R.id.editWorksiteAdressNumber);
        mWorksiteAdressRoad = (EditText) getActivity().findViewById(R.id.editWorksiteAdressRoad);
        mWorksiteAdressCode = (EditText) getActivity().findViewById(R.id.editWorksiteAdressCode);
        createWorksite = (Button) getActivity().findViewById(R.id.createWorksite);

        //wCreate = new WorkSite();

        // set id
        //typeWorksiteDBHelper.pushDBRefToGetId(wCreate);

        name = String.valueOf(mWorksiteName.getText());

        mWorksiteType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                type = parent.getItemAtPosition(pos).toString();
                Toast.makeText(getActivity(), "Type selected : " + type, Toast.LENGTH_SHORT).show();

            }

            public void onNothingSelected(AdapterView<?> parent) {
                type = "No type selected";
                Toast.makeText(getActivity(), "Nothing selected ", Toast.LENGTH_SHORT).show();

            }
        });


        createWorksite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void setMySpinnerAdapter() {
        // Setup the adapter with the list
        listOfTypes = (ArrayList<String>) CurrentStatesWorksitesList.getInstance().getTypes();
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listOfTypes);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mWorksiteType.setAdapter(spinnerAdapter);
    }

    public void createWorksite(String name, String type, String adressNumber, String adressRoad, String adressCode, List<String> listPeople) {

    }

    public void getLocation() {
        //Toast.makeText(getActivity(), "lat : " + lat + " lng : " + lng, Toast.LENGTH_SHORT).show();

    }

    public void getAddress() {
        adressNumber = String.valueOf(mWorksiteAdressNumber.getText());
        adressRoad = String.valueOf(mWorksiteAdressRoad.getText());
        adressCode = String.valueOf(mWorksiteAdressCode.getText());
    }
}
