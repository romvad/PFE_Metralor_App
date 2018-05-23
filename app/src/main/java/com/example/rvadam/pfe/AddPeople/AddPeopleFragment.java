package com.example.rvadam.pfe.AddPeople;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rvadam.pfe.FirebaseDBHelpers.CompanyDBHelper;
import com.example.rvadam.pfe.FirebaseDBHelpers.PeopleDBHelper;
import com.example.rvadam.pfe.FirebaseDBHelpers.RoleDBHelper;
import com.example.rvadam.pfe.Model.Company;
import com.example.rvadam.pfe.Model.CurrentStateRolesList;
import com.example.rvadam.pfe.Model.CurrentStatesCompaniesList;
import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.Model.Role;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.WelcomeScreen.WelcomeScreenActivity;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 23/05/2018.
 */

public class AddPeopleFragment extends Fragment {
    private static final String TAG = "AddPeopleFragment";

    //DBHelper
    PeopleDBHelper peopleDBHelper;
    CompanyDBHelper companyDBHelper;
    RoleDBHelper roleDBHelper;

    //variables
    private String firstname, lastname, email, idCompany, idRole, phone;
    ArrayList<Company> lCompaniesObject;
    ArrayList<Role> lRolesObject;
    ArrayList<String> lCompaniesString, lRoleString;
    ArrayAdapter<String> companySpinnerAdapter, roleSpinnerAdapter;
    int positionCompany;
    int positionRole;

    //views
    EditText editPersonLastName;
    EditText editPersonFirstName;
    EditText editPersonEmail;
    EditText editPersonPhone;
    Spinner company_spinner;
    Spinner role_spinner;
    Button createPeople;

    //People object to create
    private People peopleToCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Call the DBHelper that we need
        peopleDBHelper = new PeopleDBHelper("people", getActivity());
        peopleDBHelper.retrievePeople();
        companyDBHelper = new CompanyDBHelper("companies", getActivity());
        companyDBHelper.retrieveCompany();
        roleDBHelper = new RoleDBHelper("roles", getActivity());
        roleDBHelper.retrieveRole();


        return inflater.inflate(R.layout.fragment_add_people, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupUI(getActivity().findViewById(R.id.add_people_fragment));

        editPersonLastName = (EditText) getActivity().findViewById(R.id.editPersonLastName);
        editPersonFirstName = (EditText) getActivity().findViewById(R.id.editPersonFirstName);
        editPersonEmail = (EditText) getActivity().findViewById(R.id.editPersonEmail);
        editPersonPhone = (EditText) getActivity().findViewById(R.id.editPersonPhone);
        company_spinner = (Spinner) getActivity().findViewById(R.id.company_spinner);
        role_spinner = (Spinner) getActivity().findViewById(R.id.role_spinner);
        createPeople = (Button) getActivity().findViewById(R.id.createPeople);

        //listeners on spinners
        company_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String company;

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                positionCompany = pos;
                company = parent.getItemAtPosition(pos).toString();
                Toast.makeText(getActivity(), "Company selected : " + company, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                company = "No company selected";
                Toast.makeText(getActivity(), "Company selected : " + company, Toast.LENGTH_SHORT).show();
            }
        });
        role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String role;

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                positionRole = pos;
                role = parent.getItemAtPosition(pos).toString();
                Toast.makeText(getActivity(), "Company selected : " + role, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                role = "No company selected";
                Toast.makeText(getActivity(), "Company selected : " + role, Toast.LENGTH_SHORT).show();
            }
        });

        createPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPeople();

                Intent intent = new Intent(getActivity(), WelcomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setCompanySpinnerAdapter() {
        lCompaniesObject = (ArrayList<Company>) CurrentStatesCompaniesList.getInstance().getCurrentCompaniesList();
        lCompaniesString = new ArrayList<String>();
        lCompaniesString = listObjectCompanyToString(lCompaniesObject);
        companySpinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lCompaniesString);
        companySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        company_spinner.setAdapter(companySpinnerAdapter);
    }

    public void setRoleSpinnerAdapter() {
        lRolesObject = (ArrayList<Role>) CurrentStateRolesList.getInstance().getCurrentRolesList();
        lRoleString = new ArrayList<String>();
        lRoleString = listObjectRoleToString(lRolesObject);
        roleSpinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lRoleString);
        roleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role_spinner.setAdapter(roleSpinnerAdapter);
    }

    public void createPeople() {
        firstname = String.valueOf(editPersonFirstName.getText());
        lastname = String.valueOf(editPersonLastName.getText());
        email = String.valueOf(editPersonEmail.getText());
        ;
        idCompany = lCompaniesObject.get(positionCompany).getId();
        idRole = lRolesObject.get(positionRole).getId();
        phone = String.valueOf(editPersonPhone.getText());

        peopleToCreate = new People(firstname, lastname, email, idCompany, idRole, phone);
        peopleDBHelper.pushPeople(peopleToCreate);
    }

    public ArrayList<String> listObjectCompanyToString(ArrayList<Company> list) {
        ArrayList<String> l = new ArrayList<>();
        for (Company c : list) {
            l.add(c.getName());
        }
        return l;
    }

    public ArrayList<String> listObjectRoleToString(ArrayList<Role> list) {
        ArrayList<String> l = new ArrayList<>();
        for (Role c : list) {
            l.add(c.getTitle());
        }
        return l;
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
}

