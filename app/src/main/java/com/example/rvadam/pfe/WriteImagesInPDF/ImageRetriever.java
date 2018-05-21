package com.example.rvadam.pfe.WriteImagesInPDF;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.Toast;

import com.example.rvadam.pfe.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rvadam on 20/05/2018.
 */

public class ImageRetriever extends AsyncTask<String,Integer,Bitmap> {

    private WriteImagesInPDFActivity activity;
    private URL url;
    private ProgressDialog progressDialog;//Progress Dialog of the WriteImagesInPDFActivity
    private static final String TAG = "ImageRetriever";

    public ImageRetriever(WriteImagesInPDFActivity activity) {
        this.activity = activity;
        this.progressDialog= activity.getProgressDialog();

        progressDialog =new ProgressDialog(activity);
        progressDialog.setTitle(activity.getResources().getString(R.string.progress_dialog_photos_retrivement));
        progressDialog.show();

    }

    @Override
    protected Bitmap doInBackground(String[] urls) {
        Bitmap result = null;

        try {
            url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            result=BitmapFactory.decodeStream(input);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(activity,"Erreur d'URL de la photo",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(activity,"Echec de la connexion",Toast.LENGTH_LONG).show();
        }

        return result;


    }

   /* @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);

    }*/

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i(TAG,"value progress "+values[0]);
        progressDialog.setProgress(values[0]);

    }

    @Override
    protected void onPostExecute(Bitmap result) {

        progressDialog.dismiss();
        if(result!=null){
            super.onPostExecute(result);
            activity.getBitmap().add(result);
            activity.printDocument();
        }



    }
}
