package com.example.rvadam.pfe.ListPeople;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.People.PeopleActivity;
import com.example.rvadam.pfe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 11/05/2018.
 */

public class ListPeopleFragment extends ListFragment {
    OnPeopleSelectedListener mCallback;

    private static final String TAG = "Get people from DB";

    private static People innerPeople;
    private FloatingActionButton buttonAdd;
    private ListView mListView;
    private FirebaseDatabase mDB;
    private DatabaseReference mRef;
    private ArrayList<People> list;
    private PeopleCustomAdapter adapter;
    private People people;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnPeopleSelectedListener {
        /** Called by ListPeopleFragment when a list item is selected */
        public void onPeopleSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListView = (ListView) getActivity().findViewById(R.id.listViewPeople);
        buttonAdd = (FloatingActionButton) getActivity().findViewById(R.id.buttonAddPeople);

        people = new People();
        list = new ArrayList<People>();

        mDB = FirebaseDatabase.getInstance();
        mRef = mDB.getReference("people");

        // Create an array adapter for the list view
        adapter = new PeopleCustomAdapter(getActivity(), list);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    people = ds.getValue(People.class);
                    list.add(people);
                }
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.people_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnPeopleSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mCallback.onPeopleSelected(position);

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        people = new People();

        mDB = FirebaseDatabase.getInstance();
        mRef = mDB.getReference("people");
        list = new ArrayList<People>();
        adapter = new PeopleCustomAdapter(getActivity(), list);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    people = ds.getValue(People.class);
                    list.add(people);
                }
                mListView.setAdapter(adapter);

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        int itemPosition = i;
                        // Send the event to the host activity
                        mCallback.onPeopleSelected(itemPosition);
                        /*People peopleSelected = (People) mListView.getItemAtPosition(itemPosition);
                        ListPeopleFragment.setPeople(peopleSelected);

                        //Toast.makeText(ListWorkSiteActivity.this, worksiteSelected.getName().toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), PeopleActivity.class);
                        startActivity(intent);*/
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

/*        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListPeopleActivity.this, AddPeople.class);
                startActivity(intent);
            }
        });*/

    }

    public static void setPeople(com.example.rvadam.pfe.Model.People people) {
        innerPeople = people;
    }

}