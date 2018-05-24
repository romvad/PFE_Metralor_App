package com.example.rvadam.pfe.Utils;

import com.example.rvadam.pfe.Model.CoursesAccessPhotoTypes;
import com.example.rvadam.pfe.Model.MaltAdductionsPhotoTypes;
import com.example.rvadam.pfe.Model.PhotoCategories;
import com.example.rvadam.pfe.Model.SecurityPhotoTypes;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.Model.TechnicalEquipmentsPhotoTypes;
import com.microsoft.identity.client.PublicClientApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rvadam on 21/05/2018.
 */

public class WriteImagesInPDFTools {

    public static final Map<String, Integer> nbPagesByPhotoTypes;
    static {
        nbPagesByPhotoTypes=new HashMap<String, Integer>();
        nbPagesByPhotoTypes.put(CoursesAccessPhotoTypes.TECHNICAL_ZONE_ACCESS.getName(),1);
        nbPagesByPhotoTypes.put(CoursesAccessPhotoTypes.AIR_ACCESS.getName(),2);
        nbPagesByPhotoTypes.put(CoursesAccessPhotoTypes.RF_MODULES.getName(),1);
        nbPagesByPhotoTypes.put(CoursesAccessPhotoTypes.WORKSITE_ACCESS.getName(),1);
        nbPagesByPhotoTypes.put(CoursesAccessPhotoTypes.MEANS_OF_ACCESS.getName(),1);
        nbPagesByPhotoTypes.put(MaltAdductionsPhotoTypes.ENERGY.getName(),1);
        nbPagesByPhotoTypes.put(MaltAdductionsPhotoTypes.TRANSMISSION.getName(),2);
        nbPagesByPhotoTypes.put(SecurityPhotoTypes.ANTI_FALL.getName(),1);
        nbPagesByPhotoTypes.put(SecurityPhotoTypes.ANTI_INTRUSION.getName(),1);
        nbPagesByPhotoTypes.put(SecurityPhotoTypes.GROUNDS.getName(),1);
        nbPagesByPhotoTypes.put(SecurityPhotoTypes.DIVERS.getName(),1);
        nbPagesByPhotoTypes.put(SecurityPhotoTypes.SECURITY_PERIMETER.getName(),1);
        nbPagesByPhotoTypes.put(SecurityPhotoTypes.SIGNALETICS.getName(),1);
        nbPagesByPhotoTypes.put(SecurityPhotoTypes.LIGHTS.getName(),1);
        nbPagesByPhotoTypes.put(TechnicalEquipmentsPhotoTypes.TECHNICAL_EQUIPMENTS_PLACES.getName(),2);
        nbPagesByPhotoTypes.put(TechnicalEquipmentsPhotoTypes.COURSES_DIVERS.getName(),1);
        nbPagesByPhotoTypes.put(TechnicalEquipmentsPhotoTypes.COURSES_DIVERS.getName(),1);

    }

    public static final Map<PhotoCategories, Integer> nbPagesByPhotoCategories;
    static {
        nbPagesByPhotoCategories = new HashMap<PhotoCategories,Integer>();
        nbPagesByPhotoCategories.put(PhotoCategories.GENERAL_VIEW_ACCESS,4);
        nbPagesByPhotoCategories.put(PhotoCategories.COURSES_ACCESS,7);
        nbPagesByPhotoCategories.put(PhotoCategories.SECURITY,8);
        nbPagesByPhotoCategories.put(PhotoCategories.MALT_ADDUCTIONS,4);
        nbPagesByPhotoCategories.put(PhotoCategories.TECHNICAL_EQUIPMENTS,5);
    }

   /* public static final Map<GeneratedPDFDocumentSizes,Integer> pdfGeneratedDocumentSizes;
    static {
        pdfGeneratedDocumentSizes = new HashMap<GeneratedPDFDocumentSizes,Integer>();
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.MARGIN_TOP_HEADER,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.MARGIN_BOTTOM_HEADER,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.MARGIN_LEFT,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.MARGIN_RIGHT,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.Y_TITLE,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.MARGIN_BOTTOM_TYPE,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.MARGIN_BOTTOM_NAME,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.MARGIN_BOTTOM_PHOTO,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.MARGIN_BETWEEN_PHOTO,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.SIZE_TEXT_HEADER,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.SIZE_TITLE,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.SIZE_TYPE,0);
        pdfGeneratedDocumentSizes.put(GeneratedPDFDocumentSizes.SIZE_PHOTO_NAME,0);

    }*/


    public static int calculateHeightPhoto(int pageHeight){
        return (pageHeight - (GeneratedPDFDocumentSizes.MARGIN_TOP_HEADER.getSize()+GeneratedPDFDocumentSizes.SIZE_TEXT_HEADER.getSize()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_HEADER.getSize()+GeneratedPDFDocumentSizes.SIZE_TYPE.getSize()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_TYPE.getSize())/3) -
                (GeneratedPDFDocumentSizes.SIZE_PHOTO_NAME.getSize()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_NAME.getSize()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_PHOTO.getSize());
    }

