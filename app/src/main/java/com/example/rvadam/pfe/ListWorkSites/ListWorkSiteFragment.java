package com.example.rvadam.pfe.ListWorkSites;

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

import com.example.rvadam.pfe.AddWorksite.AddWorksiteActivity;
import com.example.rvadam.pfe.Model.CurrentStatesWorksitesList;
import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.WorkSite.WorkSiteActivity;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 17/05/2018.
 */

public class ListWorkSiteFragment extends Fragment {
    private static final String TAG = "ListWorkSiteFragment";

    private WorksitesCustomAdapter adapter;
    ArrayList<WorkSite> listOfWorksites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_worksites, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get the elements from the view
        ListView mListView = (ListView) getActivity().findViewById(R.id.listViewWorkSites);
        FloatingActionButton buttonAdd = (FloatingActionButton) getActivity().findViewById(R.id.buttonAddWorksite);

        // Setup the adapter with the list
        listOfWorksites = (ArrayList<WorkSite>) CurrentStatesWorksitesList.getInstance().getCurrentWorksitesList();
        adapter = new WorksitesCustomAdapter(getActivity(), listOfWorksites);
        mListView.setAdapter(adapter);

        // Setup the onClickListener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "Nom : " + listOfPeople.get(position).getLastname(), Toast.LENGTH_SHORT).show();
                WorkSite workSiteSelected = (WorkSite) listOfWorksites.get(position);
                WorkSiteActivity.setInnerWorksite(workSiteSelected);

                Intent intent = new Intent(getActivity(), WorkSiteActivity.class);
                startActivity(intent);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddWorksiteActivity.class);
                startActivity(intent);
            }
        });

    }

    public WorksitesCustomAdapter getAdapter() {
        return adapter;
    }

}