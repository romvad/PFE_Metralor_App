package com.example.rvadam.pfe.WelcomeListWorkSites;

import java.util.ArrayList;

/**
 * Created by rdelfoss on 04/05/2018.
 */

public final class WorkSites {
    private static ArrayList<WorkSite> listOfWorksites = new ArrayList<>();
    private static WorkSites singleInstance = new WorkSites();

    // ************* Constructor *************
    private WorkSites() {
        WorkSite c1 = new WorkSite("Chantier 1", 48.85837, 2.294481, "type1");
        listOfWorksites.add(c1);

/*        WorkSite c1 = new WorkSite("Chantier 1", "Bordeaux", "type1");
        WorkSite c2 = new WorkSite("Chantier 2", "Talence", "type2");
        WorkSite c3 = new WorkSite("Chantier 3", "Pessac", "type3");

        listOfWorksites.add(c1);
        listOfWorksites.add(c2);
        listOfWorksites.add(c3);*/

    }

    // ************* Getter *************
    public static ArrayList<WorkSite> getWorkSites() {
        return listOfWorksites;
    }
}