    public static int calculatedWidthPhoto (int pageWidth){
        return (pageWidth - (GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize()+GeneratedPDFDocumentSizes.MARGIN_BETWEEN_PHOTO.getSize()+GeneratedPDFDocumentSizes.MARGIN_RIGHT.getSize()))/2;
    }

    public static int calculateHeightGeneralViewPhoto(int pageHeight){
        return calculateHeightPhoto(pageHeight) + 2*(GeneratedPDFDocumentSizes.SIZE_PHOTO_NAME.getSize()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_NAME.getSize()+calculateHeightPhoto(pageHeight)+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_PHOTO.getSize());
    }

    public static int calculateWidthGeneralViewPhoto(int pageWidth){
        return calculatedWidthPhoto(pageWidth)+GeneratedPDFDocumentSizes.MARGIN_BETWEEN_PHOTO.getSize()+calculatedWidthPhoto(pageWidth);
    }

    public static int calculateYTextHeader(){
        return GeneratedPDFDocumentSizes.MARGIN_TOP_HEADER.getSize();
    }

    public static int calculateYTitle(int pageHeight){
        return (pageHeight-GeneratedPDFDocumentSizes.SIZE_TITLE.getSize())/2;
    }

    public static int calculateYType(){
        return calculateYTextHeader()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_HEADER.getSize();
    }

    public static int calculateYFirstPhotoNamesRowOnPage(){
        return calculateYType()+GeneratedPDFDocumentSizes.SIZE_TYPE.getSize()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_TYPE.getSize();
    }

    public static int calculateYPhotoNamesRowOnPage(int pageHeight,int rowNumber){//
        return calculateYFirstPhotoNamesRowOnPage()+rowNumber*(GeneratedPDFDocumentSizes.SIZE_PHOTO_NAME.getSize()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_NAME.getSize()+calculateHeightPhoto(pageHeight)+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_PHOTO.getSize());
    }
    public static int calculatedYFirstPhotosRowOnPage(){
        return calculateYFirstPhotoNamesRowOnPage()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_NAME.getSize();
    }

    public static int calculateYPhotoRowOnPage(int pageHeight,int rowNumber){//
        return calculateYPhotoNamesRowOnPage( pageHeight,rowNumber)+GeneratedPDFDocumentSizes.SIZE_PHOTO_NAME.getSize()+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_NAME.getSize();
    }

    public static int calculateYOtherPhotoNamesRowOnPage(int pageHeight, int yPreviousPhotosRow){
        return yPreviousPhotosRow + calculateHeightPhoto(pageHeight)+GeneratedPDFDocumentSizes.MARGIN_BOTTOM_PHOTO.getSize();
    }

    public static int calculateYOtherPhotosRowOnPage( int yPhotoNamesRow){
        return yPhotoNamesRow +GeneratedPDFDocumentSizes.MARGIN_BOTTOM_NAME.getSize();
    }

    public static int calculateXColumnOfNamesAndPhotos(int pageWidth,int columnNumber){//
        return GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize()+ columnNumber * (calculatedWidthPhoto(pageWidth)+GeneratedPDFDocumentSizes.MARGIN_BETWEEN_PHOTO.getSize());
    }

    public static int calculateXSecondColumnOfPhotos(int pageWidth){
        return GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize()+ calculatedWidthPhoto(pageWidth)+GeneratedPDFDocumentSizes.MARGIN_BETWEEN_PHOTO.getSize();
    }

    public static int calculateXPageNumberHeader(){
        return GeneratedPDFDocumentSizes.MARGIN_LEFT.getSize() + GeneratedPDFDocumentSizes.MARGIN_BETWEEN_HEADERS.getSize();
    }

    //Types of photo management
    public static List<String> getTypesOfPhotosToWrite(List<SpacePhoto> listOfPhotosToWrite){
        List<String > result= new ArrayList<String>();

        for (SpacePhoto photo : listOfPhotosToWrite){
            if(!result.contains(photo.getMphotoType())){
                result.add(photo.getMphotoType());
            }
        }
        return result;
    }

    public static List<SpacePhoto> getPhotosToWriteByType(List<SpacePhoto> listOfPhotosToWrite, String type){
        List<SpacePhoto> result= new ArrayList<SpacePhoto>();

        for(SpacePhoto photo : listOfPhotosToWrite){
            if(photo.getMphotoType().equals(type)){
                result.add(photo);
            }
        }
        return result;
    }

    public static int getTotalOfPagesByCategory(PhotoCategories category){
        switch (category){
            case SECURITY:
                return 8;
            case COURSES_ACCESS:
                return 7;
            case GENERAL_VIEW_ACCESS:
                return 4;
            case TECHNICAL_EQUIPMENTS:
                return 5;
            default:return 0;
        }
    }

    public static int getNbPhotosToPrintByCategory(PhotoCategories category){
        List<SpacePhoto> list = ListOfPhotosSingletonManager.getListOfPhotosByCategory(category);
        int res=0;
        for(SpacePhoto photo : list){
            if(photo.getDownloadURL()!=null){
                res++;
            }
        }
        return res;
    }

}