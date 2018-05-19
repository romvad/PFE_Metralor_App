package com.example.rvadam.pfe.PhotoHandler;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by rvadam on 18/05/2018.
 */

public class PictureHistory {
    private static ArrayList<String> history;
    private static PictureHistory instance;

    private PictureHistory(){
        history = new ArrayList<>();
    }

    public static PictureHistory getInstance(){
        if (instance == null)
            instance = new PictureHistory();

        return instance;
    }

    public void addPath(String s){
        history.add(s);
    }

    public String getLastPicturePath(){
        return history.get(history.size()-1);
    }

    public Uri getLastPicturePathUri(){
        return Uri.parse(history.get(history.size()-1));
    }
}
