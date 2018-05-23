package com.example.rvadam.pfe.FirebaseStorageHelper;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rvadam.pfe.FirebaseDBHelpers.FirebaseDBImagesHelpers;
import com.example.rvadam.pfe.Model.PhotoCategories;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.Utils.ListOfPhotosSingletonManager;
import com.example.rvadam.pfe.WriteImagesInPDF.WriteImagesInPDFActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by rvadam on 21/05/2018.
 */

public class FirebaseStoragePhotosHelpers {

    private WriteImagesInPDFActivity activity;
    int counter=0; //Counter that indicates when the ImageRetriever in WriteImagesInPDFActivity can be called
    private static final String TAG="FBStorageHelper";

    public FirebaseStoragePhotosHelpers(WriteImagesInPDFActivity activity) {
        this.activity = activity;
    }

    public void retrieveSpacePhotoUrlDownloadsByPhotoCategories(final PhotoCategories category, String idWorksite){
      /*  final List<SpacePhoto> photosToGetUrlDl= ListOfPhotosSingletonManager.getListOfPhotosByCategory(category);
        Map<SpacePhoto,Bitmap> listOfBitmaps= activity.getBitmaps();
        listOfBitmaps.clear();//Suppress potential former bitmaps loaded for another work site



        for(final SpacePhoto photo : photosToGetUrlDl){
            String fullPathFirebaseStorage=photo.getmIdWorkSite()+"/"+photo.getmFBStorageUrl();
            StorageReference ref = FirebaseStorage.getInstance().getReference();
            StorageReference refPhoto = ref.child(fullPathFirebaseStorage);
            refPhoto.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Log.i(TAG,"dl url "+ uri.toString());
                    photo.setDownloadURL(uri.toString());
                    if(counter==photosToGetUrlDl.size()-1)
                        activity.callImageRetriever(category);
                    counter++;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i(TAG,"dl url fail ");
                    if(counter==photosToGetUrlDl.size()-1)
                        activity.callImageRetriever(category);
                    counter++;
                }
            });
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference photoDLUrls = ref.child("workSites/"+idWorksite+"/"+ FirebaseDBImagesHelpers.convertFromCategoryToPhotoTypeNodeName(String.valueOf(category)));
        photoDLUrls.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataList = dataSnapshot.getChildren();
                Iterator<DataSnapshot> it = dataList.iterator();

                while (it.hasNext()) {
                    DataSnapshot snap = it.next();
                    String photoName = snap.getKey();
                    SpacePhoto photo = ListOfPhotosSingletonManager.getPhotoByName(String.valueOf(category),photoName);
                    photo.setDownloadURL(replaceTremaWithSlashUrl(snap.getValue(String.class)));
                }

                activity.callImageRetriever(category);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private String replaceTremaWithSlashUrl(String url){
        return url.replace("¨","/");
    }


}
