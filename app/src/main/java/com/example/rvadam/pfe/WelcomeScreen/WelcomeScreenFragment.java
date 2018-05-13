package com.example.rvadam.pfe.WelcomeScreen;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rvadam.pfe.ListPeople.ListPeopleActivity;
import com.example.rvadam.pfe.ListWorkSites.ListWorkSiteActivity;
import com.example.rvadam.pfe.R;

/**
 * Created by rdelfoss on 09/05/2018.
 */

public class WelcomeScreenFragment extends Fragment {

    private Button mManageWorksitesButton;
    private Button mManagePeopleButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mManageWorksitesButton = (Button) getActivity().findViewById(R.id.manage_worksites);
        mManagePeopleButton = (Button) getActivity().findViewById(R.id.manage_people);

        mManageWorksitesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listWorksitesCall = new Intent(getActivity(), ListWorkSiteActivity.class);
                startActivity(listWorksitesCall);
            }
        });
        mManagePeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listPeopleCall = new Intent(getActivity(), ListPeopleActivity.class);
                startActivity(listPeopleCall);
            }
        });
    }
}
