package com.example.rvadam.pfe.Model;

/**
 * Created by rvadam on 16/05/2018.
 */

public enum MaltAdductionsPhotoTypes {
    ENERGY("ENERGIE"),TRANSMISSION("TRANSMISSION");

    private String type;

    MaltAdductionsPhotoTypes(String type){
        this.type = type;
    }

    public String getName(){
        return type;
    }

    private void setCategory(String category){
        this.type = category;
    }
}
