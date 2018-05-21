package com.example.rvadam.pfe.AddWorksite;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.LocationHelper.LocationHelper;
import com.example.rvadam.pfe.Model.CurrentStatesWorksitesList;
import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by rdelfoss on 17/05/2018.
 */

public class AddWorksiteFragment extends Fragment {
    private static final String TAG = "AddWorksiteFragment";

    private static final int CHOOSE_PERSON_REQUEST = 1234;

    ArrayList<String> listOfTypes;
    TypeWorksiteDBHelper typeWorksiteDBHelper;
    ArrayAdapter<String> spinnerAdapter;
    private String type;
    public String address;
    double lat = 0.0;
    double lng = 0.0;

    Spinner mWorksiteType;
    EditText mWorksiteName;
    EditText mWorksiteAddressNumber;
    EditText mWorksiteAddressRoad;
    EditText mWorksiteAddressCity;
    EditText mWorksiteAddressCode;
    Button createWorksite;
    Button selectPeople;


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
        selectPeople = (Button) getActivity().findViewById(R.id.select_people_button);
        createWorksite = (Button) getActivity().findViewById(R.id.createWorksite);

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

        selectPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListPeopleActivity.class);
                startActivityForResult(intent, CHOOSE_PERSON_REQUEST);
            }
        });

        createWorksite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "req : " + requestCode + " res : " + resultCode);
        if (requestCode == CHOOSE_PERSON_REQUEST && resultCode == RESULT_OK && data != null) {
            String[] aaaa = data.getStringArrayExtra(ListPeopleActivity.transfertLoPeople);
            Log.i(TAG, "Array of people selected : " + Arrays.toString(aaaa));
        }

    }

    public void setMySpinnerAdapter() {
        // Setup the adapter with the list
        listOfTypes = (ArrayList<String>) CurrentStatesWorksitesList.getInstance().getTypes();
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listOfTypes);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mWorksiteType.setAdapter(spinnerAdapter);
    }

    public void createWorksite(String name, String type, double latitude, double longitude, List<String> listPeople) {

    }

    public void getLocation() {
        getAddress();
        //Toast.makeText(getActivity(), "Adresse : " + address, Toast.LENGTH_SHORT).show();
        LocationHelper locationHelper = new LocationHelper(getActivity());
        String latlng = locationHelper.getLocationFromAddress(address);
        String[] latlngSplit = latlng.split(",");
        lat = Double.parseDouble(latlngSplit[0]);
        lng = Double.parseDouble(latlngSplit[1]);
        Toast.makeText(getActivity(), "latitude : " + lat + " longitude : " + lng, Toast.LENGTH_SHORT).show();

    }

    public void getAddress() {
        String addressNumber = String.valueOf(mWorksiteAddressNumber.getText());
        String addressRoad = String.valueOf(mWorksiteAddressRoad.getText());
        String addressCity = String.valueOf(mWorksiteAddressCity.getText());
        String addressCode = String.valueOf(mWorksiteAddressCode.getText());

        address = addressNumber + " " + addressRoad + ", " + addressCity + ", " + addressCode;
    }
}
