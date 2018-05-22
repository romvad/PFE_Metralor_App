package com.example.rvadam.pfe.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by rvadam on 15/05/2018.
 */

public class SpacePhoto implements Parcelable {
    private String mFBStorageUrl;
    private String mphotoType;
    private String mIdWorkSite;
    //private StorageReference mStorageReference;
    private String mTitle;
    private static final String TAG="SpacePhoto";
    private String downloadURL;

    public SpacePhoto(String FBStorageUrl, String title, String photoType, String idWorkSite ) {
        mFBStorageUrl = FBStorageUrl;
        mTitle = title;
        mphotoType=photoType;
        mIdWorkSite=idWorkSite;
    }
    /*public SpacePhoto(StorageReference mStorageReference, String title) {
        mStorageReference = mStorageReference;
        mTitle = title;
    }*/

    protected SpacePhoto(Parcel in) {
        mFBStorageUrl = in.readString();
        //mStorageReference=in.readString();
        mTitle = in.readString();
        mphotoType = in.readString();
        mIdWorkSite = in.readString();
    }

    public static final Creator<SpacePhoto> CREATOR = new Creator<SpacePhoto>() {
        @Override
        public SpacePhoto createFromParcel(Parcel in) {
            return new SpacePhoto(in);
        }

        @Override
        public SpacePhoto[] newArray(int size) {
            return new SpacePhoto[size];
        }
    };



    public String getmFBStorageUrl() {
        return mFBStorageUrl;
    }

    public void setmFBStorageUrl(String mFBStorageUrl) {
        this.mFBStorageUrl = mFBStorageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getMphotoType() {
        return mphotoType;
    }

    public void setMphotoType(String mphotoType) {
        this.mphotoType = mphotoType;
    }

    public String getmIdWorkSite() {
        return mIdWorkSite;
    }

    public void setmIdWorkSite(String mIdWorkSite) {
        this.mIdWorkSite = mIdWorkSite;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    /*public static  SpacePhoto[] getSpacePhotos() {

        /*StorageReference mStorageRef= FirebaseStorage.getInstance().getReference("The other one/documents/SECURITY_DOCUMENTS");
        Log.i(TAG,"storage ref SPacePhoto "+mStorageRef);*/

       /* return new SpacePhoto[]{
                new SpacePhoto("The other one/documents/SECURITY_DOCUMENTS/photo metralor.png", "GEM.png"),
                new SpacePhoto("The other one/documents/SECURITY_DOCUMENTS/vagur.jpg", "Avatar.png"),
                new SpacePhoto("The other one/documents/SECURITY_DOCUMENTS/bg.jpg", "rom.jpg"),
                new SpacePhoto("The other one/documents/SECURITY_DOCUMENTS/homer.png", "photo metralor.png"),
                //new SpacePhoto("The other one/documents/SECURITY_DOCUMENTS/Metralor - fiche PFE.pdf","yui"),

        };
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mFBStorageUrl);
        parcel.writeString(mTitle);
        parcel.writeString(mphotoType);
        parcel.writeString(mIdWorkSite);
    }
}
