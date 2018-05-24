package com.example.rvadam.pfe.Model;

import java.io.Serializable;

/**
 * Created by rdelfoss on 16/05/2018.
 */

public class Company implements Serializable {
    private String id;
    private String name;

    public Company() {
    }

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

