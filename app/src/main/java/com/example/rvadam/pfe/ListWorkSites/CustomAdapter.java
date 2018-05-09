package com.example.rvadam.pfe.ListWorkSites;

import android.content.Context;
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

public class CustomAdapter extends ArrayAdapter<WorkSite> {

    public CustomAdapter(Context context, ArrayList<WorkSite> listOfWorksites) {
        super(context, 0, listOfWorksites);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.worksite_info, parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new TweetViewHolder();
            viewHolder.NameWorksite = (TextView) convertView.findViewById(R.id.worksiteInfo);
            convertView.setTag(viewHolder);
        }

        WorkSite workSite = getItem(position);

        assert workSite != null;
        viewHolder.NameWorksite.setText(workSite.getName());

        return convertView;
    }

    class TweetViewHolder {
        public TextView NameWorksite;
    }
}
