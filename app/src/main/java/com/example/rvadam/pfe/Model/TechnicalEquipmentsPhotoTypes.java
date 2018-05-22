package com.example.rvadam.pfe.Model;

/**
 * Created by rvadam on 16/05/2018.
 */

public enum TechnicalEquipmentsPhotoTypes {
    TECHNICAL_EQUIPMENTS_PLACES("EMPLACEMENT DES EQUIPEMENTS TECHNIQUES"),DIVISIONARY_BOARD("TABLEAU DIVISIONNAIRE"),COURSES_DIVERS("CHEMINEMENT/DIVERS");

    private String type;

    TechnicalEquipmentsPhotoTypes(String type){
        this.type = type;
    }

    public String getName(){
        return type;
    }

    private void setCategory(String category){
        this.type = category;
    }
}
