package com.example.rvadam.pfe.WelcomeListWorkSites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rvadam.pfe.R;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 04/05/2018.
 */

public class CustomArrayAdapterWorkSite extends ArrayAdapter<WorkSite> {

    public CustomArrayAdapterWorkSite(Context context, ArrayList<WorkSite> listOfWorksites) {
        super(context, 0, listOfWorksites);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new TweetViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.longitude = (TextView) convertView.findViewById(R.id.longitude);
            viewHolder.latitude = (TextView) convertView.findViewById(R.id.latitude);
            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
            convertView.setTag(viewHolder);
        }

        WorkSite workSite = getItem(position);

        assert workSite != null;
        viewHolder.name.setText(workSite.getWorksiteName());
        viewHolder.longitude.setText(String.valueOf(workSite.getWorksiteLongitude()));
        viewHolder.latitude.setText(String.valueOf(workSite.getWorksiteLatitude()));
        viewHolder.type.setText(workSite.getWorksiteType());

        return convertView;
    }

    class TweetViewHolder {
        public TextView name;
        public TextView longitude;
        public TextView latitude;
        public TextView type;
    }
}
