package com.example.rvadam.pfe.Model;

/**
 * Created by rvadam on 16/05/2018.
 */

public enum SecurityPhotoTypes {
    ANTI_INTRUSION("ANTI-INTRUSION"),ANTI_FALL("ANTI-CHUTE"),SECURITY_PERIMETER("PERIMETRE DE SECURITE"),SIGNALETICS("SIGNALETIQUE"),LIGHTS("LUMIERES"),GROUNDS("TERRE"),DIVERS("DIVERS");

    private String type;

    SecurityPhotoTypes(String type){
        this.type = type;
    }

    public String getName(){
        return type;
    }

    private void setCategory(String category){
        this.type = category;
    }
}
