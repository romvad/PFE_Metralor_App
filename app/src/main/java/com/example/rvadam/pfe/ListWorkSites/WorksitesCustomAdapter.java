package com.example.rvadam.pfe.ListWorkSites;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 09/05/2018.
 */

public class WorksitesCustomAdapter extends ArrayAdapter<WorkSite> {
    private static final String TAG = "WorksitesCustomAdapter";

    WorksitesCustomAdapter(Context context, ArrayList<WorkSite> listOfWorksites) {
        super(context, 0, listOfWorksites);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.worksite_info, parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new WorksitesCustomAdapter.TweetViewHolder();

            // Get our sub views
            viewHolder.name = (TextView) convertView.findViewById(R.id.worksiteName);
            viewHolder.type = (TextView) convertView.findViewById(R.id.worksiteType);
            viewHolder.vic = (TextView) convertView.findViewById(R.id.worksiteVIC);

            // save the mini-controller in the view
            convertView.setTag(viewHolder);
        }

        WorkSite workSite = getItem(position);

        assert workSite != null;
        viewHolder.name.setText(workSite.getName());
        viewHolder.type.setText(workSite.getType());
        viewHolder.vic.setText(String.valueOf(workSite.getDateVIC()));

        return convertView;
    }

    class TweetViewHolder {
        TextView name;
        TextView type;
        TextView vic;
    }
}

