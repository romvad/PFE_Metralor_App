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
            viewHolder.LastnamePeople = (TextView) convertView.findViewById(R.id.peopleInfo);
            convertView.setTag(viewHolder);
        }

        People people = getItem(position);

        assert people != null;
        viewHolder.LastnamePeople.setText(people.getLastname());

        return convertView;
    }

    class TweetViewHolder {
        public TextView LastnamePeople;
    }
}

