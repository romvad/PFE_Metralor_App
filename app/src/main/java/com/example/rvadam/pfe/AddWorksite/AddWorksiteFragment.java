package com.example.rvadam.pfe.AddWorksite;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rvadam.pfe.FirebaseDBHelpers.TypeWorksiteDBHelper;
import com.example.rvadam.pfe.FirebaseDBHelpers.WorksiteDBHelper;
import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.ListWorkSites.ListWorkSiteActivity;
import com.example.rvadam.pfe.LocationHelper.LocationHelper;
import com.example.rvadam.pfe.Model.CurrentStatesWorksitesList;
import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.PeopleManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by rdelfoss on 17/05/2018.
 */

public class AddWorksiteFragment extends Fragment {
    private static final String TAG = "AddWorksiteFragment";

    private static final int CHOOSE_PERSON_REQUEST = 1234;

    ArrayList<String> listOfTypes;
    TypeWorksiteDBHelper typeWorksiteDBHelper;
    WorksiteDBHelper worksiteDBHelper;
    ArrayAdapter<String> spinnerAdapter;
    private String type;
    public String address;
    double lat = 0.0;
    double lng = 0.0;
    String[] listOfIdSelected;
    String[] listOfPeopleSelected;

    Spinner mWorksiteType;
    EditText mWorksiteName;
    EditText mWorksiteAddressNumber;
    EditText mWorksiteAddressRoad;
    EditText mWorksiteAddressCity;
    EditText mWorksiteAddressCode;
    ListView mWorksiteListOfPeople;
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

        ListWorkSiteActivity listWorkSiteActivity = new ListWorkSiteActivity();

        worksiteDBHelper = new WorksiteDBHelper("workSites/", listWorkSiteActivity);

        return v;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupUI(getActivity().findViewById(R.id.people_fragment));

        mWorksiteName = (EditText) getActivity().findViewById(R.id.editWorksiteName);
        mWorksiteType = (Spinner) getActivity().findViewById(R.id.addTypeSpinner);
        mWorksiteAddressNumber = (EditText) getActivity().findViewById(R.id.editWorksiteAddressNumber);
        mWorksiteAddressRoad = (EditText) getActivity().findViewById(R.id.editWorksiteAddressRoad);
        mWorksiteAddressCity = (EditText) getActivity().findViewById(R.id.editWorksiteAddressCity);
        mWorksiteAddressCode = (EditText) getActivity().findViewById(R.id.editWorksiteAddressCode);
        mWorksiteListOfPeople = (ListView) getActivity().findViewById(R.id.listOfPeopleSelected);
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
                getAddress();
                getLocation(address);
                createWorksite(String.valueOf(mWorksiteName.getText()), type, lat, lng, listOfIdSelected);

                Intent intent = new Intent(getActivity(), ListWorkSiteActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "req : " + requestCode + " res : " + resultCode);
        if (requestCode == CHOOSE_PERSON_REQUEST && resultCode == RESULT_OK && data != null) {
            String[] aaaa = data.getStringArrayExtra(ListPeopleActivity.transfertLoPeople);
            listOfPeopleSelected = removeNullValue(aaaa);
            listOfIdSelected = removeNullValue(aaaa);
            Log.i(TAG, "Array of people selected : " + Arrays.toString(listOfIdSelected));
            for (int i = 0; i < listOfIdSelected.length; i++) {
                listOfPeopleSelected[i] = PeopleManager.getPeopleLastNameById(listOfIdSelected[i]) + " " + PeopleManager.getPeopleFirstNameById(listOfIdSelected[i]);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfPeopleSelected);
            mWorksiteListOfPeople.setAdapter(adapter);
        }

    }

    public void setMySpinnerAdapter() {
        // Setup the adapter with the list
        listOfTypes = (ArrayList<String>) CurrentStatesWorksitesList.getInstance().getTypes();
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listOfTypes);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mWorksiteType.setAdapter(spinnerAdapter);
    }

    public void createWorksite(String name, String type, double latitude, double longitude, String[] listOfIdSelected) {
        List<String> employees = new ArrayList<>();
        for (String aListOfIdSelected : listOfIdSelected) {
            employees.add(aListOfIdSelected);
        }

        Map<String, String> otherDocs = new HashMap<String, String>();
        otherDocs.put("carteAmers", "amers.pdf ");
        otherDocs.put("etudeSol", "sol.pdf ");
        otherDocs.put("ficheLoc", " ");
        otherDocs.put("noteCalcul", "");
        otherDocs.put("predimMassif", " ");

        Map<String, String> secuDocs = new HashMap<String, String>();
        secuDocs.put("APD", " ");
        secuDocs.put("PGC", " ");
        secuDocs.put("VTDI", " ");

        Map<String, String> planDocs = new HashMap<String, String>();
        planDocs.put("APD", " ");
        planDocs.put("APS", " ");
        planDocs.put("Serrurerie", " ");

        Map<String, String> ppspsDocs = new HashMap<String, String>();
        for (String aListOfIdSelected : listOfIdSelected) {
            ppspsDocs.put(PeopleManager.getPeopleCompanyById(aListOfIdSelected), " ");
        }

        Date dateVicProvisoire = new Date();

        WorkSite w1 = new WorkSite(dateVicProvisoire.getTime(), employees, latitude, longitude, name, otherDocs, planDocs, secuDocs, ppspsDocs, type);
        worksiteDBHelper.pushWorksite(w1);
    }

    public void getLocation(String address) {

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

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public String[] removeNullValue(String[] str) {
        String[] firstArray;

        List<String> list = new ArrayList<String>();

        for (String s : str) {
            if (s != null && s.length() > 0) {
                list.add(s);
            }
        }

        firstArray = list.toArray(new String[list.size()]);
        return firstArray;
    }
}

