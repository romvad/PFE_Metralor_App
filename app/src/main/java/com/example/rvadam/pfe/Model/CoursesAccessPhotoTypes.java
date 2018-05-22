package com.example.rvadam.pfe.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rvadam on 16/05/2018.
 */

public enum CoursesAccessPhotoTypes {
    WORKSITE_ACCESS("ACCES AU CHANTIER") ,MEANS_OF_ACCESS("MOYENS d'ACCES"),AIR_ACCESS("ACCESS AUX AERIENS"),TECHNICAL_ZONE_ACCESS("ACCES A LA ZONE TECHNIQUE"),RF_MODULES("MODULES RF");

    private String type;

    CoursesAccessPhotoTypes(String type){
        this.type = type;
    }

    public String getName(){
        return type;
    }

    private void setCategory(String category){
        this.type = category;
    }
}
