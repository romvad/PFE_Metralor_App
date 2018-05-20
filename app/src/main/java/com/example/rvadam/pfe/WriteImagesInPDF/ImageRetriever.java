package com.example.rvadam.pfe.WriteImagesInPDF;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rvadam on 20/05/2018.
 */

public class ImageRetriever extends AsyncTask<String,Void,Object> {

    private WriteImagesInPDFActivity activity;
    private URL url;

    public ImageRetriever(WriteImagesInPDFActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Object doInBackground(String[] urls) {
        try {
            url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            activity.getBitmap().add(BitmapFactory.decodeStream(input));

        } catch (IOException e) {
            e.printStackTrace();
        }
            //return myBitmap;

        return null;
    }

    @Override
    protected void onPostExecute(Object object) {

            //URL url = new URL(urls[0]);

        activity.getButton().setEnabled(true);
    }
}
