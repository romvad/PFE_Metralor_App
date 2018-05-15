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
    //private StorageReference mStorageReference;
    private String mTitle;
    private static final String TAG="SpacePhoto";

    public SpacePhoto(String url, String title) {
        mFBStorageUrl = url;
        mTitle = title;
    }
    /*public SpacePhoto(StorageReference mStorageReference, String title) {
        mStorageReference = mStorageReference;
        mTitle = title;
    }*/

    protected SpacePhoto(Parcel in) {
        mFBStorageUrl = in.readString();
        //mStorageReference=in.readString();
        mTitle = in.readString();
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

   /* public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }*/

    /*public StorageReference getmStorageReference() {
        return mStorageReference;
    }

    public void setmStorageReference(StorageReference mStorageReference) {
        this.mStorageReference = mStorageReference;
    }*/

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

    public static  SpacePhoto[] getSpacePhotos() {

        /*StorageReference mStorageRef= FirebaseStorage.getInstance().getReference("The other one/documents/SECURITY_DOCUMENTS");
        Log.i(TAG,"storage ref SPacePhoto "+mStorageRef);*/

        return new SpacePhoto[]{
                new SpacePhoto("The other one/documents/SECURITY_DOCUMENTS/GEM.png", "GEM.png"),
                new SpacePhoto("The other one/documents/SECURITY_DOCUMENTS/Avatar.png", "Avatar.png"),
                new SpacePhoto("The other one/documents/SECURITY_DOCUMENTS/rom.jpg", "rom.jpg"),
                new SpacePhoto("The other one/documents/SECURITY_DOCUMENTS/photo metralor.png", "photo metralor.png"),

        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mFBStorageUrl);
        parcel.writeString(mTitle);
    }
}
