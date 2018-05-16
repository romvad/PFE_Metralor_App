package com.example.rvadam.pfe.ListPeople;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.People.PeopleActivity;
import com.example.rvadam.pfe.R;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 11/05/2018.
 */

public class ListPeopleFragment extends Fragment {
    private static final String TAG = "Get people from DB";

    private FloatingActionButton buttonAdd;
    private ListView mListView;
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
        mListView = (ListView) getActivity().findViewById(R.id.listViewPeople);
        buttonAdd = (FloatingActionButton) getActivity().findViewById(R.id.buttonAddPeople);

        // Setup the adapter with the list
        listOfPeople = (ArrayList<People>) CurrentStatesPeopleList.getInstance().getCurrentPeopleList();
        adapter = new PeopleCustomAdapter(getActivity(), listOfPeople);
        mListView.setAdapter(adapter);

        // Setup the onClickListener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "Nom : " + listOfPeople.get(position).getLastname(), Toast.LENGTH_SHORT).show();
                People peopleSelected = (People) listOfPeople.get(position);
                PeopleActivity.setpeople(peopleSelected);

                Intent intent = new Intent(getActivity(), PeopleActivity.class);
                startActivity(intent);
            }
        });

    }

    public PeopleCustomAdapter getAdapter() {
        return adapter;
    }

}