package com.example.rvadam.pfe.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rdelfoss on 15/05/2018.
 */

public class CurrentStatesPeopleList {
    private static List<People> currentPeopleList = new ArrayList<People>();
    private static final CurrentStatesPeopleList instance = new CurrentStatesPeopleList();

    private CurrentStatesPeopleList() {
    }

    public static CurrentStatesPeopleList getInstance() {
        return instance;
    }

    public List<People> getCurrentPeopleList() {
        return currentPeopleList;
    }

    private static Map<String, String> companies = new HashMap<String, String>();

    public Map<String, String> getCompaniesMap() {
        return companies;
    }

    private static Map<String, String> roles = new HashMap<String, String>();

    public Map<String, String> getRolesMap() {
        return roles;
    }
}