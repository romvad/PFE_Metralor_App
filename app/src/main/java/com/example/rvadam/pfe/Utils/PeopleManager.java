package com.example.rvadam.pfe.Utils;

import com.example.rvadam.pfe.Model.CurrentStatesPeopleList;
import com.example.rvadam.pfe.Model.People;

import java.util.List;

/**
 * Created by rdelfoss on 22/05/2018.
 */

public class PeopleManager {
    public static People getPeopleById(String id) {
        People people = null;
        List<People> list = CurrentStatesPeopleList.getInstance().getCurrentPeopleList();
        for (People p : list) {
            if (p.getId().equals(id)) {
                people = p;
            }
        }

        return people;
    }

    public static String getPeopleLastNameByPeople(String id) {
        return getPeopleById(id).getLastname();
    }

    public static String getPeopleFirstNameById(String id) {
        return getPeopleById(id).getFirstname();
    }
}
