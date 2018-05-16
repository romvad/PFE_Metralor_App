package com.example.rvadam.pfe.ListPeople;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rvadam.pfe.Model.People;
import com.example.rvadam.pfe.R;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 13/05/2018.
 */

public class PeopleCustomAdapter extends ArrayAdapter<People> {
    private ArrayAdapter<People> listOfPeople;

    public PeopleCustomAdapter(Context context, ArrayList<People> listOfPeople) {
        super(context, 0, listOfPeople);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

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

        assert people != null;
        viewHolder.LastName.setText(people.getLastname());
        viewHolder.FirstName.setText(people.getFirstname());
        viewHolder.Company.setText(people.getIdCompany());

        return convertView;
    }

    class TweetViewHolder {
        public TextView LastName;
        public TextView FirstName;
        public TextView Company;
    }
}

