package com.example.rvadam.pfe.Utils;

import com.example.rvadam.pfe.Model.Constants;
import com.example.rvadam.pfe.Model.CurrentStatesWorkSites;
import com.example.rvadam.pfe.Model.ListOfPhotosSingleton;
import com.example.rvadam.pfe.Model.SpacePhoto;
import com.example.rvadam.pfe.Model.WorkSite;

import java.util.List;

/**
 * Created by rvadam on 12/05/2018.
 */

public class WorkSitesManager {

    public static WorkSite getWorkSiteById(String id){
        List<WorkSite> listOfWorkSites= CurrentStatesWorkSites.getInstance().getCurrentWorkSites();

        while (!Constants.getInstance().isListOfWorksitesRetrieveFromDB()){
            listOfWorkSites=CurrentStatesWorkSites.getInstance().getCurrentWorkSites();
        }
        for(WorkSite w : listOfWorkSites){
            if(w.getId().equals(id)){
                return w;
            }
        }
        return null;
    }

    public static void updateWorkSiteIdListOfPhotosSingleton(String idWorkSite){

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getCoursesAccessPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getGeneralViewAccessPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getMaltAdductionsPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getSecurityPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }

        for(SpacePhoto photo : ListOfPhotosSingleton.getInstance().getTechnicalEquipmentsPhotos()){
            photo.setmIdWorkSite(idWorkSite);
        }
    }
}
