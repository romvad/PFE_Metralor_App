package com.example.rvadam.pfe.FirebaseStorageHelper;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.rvadam.pfe.Model.PhotoCategories;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.Utils.ListOfPhotosSingletonManager;
import com.example.rvadam.pfe.WriteImagesInPDF.WriteImagesInPDFActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rvadam on 21/05/2018.
 */

public class FirebaseStoragePhotosHelpers {

    private WriteImagesInPDFActivity activity;
    int counter=0; //Counter that indicates when the ImageRetriever in WriteImagesInPDFActivity can be called

    public FirebaseStoragePhotosHelpers(WriteImagesInPDFActivity activity) {
        this.activity = activity;
    }

    public void defineSpacePhotoUrlDownloadsByPhotoCategories(final PhotoCategories category){
        final List<SpacePhoto> photosToGetUrlDl= ListOfPhotosSingletonManager.getListOfPhotosByCategory(category);
        Map<SpacePhoto,Bitmap> listOfBitmaps= activity.getBitmaps();
        listOfBitmaps.clear();//Suppress potential former bitmaps loaded for another work site



        for(final SpacePhoto photo : photosToGetUrlDl){
            String fullPathFirebaseStorage=photo.getmIdWorkSite()+"/"+photo.getmFBStorageUrl();
            StorageReference ref = FirebaseStorage.getInstance().getReference(fullPathFirebaseStorage);
            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    photo.setDownloadURL(uri.toString());
                    if(counter==photosToGetUrlDl.size())
                        activity.callImageRetriever(category);
                    counter++;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(counter==photosToGetUrlDl.size())
                        activity.callImageRetriever(category);
                    counter++;
                }
            });
        }
    }
}
