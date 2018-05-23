package com.example.rvadam.pfe.FirebaseDBHelpers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.rvadam.pfe.Model.CoursesAccessPhotoTypes;
import com.example.rvadam.pfe.Model.PhotoCategories;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rvadam on 23/05/2018.
 */

public class FirebaseDBImagesHelpers {
    private static final String TAG="FDBIMAGESHElper";

    public static void updateDownloadUrlForPhoto(final Activity activity, String worksiteId, String category, final String photoName, String downloadUrl){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference categRef = ref.child("workSites/"+worksiteId+"/"+convertFromCategoryToPhotoTypeNodeName(category));

        //Add or update of the value for the key "photoName"
        Map<String, Object> childMap= new HashMap<String, Object>();
        Log.i(TAG,"url replace "+replaceSlashWithTremaInUrl(downloadUrl));
        childMap.put(replacePointWithSemiColum(photoName),replaceSlashWithTremaInUrl(downloadUrl));
        categRef.updateChildren(childMap).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity,"Echec de l'obtention du lien de téléchargement pour l'image "+photoName,Toast.LENGTH_LONG).show();
            }
        });

    }

    /*private String convertFromTypeToPhotoTypeNodeName(String type){

        switch (type){
            case "WORKSITE_ACCESS":
                return "worksiteAccessPhotosMap";
            case "MEANS_OF_ACCESS":
                return "meansOfAccessPhotosMap";
            case "AIR_ACCESS":
                return "airAccessPhotosMap";
            case "TECHNICAL_ZONE_ACCESS":
                return "technicalZoneAccessPhotosMap";
            case "RF_MODULES":
                return "rfModulesPhotosMap";
            case "ENERGY":
                return "energyPhotosMap";
            case "TRANSMISSION":
                return "transmissionPhotosMap";
            case "ANTI_FALL":
                return "antiFallPhotosMap";
            case "ANTI_INTRUSION":
                return "antiIntrusionPhotosMap";
            case "SECURITY_PERIMETER":
                return "securityPerimeterPhotosMap";
            case "SIGNALETICS":
                return "signaleticsPhotosMap";
            case "LIGHTS":
                return "lightsPhotosMap";
            case "GROUNDS":
                return "groundsPhotosMap";
            case "DIVERS":
                return "diversPhotosMap";
            case "TECHNICAL_EQUIPMENTS_PLACES":
                return "technicalEquipmentsPlacesPhotosMap";
            case "DIVISIONARY_BOARD":
                return "divisionaryBoardPhotosMap";
            case "COURSES_DIVERS":
                return "coursesDiversPhotosMap";
            default: return null;
        }
    }*/

    private static String replaceSlashWithTremaInUrl(String url){
        return url.replace("/","¨");
    }

    private static String replacePointWithSemiColum(String key){
        return key.replace(".",";");
    }

    public static String convertFromCategoryToPhotoTypeNodeName(String categ){

        PhotoCategories category = PhotoCategories.valueOf(categ);
        switch (category){
            case COURSES_ACCESS:
                return "coursesAccessPhotosMap";
            case SECURITY:
                return "securityPhotosMap";
            case TECHNICAL_EQUIPMENTS:
                return "technicalEquipmentsPhotosMap";
            case GENERAL_VIEW_ACCESS:
                return "generalViewAccessPhotosMap";
            case MALT_ADDUCTIONS:
                return "maltAdductionsPhotosMap";

            default: return null;
        }
    }


}
