package com.example.rvadam.pfe.Model;

import java.io.Serializable;

/**
 * Created by rdelfoss on 18/05/2018.
 */

public class Type implements Serializable {
    private String id;
    private String listeDoc;

    public Type() {
    }

    public Type(String id, String listeDoc) {
        this.id = id;
        this.listeDoc = listeDoc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListeDoc() {
        return listeDoc;
    }

    public void setListeDoc(String listeDoc) {
        this.listeDoc = listeDoc;
    }
}