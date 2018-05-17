package com.example.rvadam.pfe.Model;

import java.io.Serializable;

/**
 * Created by rdelfoss on 16/05/2018.
 */

public class Role implements Serializable {
    private String id;
    private String title;

    public Role() {
    }

    public Role(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
