package com.example.rvadam.pfe.WelcomeListWorkSites;

/**
 * Created by rdelfoss on 04/05/2018.
 */

public class WorkSite {
    // ************* Variable declaration *************
    private String name;
    private String location;
    private String type;

    // ************* Constructor with parameter *************
    public WorkSite(String pName, String pLocation, String pType) {
        this.name = pName;
        this.location = pLocation;
        this.type = pType;
    }

    // ************* GETTERS *************
    public String getWorksiteName() {
        return name;
    }

    public String getWorksiteLocation() {
        return location;
    }

    public String getWorksiteType() {
        return type;
    }

    // ************* SETTERS *************
    public void setWorksiteName(String pName) {
        name = pName;
    }

    public void setWorksiteLocation(String pLocation) {
        location = pLocation;
    }

    public void setWorksiteDate(String pType) {
        type = pType;
    }

}
