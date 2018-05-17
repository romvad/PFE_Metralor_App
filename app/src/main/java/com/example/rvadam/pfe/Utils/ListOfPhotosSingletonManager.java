package com.example.rvadam.pfe.Utils;

import com.example.rvadam.pfe.Model.CoursesAccessPhotoTypes;
import com.example.rvadam.pfe.Model.ListOfPhotosSingleton;
import com.example.rvadam.pfe.Model.PhotoCategories;
import com.example.rvadam.pfe.Model.SpacePhoto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvadam on 17/05/2018.
 */

public class ListOfPhotosSingletonManager {

    public static void updateWorkSiteIdListOfPhotosSingleton(String idWorkSite){

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getCoursesAccessPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getGeneralViewAccessPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getMaltAdductionsPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getSecurityPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getTechnicalEquipmentsPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }
    }

    public static List<SpacePhoto> getListOfPhotosByCategoryAndType(PhotoCategories category, String type){
        List<SpacePhoto> result=new ArrayList<SpacePhoto>();
        List<SpacePhoto> listSingleton=new ArrayList<SpacePhoto>();
        switch (category){
            case COURSES_ACCESS:
                listSingleton=ListOfPhotosSingleton.getInstance().getCoursesAccessPhotos();
                for(SpacePhoto photo : listSingleton){
                    if (photo.getMphotoType().equals(type)){
                        result.add(photo);
                    }
                }
                break;
            case MALT_ADDUCTIONS:
                listSingleton=ListOfPhotosSingleton.getInstance().getMaltAdductionsPhotos();
                for(SpacePhoto photo : listSingleton){
                    if (photo.getMphotoType().equals(type)){
                        result.add(photo);
                    }
                }
                break;
            case SECURITY:
                listSingleton=ListOfPhotosSingleton.getInstance().getSecurityPhotos();
                for(SpacePhoto photo : listSingleton){
                    if (photo.getMphotoType().equals(type)){
                        result.add(photo);
                    }
                }
                break;
            case TECHNICAL_EQUIPMENTS:
                listSingleton=ListOfPhotosSingleton.getInstance().getTechnicalEquipmentsPhotos();
                for(SpacePhoto photo : listSingleton){
                    if (photo.getMphotoType().equals(type)){
                        result.add(photo);
                    }
                }
                break;
            case GENERAL_VIEW_ACCESS:
                result=ListOfPhotosSingleton.getInstance().getGeneralViewAccessPhotos();
                break;
            default:;
        }

        return result;
    }
}
