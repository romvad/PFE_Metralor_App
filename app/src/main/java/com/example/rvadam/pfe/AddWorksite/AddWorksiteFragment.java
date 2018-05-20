package com.example.rvadam.pfe.AddWorksite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private String addressNumber;
    private String addressRoad;
    private String addressCity;
    private String addressCode;
    private String address;
    Spinner mWorksiteType;
    EditText mWorksiteName;
    EditText mWorksiteAddressNumber;
    EditText mWorksiteAddressRoad;
    EditText mWorksiteAddressCity;
    EditText mWorksiteAddressCode;
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
        mWorksiteAddressNumber = (EditText) getActivity().findViewById(R.id.editWorksiteAddressNumber);
        mWorksiteAddressRoad = (EditText) getActivity().findViewById(R.id.editWorksiteAddressRoad);
        mWorksiteAddressCity = (EditText) getActivity().findViewById(R.id.editWorksiteAddressCity);
        mWorksiteAddressCode = (EditText) getActivity().findViewById(R.id.editWorksiteAddressCode);
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
                Toast.makeText(getActivity(), "Adresse : " + address, Toast.LENGTH_SHORT).show();
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

    public void createWorksite(String name, String type, String addressNumber, String addressRoad, String addressCode, List<String> listPeople) {

    }

    public void getLocation() {
        //Toast.makeText(getActivity(), "lat : " + lat + " lng : " + lng, Toast.LENGTH_SHORT).show();

    }

    public void getAddress() {
        addressNumber = String.valueOf(mWorksiteAddressNumber.getText());
        addressRoad = String.valueOf(mWorksiteAddressRoad.getText());
        addressCity = String.valueOf(mWorksiteAddressCity.getText());
        addressCode = String.valueOf(mWorksiteAddressCode.getText());

        address = addressNumber + " " + addressRoad + ", " + addressCity + ", " + addressCode;
    }
}
