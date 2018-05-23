package com.example.rvadam.pfe.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by rvadam on 07/05/2018.
 */

public class WorkSite implements Serializable {

    private String id;
    private long dateVIC;
    private List<String> employees;
    private double lattitude;
    private double longitude;
    private String name;
    private Map<String, String> otherDocuments;
    private Map<String, String> planDocuments;
    private Map<String, String> securityDocuments;
    private Map<String, String> ppspsDocuments;
    private String type;

    public WorkSite(String id, String name, long dateVIC, List<String> employees, double lattitude, double longitude, Map<String, String> otherDocuments, Map<String, String> planDocuments, Map<String, String> securityDocuments, Map<String, String> ppspsDocuments, String type) {
        this.id = id;
        this.dateVIC = dateVIC;
        this.employees = employees;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.name = name;
        this.otherDocuments = otherDocuments;
        this.planDocuments = planDocuments;
        this.securityDocuments = securityDocuments;
        this.ppspsDocuments = ppspsDocuments;
        this.type = type;
    }

    public WorkSite(long dateVIC, List<String> employees, double lattitude, double longitude, String name, Map<String, String> otherDocuments, Map<String, String> planDocuments, Map<String, String> securityDocuments, Map<String, String> ppspsDocuments, String type) {
        this.dateVIC = dateVIC;
        this.employees = employees;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.name = name;
        this.otherDocuments = otherDocuments;
        this.planDocuments = planDocuments;
        this.securityDocuments = securityDocuments;
        this.ppspsDocuments = ppspsDocuments;
        this.type = type;
    }

    public WorkSite() {
    }

    public long getDateVIC() {
        return dateVIC;
    }

    public Map<String, String> getPlanDocuments() {
        return planDocuments;
    }

    public Map<String, String> getSecurityDocuments() {
        return securityDocuments;
    }

    public Map<String, String> getPpspsDocuments() {
        return ppspsDocuments;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getOtherDocuments() {
        return otherDocuments;
    }

    public void setId(String id) {
        this.id = id;
    }
}
