package com.example.rvadam.pfe.Utils;

import com.example.rvadam.pfe.Model.CoursesAccessPhotoTypes;
import com.example.rvadam.pfe.Model.ListOfPhotosSingleton;
import com.example.rvadam.pfe.Model.MaltAdductionsPhotoTypes;
import com.example.rvadam.pfe.Model.PhotoCategories;
import com.example.rvadam.pfe.Model.SecurityPhotoTypes;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.Model.TechnicalEquipmentsPhotoTypes;

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

    public static List<SpacePhoto> getListOfPhotosByCategory(PhotoCategories category){

        switch (category){
            case COURSES_ACCESS:
                return ListOfPhotosSingleton.getInstance().getCoursesAccessPhotos();
            case MALT_ADDUCTIONS:
                return ListOfPhotosSingleton.getInstance().getMaltAdductionsPhotos();
            case SECURITY:
                return ListOfPhotosSingleton.getInstance().getSecurityPhotos();
            case TECHNICAL_EQUIPMENTS:
                return ListOfPhotosSingleton.getInstance().getTechnicalEquipmentsPhotos();
            case GENERAL_VIEW_ACCESS:
                return ListOfPhotosSingleton.getInstance().getGeneralViewAccessPhotos();
            default:;
        }

        return null;
    }

    /*public static int getNumberOfTypesByCategories(PhotoCategories category){

        switch (category){
            case GENERAL_VIEW_ACCESS:
                return 1;
            case COURSES_ACCESS:
                return CoursesAccessPhotoTypes.values().length;
            case SECURITY:
                return SecurityPhotoTypes.values().length;
            case MALT_ADDUCTIONS:
                return MaltAdductionsPhotoTypes.values().length;
            case TECHNICAL_EQUIPMENTS:
                return TechnicalEquipmentsPhotoTypes.values().length;
            default: return 0;
        }
    }*/

    public static List<String> getTypesByCategory(PhotoCategories category){
        List<String> result=new ArrayList<String>();
        switch (category){
            case GENERAL_VIEW_ACCESS:
                return result;
            case COURSES_ACCESS:
                CoursesAccessPhotoTypes[] array= CoursesAccessPhotoTypes.values();
                for(int i=0; i<array.length;i++){
                    result.add(array[i].getName());
                }
                return result;
            case SECURITY:
                SecurityPhotoTypes[] array1=SecurityPhotoTypes.values();
                for(int i=0; i<array1.length;i++){
                    result.add(array1[i].getName());
                }
                return result;
            case MALT_ADDUCTIONS:
                MaltAdductionsPhotoTypes[] array2=MaltAdductionsPhotoTypes.values();
                for(int i=0; i<array2.length;i++){
                    result.add(array2[i].getName());
                }
                return result;
            case TECHNICAL_EQUIPMENTS:
                TechnicalEquipmentsPhotoTypes[] array3=TechnicalEquipmentsPhotoTypes.values();
                for(int i=0; i<array3.length;i++){
                    result.add(array3[i].getName());
                }
                return result;
            default: return result;
        }
    }

    public static SpacePhoto getPhotoByName(String category, String photoName){

        PhotoCategories categ = PhotoCategories.valueOf(category);
        List<SpacePhoto> photosOfCategory = getListOfPhotosByCategory(categ);

        for(SpacePhoto photo : photosOfCategory){
            if(photo.getTitle().equals(photoName)){
                return photo;
            }
        }

        return null;
    }
}
