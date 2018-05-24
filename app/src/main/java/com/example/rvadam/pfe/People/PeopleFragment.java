package com.example.rvadam.pfe.People;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.R;

import java.util.Map;

/**
 * Created by rdelfoss on 13/05/2018.
 */

public class PeopleFragment extends Fragment {
    private static final String TAG = "PeopleFragment";

    private static final String DESCRIBABLE_KEY = "describable_key";
    private People innerPeople;

    public static PeopleFragment newInstance(People innerPeople) {
        PeopleFragment fragment = new PeopleFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, innerPeople);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        innerPeople = (People) getArguments().getSerializable(DESCRIBABLE_KEY);

        return inflater.inflate(R.layout.fragment_people, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView mFirstname;
        TextView mLastname;
        TextView mPhone;
        TextView mId;
        TextView mCompany;
        TextView mRole;

        mFirstname = (TextView) getActivity().findViewById(R.id.firstname);
        mLastname = (TextView) getActivity().findViewById(R.id.lastname);
        mPhone = (TextView) getActivity().findViewById(R.id.phone);
        mCompany = (TextView) getActivity().findViewById(R.id.company);
        mRole = (TextView) getActivity().findViewById(R.id.role);

        CurrentStatesPeopleList currentStatesPeopleList = CurrentStatesPeopleList.getInstance();
        Map<String, String> companiesMap = currentStatesPeopleList.getCompaniesMap();
        Map<String, String> rolesMap = currentStatesPeopleList.getRolesMap();

        String companyName = companiesMap.get(innerPeople.getIdCompany());
        Log.i(TAG, "companyName : " + companyName);
        String roleTitle = rolesMap.get(innerPeople.getIdRole());
        Log.i(TAG, "roleTitle : " + roleTitle);

        mFirstname.setText(innerPeople.getFirstname());
        mLastname.setText(innerPeople.getLastname());
        mPhone.setText(innerPeople.getPhone());
        mCompany.setText(companyName);
        mRole.setText(roleTitle);

    }

}
