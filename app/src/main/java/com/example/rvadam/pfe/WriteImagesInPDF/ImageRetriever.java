package com.example.rvadam.pfe.WriteImagesInPDF;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.Toast;

import com.example.rvadam.pfe.Model.ListOfPhotosSingleton;
import com.example.rvadam.pfe.Model.PhotoCategories;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.ListOfPhotosSingletonManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by rvadam on 20/05/2018.
 */

public class ImageRetriever extends AsyncTask<String, Integer, Bitmap> {

    private WriteImagesInPDFActivity activity;
    private URL url;
    private ProgressDialog progressDialog;//Progress Dialog of the WriteImagesInPDFActivity
    private static final String TAG = "ImageRetriever";
    private PhotoCategories category;
    private int nbOfCall;

    public ImageRetriever(WriteImagesInPDFActivity activity, PhotoCategories category,int nbOfCall) {
        this.activity = activity;
        this.progressDialog = activity.getProgressDialog();
        this.category = category;
        this.nbOfCall=nbOfCall;

    }

    @Override
    protected Bitmap doInBackground(String[] urls) {
        Bitmap result=null;

        //for (int i = 0; i < urls.length; i++) {
            try {
                url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                result = BitmapFactory.decodeStream(input);
            } catch (MalformedURLException e) {
                e.printStackTrace();
               // Toast.makeText(activity, "Erreur d'URL de la photo", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                //Toast.makeText(activity, "Echec de la connexion", Toast.LENGTH_LONG).show();
            }
       // }


        return result;


    }

   /* @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);

    }*/

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(nbOfCall==0) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setTitle(activity.getResources().getString(R.string.progress_dialog_photos_retrivement));
            progressDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i(TAG, "value progress " + values[0]);
        progressDialog.setProgress(values[0]);

    }

    @Override
    protected void onPostExecute(Bitmap result) {

        Map<SpacePhoto, Bitmap> bitmapMap = activity.getBitmaps();
        List<SpacePhoto> keysMap = activity.getListMapKeys(); //indexes of bitmaps in result matches the order of keys of bitmapMap in keysMap
        //for (int i = 0; i < result.length; i++) {
            if (result != null) {
                super.onPostExecute(result);
                bitmapMap.put(keysMap.get(nbOfCall), result);
            }
        //}

        if(nbOfCall==activity.getNbPhotosToPrint()){
            activity.printDocument(category);
            progressDialog.dismiss();
        }



    }
}
