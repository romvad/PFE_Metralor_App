package com.example.rvadam.pfe.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvadam on 02/05/2018.
 */

public enum PhotoCategories /*implements Parcelable*/{
    GENERAL_VIEW_ACCESS("ACCES VUE GENERALE"),COURSES_ACCESS("ACCES CHEMINEMENT"),MALT_ADDUCTIONS("MALT ADDUCTIONS"),SECURITY("SECURITE"),TECHNICAL_EQUIPMENTS("EQUIPEMENTS TECHNIQUES");

    private String category;

    PhotoCategories(String category){
        this.category = category;
    }

    public String getName(){
        return category;
    }

    private void setCategory(String category){
        this.category = category;
    }



}
