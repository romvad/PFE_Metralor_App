package com.example.rvadam.pfe.Model;


import java.util.ArrayList;

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
    private Map<String,String> otherDocumentsMap;
    private Map<String,String> planDocumentsMap;
    private Map<String,String> securityDocumentsMap;
    private Map<String,String> ppspsDocumentsMap;
    /*private Map<String,String> worksiteAccessPhotosMap;
    private Map<String,String> meansOfAccessPhotosMap;
    private Map<String,String> airAccessPhotosMap;
    private Map<String,String> technicalZoneAccessPhotosMap;
    private Map<String,String> rfModulesPhotosMap;
    private Map<String,String> energyPhotosMap;
    private Map<String,String> transmissionPhotosMap;
    private Map<String,String> antiIntrusionPhotosMap;
    private Map<String,String> antiFallPhotosMap;
    private Map<String,String> securityPerimeterPhotosMap;
    private Map<String,String> signaleticsPhotosMap;
    private Map<String,String> lightsPhotosMap;
    private Map<String,String> groundsPhotosMap;
    private Map<String,String> diversPhotosMap;
    private Map<String,String> technicalEquipmentsPlacesPhotosMap;
    private Map<String,String> divisionaryBoardPhotosMap;
    private Map<String,String> coursesDiversPhotosMap;*/
    private Map<String,String> coursesAccessPhotosMap;
    private Map<String,String> securityPhotosMap;
    private Map<String,String> technicalEquipmentsPhotosMap;
    private Map<String,String> generalViewAccessPhotosMap;
    private Map<String,String> maltAdductionsPhotosMap;
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

    // constructor with all param
    public WorkSite(String id, long dateVIC, List<String> employees, double lattitude, double longitude, String name, Map<String, String> otherDocumentsMap, Map<String, String> planDocumentsMap, Map<String, String> securityDocumentsMap, Map<String, String> ppspsDocumentsMap, Map<String, String> coursesAccessPhotosMap, Map<String, String> securityPhotosMap, Map<String, String> technicalEquipmentsPhotosMap, Map<String, String> generalViewAccessPhotosMap, Map<String, String> maltAdductionsPhotosMap, String type) {
        this.id = id;
        this.dateVIC = dateVIC;
        this.employees = employees;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.name = name;
        this.otherDocumentsMap = otherDocumentsMap;
        this.planDocumentsMap = planDocumentsMap;
        this.securityDocumentsMap = securityDocumentsMap;
        this.ppspsDocumentsMap = ppspsDocumentsMap;
        this.coursesAccessPhotosMap = coursesAccessPhotosMap;
        this.securityPhotosMap = securityPhotosMap;
        this.technicalEquipmentsPhotosMap = technicalEquipmentsPhotosMap;
        this.generalViewAccessPhotosMap = generalViewAccessPhotosMap;
        this.maltAdductionsPhotosMap = maltAdductionsPhotosMap;
        this.type = type;
    }

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
    public WorkSite(long dateVIC, List<String> employees, double lattitude, double longitude, String name, Map<String,String> otherDocuments, Map<String,String> planDocuments, Map<String,String> securityDocuments, Map<String,String> ppspsDocuments, String type) {
        this.dateVIC = dateVIC;
        this.employees = employees;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.name = name;
        this.otherDocumentsMap = otherDocuments;
        this.planDocumentsMap = planDocuments;
        this.securityDocumentsMap = securityDocuments;
        this.ppspsDocumentsMap = ppspsDocuments;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDateVIC() {
        return dateVIC;
    }

    public void setDateVIC(long dateVIC) {
        this.dateVIC = dateVIC;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Map<String, String> getCoursesAccessPhotosMap() {
        return coursesAccessPhotosMap;
    }

    public void setCoursesAccessPhotosMap(Map<String, String> coursesAccessPhotosMap) {
        this.coursesAccessPhotosMap = coursesAccessPhotosMap;
    }

    public Map<String, String> getSecurityPhotosMap() {
        return securityPhotosMap;
    }

    public void setSecurityPhotosMap(Map<String, String> securityPhotosMap) {
        this.securityPhotosMap = securityPhotosMap;
    }

    public Map<String, String> getTechnicalEquipmentsPhotosMap() {
        return technicalEquipmentsPhotosMap;
    }

    public void setTechnicalEquipmentsPhotosMap(Map<String, String> technicalEquipmentsPhotosMap) {
        this.technicalEquipmentsPhotosMap = technicalEquipmentsPhotosMap;
    }

    public Map<String, String> getGeneralViewAccessPhotosMap() {
        return generalViewAccessPhotosMap;
    }

    public void setGeneralViewAccessPhotosMap(Map<String, String> generalViewAccessPhotosMap) {
        this.generalViewAccessPhotosMap = generalViewAccessPhotosMap;
    }

    public Map<String, String> getMaltAdductionsPhotosMap() {
        return maltAdductionsPhotosMap;
    }

    public void setMaltAdductionsPhotosMap(Map<String, String> maltAdductionsPhotosMap) {
        this.maltAdductionsPhotosMap = maltAdductionsPhotosMap;
    }

    public List<Document> getOtherDocuments() {
        return otherDocuments;
    }

    public void setOtherDocuments(List<Document> otherDocuments) {
        this.otherDocuments = otherDocuments;
    }

    public List<Document> getPlanDocuments() {
        return planDocuments;
    }

    public void setPlanDocuments(List<Document> planDocuments) {
        this.planDocuments = planDocuments;
    }

    public List<Document> getSecurityDocuments() {
        return securityDocuments;
    }

    public void setSecurityDocuments(List<Document> securityDocuments) {
        this.securityDocuments = securityDocuments;
    }

    public List<Document> getPpspsDocuments() {
        return ppspsDocuments;
    }

    public void setPpspsDocuments(List<Document> ppspsDocuments) {
        this.ppspsDocuments = ppspsDocuments;
    }

    public List<SpacePhoto> getCoursesAccessPhotos() {
        return coursesAccessPhotos;
    }

    public void setCoursesAccessPhotos(List<SpacePhoto> coursesAccessPhotos) {
        this.coursesAccessPhotos = coursesAccessPhotos;
    }

    public List<SpacePhoto> getGeneralViewAccessPhotos() {
        return generalViewAccessPhotos;
    }

    public void setGeneralViewAccessPhotos(List<SpacePhoto> generalViewAccessPhotos) {
        this.generalViewAccessPhotos = generalViewAccessPhotos;
    }

    public List<SpacePhoto> getTechnicalEquipmentsPhotos() {
        return technicalEquipmentsPhotos;
    }

    public void setTechnicalEquipmentsPhotos(List<SpacePhoto> technicalEquipmentsPhotos) {
        this.technicalEquipmentsPhotos = technicalEquipmentsPhotos;
    }

    public List<SpacePhoto> getMaltAdductionsPhotos() {
        return maltAdductionsPhotos;
    }

    public void setMaltAdductionsPhotos(List<SpacePhoto> maltAdductionsPhotos) {
        this.maltAdductionsPhotos = maltAdductionsPhotos;
    }

    public List<SpacePhoto> getSecurityPhotos() {
        return securityPhotos;
    }

    public void setSecurityPhotos(List<SpacePhoto> securityPhotos) {
        this.securityPhotos = securityPhotos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
