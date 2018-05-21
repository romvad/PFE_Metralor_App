package com.example.rvadam.pfe.ListPeople;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.People.PeopleActivity;
import com.example.rvadam.pfe.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rdelfoss on 13/05/2018.
 */

public class PeopleCustomAdapter extends ArrayAdapter<People> {
    private static final String TAG = "PeopleCustomAdapter";
    private Context context;

    PeopleCustomAdapter(Context context, ArrayList<People> listOfPeople) {
        super(context, 0, listOfPeople);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.people_info, parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new PeopleCustomAdapter.TweetViewHolder();

            // Get our sub views
            viewHolder.lastName = (TextView) convertView.findViewById(R.id.personLastName);
            viewHolder.firstName = (TextView) convertView.findViewById(R.id.personFirstName);
            viewHolder.company = (TextView) convertView.findViewById(R.id.personCompany);
            viewHolder.role = (TextView) convertView.findViewById(R.id.personRole);
            viewHolder.personCheckBox = (CheckBox) convertView.findViewById(R.id.personCheckBox);

            // save the mini-controller in the view
            convertView.setTag(viewHolder);
        }

        final People people = getItem(position);

        CurrentStatesPeopleList currentStatesPeopleList = CurrentStatesPeopleList.getInstance();
        Map<String, String> companiesMap = currentStatesPeopleList.getCompaniesMap();
        Map<String, String> rolesMap = currentStatesPeopleList.getRolesMap();

        assert people != null;
        String companyName = companiesMap.get(people.getIdCompany());
        Log.i(TAG, "companyName : " + companyName);
        String roleTitle = rolesMap.get(people.getIdRole());
        Log.i(TAG, "roleTitle : " + roleTitle);

        viewHolder.lastName.setText(people.getLastname());
        viewHolder.firstName.setText(people.getFirstname());
        viewHolder.company.setText(companyName);
        viewHolder.role.setText(roleTitle);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                People peopleSelected = (People) people;
                PeopleActivity.setpeople(peopleSelected);

                Intent intent = new Intent(context, PeopleActivity.class);
                context.startActivity(intent);
            }
        });
        
        return convertView;
    }


    class TweetViewHolder {
        TextView lastName;
        TextView firstName;
        TextView company;
        TextView role;
        CheckBox personCheckBox;
    }


}

