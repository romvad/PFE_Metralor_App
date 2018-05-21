package com.example.rvadam.pfe.ListPeople;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.rvadam.pfe.AddWorksite.AddWorksiteActivity;
import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.R;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 11/05/2018.
 */

public class ListPeopleFragment extends Fragment {
    private static final String TAG = "Get people from DB";

    private PeopleCustomAdapter adapter;
    ArrayList<People> listOfPeople;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_people, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get the elements from the view
        ListView mListView = (ListView) getActivity().findViewById(R.id.listViewPeople);
        Button buttonValidate = (Button) getActivity().findViewById(R.id.button_choose_persons);

        // Setup the adapter with the list
        listOfPeople = (ArrayList<People>) CurrentStatesPeopleList.getInstance().getCurrentPeopleList();
        adapter = new PeopleCustomAdapter(getActivity(), listOfPeople);
        mListView.setAdapter(adapter);

        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddWorksiteActivity.class);
                ListPeopleActivity lPA = ((ListPeopleActivity) getActivity());
                String[] mTab = lPA.getLoPeople();
                intent.putExtra(lPA.transfertLoPeople, mTab);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });

    }

    public PeopleCustomAdapter getAdapter() {
        return adapter;
    }

}