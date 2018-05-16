package com.example.rvadam.pfe.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvadam on 02/05/2018.
 */

public enum PhotoCategories /*implements Parcelable*/{
    GENERAL_VIEW_ACCESS,COURSES_ACCESS,MALT_ADDUCTIONS,SECURITY,TECHNICAL_EQUIPMENTS;

    /*private String category;

    PhotoCategories(String category){
        this.category = category;
    }

    public String getName(){
        return category;
    }

    private void setCategory(String category){
        this.category = category;
    }

    public static final Parcelable.Creator<PhotoCategories> CREATOR = new Parcelable.Creator<PhotoCategories>() {

        public PhotoCategories createFromParcel(Parcel in) {
            PhotoCategories category = PhotoCategories.values()[in.readInt()];
            category.setCategory(in.readString());
            return category;
        }

        public PhotoCategories[] newArray(int size) {
            return new PhotoCategories[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(ordinal());
        out.writeString(category);
    }*/
}
