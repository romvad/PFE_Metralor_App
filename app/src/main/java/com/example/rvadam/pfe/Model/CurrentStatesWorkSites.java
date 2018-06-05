package com.example.rvadam.pfe.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvadam on 12/05/2018.
 */

public class CurrentStatesWorkSites {

    private static List<WorkSite> currentWorkSites;
    private static final CurrentStatesWorkSites instance=new CurrentStatesWorkSites();

    private CurrentStatesWorkSites() {
        currentWorkSites=new ArrayList<WorkSite>();
    }

    public static CurrentStatesWorkSites getInstance(){
        return instance;
    }

    public List<WorkSite> getCurrentWorkSites() {
        return currentWorkSites;
    }
}
