package com.example.rvadam.pfe.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdelfoss on 17/05/2018.
 */

public class CurrentStatesWorksitesList {
    private static List<WorkSite> currentWorksitesList = new ArrayList<WorkSite>();
    private static List<String> types = new ArrayList<String>();
    private static final CurrentStatesWorksitesList instance = new CurrentStatesWorksitesList();

    private CurrentStatesWorksitesList() {
    }

    public static CurrentStatesWorksitesList getInstance() {
        return instance;
    }

    public List<WorkSite> getCurrentWorksitesList() {
        return currentWorksitesList;
    }

    public List<String> getTypes() {
        return types;
    }

}