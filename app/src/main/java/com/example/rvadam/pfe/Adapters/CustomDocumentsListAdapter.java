package com.example.rvadam.pfe.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.Model.DocumentTypes;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.InternetConnectionTools;
import com.example.rvadam.pfe.WorkSiteListOfDocuments.TabsOfListOfDocumentsActivity;
import com.microsoft.identity.client.PublicClientApplication;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvadam on 08/05/2018.
 */

public class CustomDocumentsListAdapter extends ArrayAdapter<Document> {
    List<Document> listOfDocs;
    Context context;
    TabsOfListOfDocumentsActivity tabsOfListOfDocumentsActivity;
    DocumentTypes typeOfDocs;

    private int currentPosition=-1;

    private static final int PICK_IMAGE_REQUEST = 234;
    /* Azure AD Variables */


    private static final String TAG="CustomDocumentsAdapter";
    private TextView statusChoosedFile;

    public CustomDocumentsListAdapter(@NonNull Context context, int resource, @NonNull List<Document> listOfDocs, DocumentTypes typeOfDocs) {
        super(context, resource, listOfDocs);
        this.listOfDocs = listOfDocs;
        this.typeOfDocs=typeOfDocs;
        this.context=context;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        currentPosition=position;
        View row;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater.from(context));
            row = inflater.inflate(R.layout.item_list_documents, null);
        } else {
            row = convertView;
        }

        Document currentDocument=listOfDocs.get(position);

        tabsOfListOfDocumentsActivity= (TabsOfListOfDocumentsActivity) context;

        Button chooseButton = (Button) row.findViewById(R.id.chooseButton);
        Button uploadButton = (Button) row.findViewById(R.id.uploadButton);
        TextView nameChoosedFile = (TextView) row.findViewById(R.id.nameChoosedFile);
        statusChoosedFile = (TextView) row.findViewById(R.id.statusChoosedFile);
        TextView nameSavedFile= (TextView) row.findViewById(R.id.nameUploadedFile);
        TextView nameDocument =(TextView) row.findViewById(R.id.nameDocument);

        nameChoosedFile.setText(currentDocument.getNameChoosedFile());
        statusChoosedFile.setText(String.valueOf(currentDocument.getStatus()));
        nameSavedFile.setText(currentDocument.getFileName());
        nameDocument.setText(currentDocument.getName());

        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildAlertDialog(position);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabsOfListOfDocumentsActivity.uploadFile(typeOfDocs,position);
            }
        });

        return row;
    }

    private void buildAlertDialog(final int documentPosition) {
        List<String> listOptions = new ArrayList<String>();
        listOptions.add("Interne");
        listOptions.add("OneDrive");
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        //LayoutInflater inflater = getLayoutInflater();
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.list_choosefile_dialog, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(R.string.title_choice_file);
        ListView lv = (ListView) convertView.findViewById(R.id.listViewChooseFileDialog);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        CustomChooseFileListAdapter adapter = new CustomChooseFileListAdapter(context, android.R.layout.simple_list_item_1, listOptions);
        lv.setAdapter(adapter);
        final AlertDialog alertDialogCreated = alertDialog.create();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        //Toast.makeText(getApplicationContext(), "interne", Toast.LENGTH_LONG);
                        Log.i(TAG,"interne");
                        showFileChooser(documentPosition);
                        alertDialogCreated.dismiss();
                        break;
                    case 1:
                        //Toast.makeText(getApplicationContext(), "oneDrive", Toast.LENGTH_LONG);
                        Log.i(TAG,"oneDrive");
                        if(InternetConnectionTools.isNetworkAvailable((AppCompatActivity)context)){
                            tabsOfListOfDocumentsActivity.startDownloadFileFromOneDrive(documentPosition,typeOfDocs.getValue());
                            alertDialogCreated.dismiss();
                        }else {
                            Toast.makeText(tabsOfListOfDocumentsActivity, R.string.download_from_one_drive_offline_impossible, Toast.LENGTH_LONG).show();
                        }

                        break;
                    default:
                        ;

                }
            }
        });


        alertDialogCreated.setButton(DialogInterface.BUTTON_POSITIVE,"annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogCreated.dismiss();
            }

        });


        alertDialogCreated.show();

        Log.i(TAG, "builder alert dialog called");
    }


    private void showFileChooser(int documentPosition) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        tabsOfListOfDocumentsActivity.setPositionChoosedDocument(documentPosition);
        tabsOfListOfDocumentsActivity.setTypeOfChoosedDocument(typeOfDocs.getValue());
        /*intent.putExtra("position doc",documentPosition);
        intent.putExtra("type of doc", typeOfDocs.getValue());*/
        //((Activity)context).startActivityForResult(Intent.createChooser(intent, "select an image"), PICK_IMAGE_REQUEST);
        tabsOfListOfDocumentsActivity.startActivityForResult(Intent.createChooser(intent, "select an image"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


}