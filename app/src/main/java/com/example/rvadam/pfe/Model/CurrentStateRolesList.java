package com.example.rvadam.pfe.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdelfoss on 23/05/2018.
 */

public class CurrentStateRolesList {
    private static List<Role> currentRolesList;
    private static final CurrentStateRolesList instance = new CurrentStateRolesList();

    private CurrentStateRolesList() {
        currentRolesList = new ArrayList<Role>();
    }

    public static CurrentStateRolesList getInstance() {
        return instance;
    }

    public List<Role> getCurrentRolesList() {
        return currentRolesList;
    }
}