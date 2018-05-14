package com.example.rvadam.pfe.People;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.R;

/**
 * Created by rdelfoss on 13/05/2018.
 */

public class PeopleFragment extends Fragment {
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
        TextView mIdCompany;
        TextView mIdRole;

        mFirstname = (TextView) getActivity().findViewById(R.id.firstname);
        mLastname = (TextView) getActivity().findViewById(R.id.lastname);
        mPhone = (TextView) getActivity().findViewById(R.id.phone);
        mId = (TextView) getActivity().findViewById(R.id.idPeople);
        mIdCompany = (TextView) getActivity().findViewById(R.id.idCompany);
        mIdRole = (TextView) getActivity().findViewById(R.id.idRole);

        mFirstname.setText(innerPeople.getFirstname().toString());
        mLastname.setText(innerPeople.getLastname());
        mPhone.setText(innerPeople.getPhone());
        mId.setText(innerPeople.getId());
        mIdCompany.setText(innerPeople.getIdCompany());
        mIdRole.setText(innerPeople.getIdRole());

    }

}
