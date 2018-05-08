package com.example.rvadam.pfe.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.R;

import java.util.List;

/**
 * Created by rvadam on 08/05/2018.
 */

public class CustomDocumentsListAdapter extends ArrayAdapter<Document> {
    List<Document> listOfDocs;
    Context context;

    public CustomDocumentsListAdapter(@NonNull Context context, int resource, @NonNull List<Document> listOfDocs, String typeOfDocs) {
        super(context, resource, listOfDocs);
        this.listOfDocs=listOfDocs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View row;
        if(convertView==null) {

            LayoutInflater inflater = (LayoutInflater.from(context));
            row = inflater.inflate(R.layout.item_list_view, null);
        }else{
            row=convertView;
        }
}
