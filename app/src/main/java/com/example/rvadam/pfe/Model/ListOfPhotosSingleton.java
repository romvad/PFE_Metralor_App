package com.example.rvadam.pfe.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvadam on 16/05/2018.
 */

public class ListOfPhotosSingleton {

    private static final ListOfPhotosSingleton instance=new ListOfPhotosSingleton();
    private static final String TAG ="ListOfPhotosSingleton" ;
    private List<SpacePhoto> coursesAccessPhotos= new ArrayList<SpacePhoto>();
    private List<SpacePhoto> generalViewAccessPhotos=new ArrayList<SpacePhoto>();
    private List<SpacePhoto> technicalEquipmentsPhotos=new ArrayList<SpacePhoto>();
    private List<SpacePhoto> maltAdductionsPhotos=new ArrayList<SpacePhoto>();
    private List<SpacePhoto> securityPhotos=new ArrayList<SpacePhoto>();

    public ListOfPhotosSingleton() {

        //GENERAL VIEW ACCESS PHOTOS
        for (String s: Constants.getInstance().getGeneralViewAccessPhotoNames()){
            String FBStorageURL=String.valueOf(PhotoCategories.GENERAL_VIEW_ACCESS)+"/"+s;
            generalViewAccessPhotos.add(new SpacePhoto(FBStorageURL,s,"",""));
        }

        //ACCESS COURSES PHOTOS
        for(int i=0;i<CoursesAccessPhotoTypes.values().length;i++){
            CoursesAccessPhotoTypes type= CoursesAccessPhotoTypes.values()[i];
            Log.i(TAG,"type retrieved "+type);
            Log.i(TAG,"size map courses access photos "+Constants.getInstance().getCoursesAccessPhotoNames().size());
            List<String> list=Constants.getInstance().getCoursesAccessPhotoNames().get(type);
            Log.i(TAG, "list retrieved "+list);
            for(String s : list){
                String FBStorageURL=String.valueOf(PhotoCategories.COURSES_ACCESS)+"/"+String.valueOf(type)+"/"+s;
                coursesAccessPhotos.add(new SpacePhoto(FBStorageURL,s,String.valueOf(type),""));
            }

        }

        // MAL ADDUCTIONS PHOTOS
        for(int i=0;i<MaltAdductionsPhotoTypes.values().length;i++){
            MaltAdductionsPhotoTypes type= MaltAdductionsPhotoTypes.values()[i];
            List<String> list=Constants.getInstance().getMaltAdductionsPhotoNames().get(type);

            for(String s : list){
                String FBStorageURL=String.valueOf(PhotoCategories.MALT_ADDUCTIONS)+"/"+String.valueOf(type)+"/"+s;
                maltAdductionsPhotos.add(new SpacePhoto(FBStorageURL,s,String.valueOf(type),""));
            }

        }

        //SECURITY PHOTOS
        for(int i=0;i<SecurityPhotoTypes.values().length;i++){
            SecurityPhotoTypes type= SecurityPhotoTypes.values()[i];
            List<String> list=Constants.getInstance().getSecurityPhotoNames().get(type);

            for(String s : list){
                String FBStorageURL=String.valueOf(PhotoCategories.SECURITY)+"/"+String.valueOf(type)+"/"+s;
                securityPhotos.add(new SpacePhoto(FBStorageURL,s,String.valueOf(type),""));
            }

        }

        //TECHNICAL EQUIPMENTS PHOTOS
        for(int i=0;i<TechnicalEquipmentsPhotoTypes.values().length;i++){
            TechnicalEquipmentsPhotoTypes type= TechnicalEquipmentsPhotoTypes.values()[i];
            List<String> list=Constants.getInstance().getTechnicalEquipmentsPhotoNames().get(type);

            for(String s : list){
                String FBStorageURL=String.valueOf(PhotoCategories.TECHNICAL_EQUIPMENTS)+"/"+String.valueOf(type)+"/"+s;
                technicalEquipmentsPhotos.add(new SpacePhoto(FBStorageURL,s,String.valueOf(type),""));
            }

        }


    }

    public static ListOfPhotosSingleton getInstance() {
        return instance;
    }

    public List<SpacePhoto> getCoursesAccessPhotos() {
        return coursesAccessPhotos;
    }

    public List<SpacePhoto> getGeneralViewAccessPhotos() {
        return generalViewAccessPhotos;
    }

    public List<SpacePhoto> getTechnicalEquipmentsPhotos() {
        return technicalEquipmentsPhotos;
    }

    public List<SpacePhoto> getMaltAdductionsPhotos() {
        return maltAdductionsPhotos;
    }

    public List<SpacePhoto> getSecurityPhotos() {
        return securityPhotos;
    }
}
