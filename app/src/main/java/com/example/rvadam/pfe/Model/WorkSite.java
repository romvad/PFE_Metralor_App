package com.example.rvadam.pfe.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rvadam on 07/05/2018.
 */

public class WorkSite {

    private String id;
    private long dateVIC;
    private List<String> employees;
    private double lattitude;
    private double longitude;
    private String name;
    private Map<String,String> otherDocumentsMap;
    private Map<String,String> planDocumentsMap;
    private Map<String,String> securityDocumentsMap;
    private Map<String,String> ppspsDocumentsMap;
    private List<Document> otherDocuments=new ArrayList<Document>();
    private List<Document> planDocuments=new ArrayList<Document>();
    private List<Document> securityDocuments=new ArrayList<Document>();
    private List<Document> ppspsDocuments=new ArrayList<Document>();
    private List<SpacePhoto> coursesAccessPhotos= new ArrayList<SpacePhoto>();
    private List<SpacePhoto> generalViewAccessPhotos=new ArrayList<SpacePhoto>();
    private List<SpacePhoto> technicalEquipmentsPhotos=new ArrayList<SpacePhoto>();
    private List<SpacePhoto> maltAdductionsPhotos=new ArrayList<SpacePhoto>();
    private List<SpacePhoto> securityPhotos=new ArrayList<SpacePhoto>();
    private String type;

    public WorkSite(String id, long dateVIC, double lattitude, double longitude, String name, Map<String, String> otherDocumentsMap, Map<String, String> planDocumentsMap, Map<String, String> securityDocumentsMap, Map<String, String> ppspsDocumentsMap, String type) {
        this.id = id;
        this.dateVIC = dateVIC;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.name = name;
        this.otherDocumentsMap = otherDocumentsMap;
        this.planDocumentsMap = planDocumentsMap;
        this.securityDocumentsMap = securityDocumentsMap;
        this.ppspsDocumentsMap = ppspsDocumentsMap;
        this.type = type;
    }

    public WorkSite(String id,String name, long dateVIC, List<String> employees, double lattitude, double longitude, List<Document> otherDocuments, List<Document> planDocuments, List<Document> securityDocuments, List<Document> ppspsDocuments, String type) {
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

    //Constructor without ID, used when we create a worksite to be able to generate a unique ID in Firebase DB
    public WorkSite(long dateVIC, List<String> employees, double lattitude, double longitude, String name, List<Document> otherDocuments, List<Document> planDocuments, List<Document> securityDocuments, List<Document> ppspsDocuments, String type) {
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

    public WorkSite(String id, long dateVIC, double lattitude, double longitude, String name, String type) {
        this.id = id;
        this.dateVIC = dateVIC;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.name = name;
        this.type = type;
    }



    public long getDateVIC() {
        return dateVIC;
    }

    public List<Document> getPlanDocuments() {
        return planDocuments;
    }

    public List<Document> getSecurityDocuments() {
        return securityDocuments;
    }

    public List<Document> getPpspsDocuments() {
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

    public List<Document> getOtherDocuments() {
        return otherDocuments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getOtherDocumentsMap() {
        return otherDocumentsMap;
    }

    public void setOtherDocumentsMap(Map<String, String> otherDocumentsMap) {
        this.otherDocumentsMap = otherDocumentsMap;
    }

    public Map<String, String> getPlanDocumentsMap() {
        return planDocumentsMap;
    }

    public void setPlanDocumentsMap(Map<String, String> planDocumentsMap) {
        this.planDocumentsMap = planDocumentsMap;
    }

    public Map<String, String> getSecurityDocumentsMap() {
        return securityDocumentsMap;
    }

    public void setSecurityDocumentsMap(Map<String, String> securityDocumentsMap) {
        this.securityDocumentsMap = securityDocumentsMap;
    }

    public Map<String, String> getPpspsDocumentsMap() {
        return ppspsDocumentsMap;
    }

    public void setPpspsDocumentsMap(Map<String, String> ppspsDocumentsMap) {
        this.ppspsDocumentsMap = ppspsDocumentsMap;
    }

    public List<SpacePhoto> getCoursesAccessPhotos() {
        return coursesAccessPhotos;
    }

    public List<SpacePhoto> getGeneralViewAccessPhotos() {
        return generalViewAccessPhotos;
    }

    public List<SpacePhoto> getTechnicalEquipmentsPhotos() {
        return technicalEquipmentsPhotos;
    }

    public List<SpacePhoto> getMaltAdductionsPhotos() {
        return maltAdductionsPhotos;
    }

    public List<SpacePhoto> getSecurityPhotos() {
        return securityPhotos;
    }
}
