package com.example.rvadam.pfe.Utils;

import com.example.rvadam.pfe.Model.Constants;
import com.example.rvadam.pfe.Model.CurrentStatesWorkSites;
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
}
