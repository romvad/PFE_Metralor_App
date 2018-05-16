package com.example.rvadam.pfe.ListPeople;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rdelfoss on 13/05/2018.
 */

public class PeopleCustomAdapter extends ArrayAdapter<People> {
    private static final String TAG = "PeopleCustomAdapter";

    PeopleCustomAdapter(Context context, ArrayList<People> listOfPeople) {
        super(context, 0, listOfPeople);
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
            viewHolder.LastName = (TextView) convertView.findViewById(R.id.personLastName);
            viewHolder.FirstName = (TextView) convertView.findViewById(R.id.personFirstName);
            viewHolder.Company = (TextView) convertView.findViewById(R.id.personCompany);

            // save the mini-controller in the view
            convertView.setTag(viewHolder);
        }

        People people = getItem(position);

        CurrentStatesPeopleList currentStatesPeopleList = CurrentStatesPeopleList.getInstance();
        Map<String, String> companiesMap = currentStatesPeopleList.getCompaniesMap();


        assert people != null;
        String companyName = companiesMap.get(people.getIdCompany());
        Log.i(TAG, "companyName : " + companyName);

        viewHolder.LastName.setText(people.getLastname());
        viewHolder.FirstName.setText(people.getFirstname());
        viewHolder.Company.setText(companyName);

        return convertView;
    }

    class TweetViewHolder {
        TextView LastName;
        TextView FirstName;
        TextView Company;
    }
}

