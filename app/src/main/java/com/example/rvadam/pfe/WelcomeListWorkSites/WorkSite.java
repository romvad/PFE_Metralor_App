package com.example.rvadam.pfe.WelcomeListWorkSites;

/**
 * Created by rdelfoss on 04/05/2018.
 */

public class WorkSite {
    // ************* Variable declaration *************
    private String name;
    private double longitude;
    private double latitude;
    private String type;

    // ************* Constructor with parameter *************
    public WorkSite(String pName, double pLongitude, double pLatitude, String pType) {
        this.name = pName;
        this.longitude = pLongitude;
        this.latitude = pLatitude;
        this.type = pType;
    }

    // ************* GETTERS *************
    public String getWorksiteName() {
        return name;
    }

    public double getWorksiteLongitude() {
        return longitude;
    }

    public double getWorksiteLatitude() {
        return latitude;
    }

    public String getWorksiteType() {
        return type;
    }

    // ************* SETTERS *************
    public void setWorksiteName(String pName) {
        name = pName;
    }

    public void setWorksiteLongitude(double pLongitude) {
        longitude = pLongitude;
    }

    public void setWorksiteLatitude(double pLatitude) {
        latitude = pLatitude;
    }

    public void setWorksiteDate(String pType) {
        type = pType;
    }

}
