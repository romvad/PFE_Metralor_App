package com.example.rvadam.pfe.PhotoHandler;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by rvadam on 18/05/2018.
 */

public class PictureHistory {
    private static ArrayList<Uri> history;
    private static PictureHistory instance;

    private PictureHistory(){
        history = new ArrayList<>();
    }

    public static PictureHistory getInstance(){
        if (instance == null)
            instance = new PictureHistory();

        return instance;
    }

    public void addPath(Uri uri){
        history.add(uri);
    }

    /*public String getLastPicturePath(){
        return history.get(history.size()-1);
    }*/

    public Uri getLastPicturePathUri(){
        return history.get(history.size()-1);
    }
}
