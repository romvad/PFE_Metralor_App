package com.example.rvadam.pfe.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdelfoss on 23/05/2018.
 */

public class CurrentStatesCompaniesList {
    private static List<Company> currentCompaniesList;
    private static final CurrentStatesCompaniesList instance = new CurrentStatesCompaniesList();

    private CurrentStatesCompaniesList() {
        currentCompaniesList = new ArrayList<Company>();
    }

    public static CurrentStatesCompaniesList getInstance() {
        return instance;
    }

    public List<Company> getCurrentCompaniesList() {
        return currentCompaniesList;
    }
}