package com.example.rvadam.pfe.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rvadam.pfe.R;

import java.util.List;

/**
 * Created by rvadam on 03/05/2018.
 */

public class CustomChooseFileListAdapter extends ArrayAdapter<String>    {

    Context context;
    List<String> listChoice;

    public CustomChooseFileListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> listChoice) {
        super(context, resource, listChoice);
        this.context=context;
        this.listChoice=listChoice;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View row;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater.from(context));
            row = inflater.inflate(R.layout.custom_item_choosefile_dialog, null);
        } else {
            row = convertView;
        }

        ImageView imageView = (ImageView) row.findViewById(R.id.iconChoice);
        TextView textView = (TextView) row.findViewById(R.id.optionChoice);

        String optionString = listChoice.get(position);

        int iconSrc=0;
        int optionResource=0;
        switch (optionString) {
            case "Interne":
                iconSrc = R.mipmap.ic_phone;
                optionResource = R.string.option_internal_choice_file;
                break;
            case "OneDrive":
                iconSrc = R.mipmap.ic_one_drive_app;
                optionResource = R.string.option_onedrive_choice_file;
                break;
            default:
                ;
        }
        textView.setText(optionResource);
        imageView.setImageResource(iconSrc);

        return row;
    }


}
