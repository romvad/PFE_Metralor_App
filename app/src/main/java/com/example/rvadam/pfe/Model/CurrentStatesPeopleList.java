package com.example.rvadam.pfe.Model;

import java.util.ArrayList;
import java.util.List;

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
}