package com.example.rvadam.pfe.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;

import com.example.rvadam.pfe.Model.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rvadam on 25/04/2018.
 */

public class DownloadTask extends AsyncTask {

    private Context context;
    private PowerManager.WakeLock mWakeLock;

    public DownloadTask(Context context) {
        this.context = context;
    }

    private static final String TAG="Download task";
    @Override
    protected String doInBackground(Object[] sUrl) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        Log.i(TAG,"Download task start");
        if(sUrl[0] instanceof String){
            String strUrl=(String) sUrl[0];
            try {
                URL url = new URL(strUrl);
                Log.i(TAG,"strUrl : "+strUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                Log.i(TAG,"after connection.connect()");
                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.i(TAG,"HTTP connection not OK");
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }
                Log.i(TAG,"after condition HTTP not OK");
                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                Log.i(TAG,"after connection.getInputStream()");
                Log.i(TAG,"environment dl directory"+ Environment.DIRECTORY_DOWNLOADS);
                Log.i(TAG,"environment doc directory"+ Environment.DIRECTORY_DOCUMENTS);

                /*File path = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS);*/
                File file = Constants.getInstance().getTmpFileDLFromOneDrive();
                output = new FileOutputStream(file);
                Log.i(TAG,"environment dl directory"+ Environment.DIRECTORY_DOWNLOADS);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                Log.i(TAG,"exception catch : "+e.getMessage());
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
    }

        return null;
    }
}
