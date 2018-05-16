package com.example.rvadam.pfe.Model;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rvadam on 04/05/2018.
 */

public class Constants {

    private static final Constants instance=new Constants();
    private static File tmpFileDLFromOneDrive;

    private static List<String> generalViewAccessPhotoNames =new ArrayList<String>();
    private static Map<CoursesAccessPhotoTypes,List<String>> coursesAccessPhotoNames = new HashMap<CoursesAccessPhotoTypes, List<String>>();
    private static Map<TechnicalEquipmentsPhotoTypes,List<String>> technicalEquipmentsPhotoNames = new HashMap<TechnicalEquipmentsPhotoTypes, List<String>>();
    private static Map<MaltAdductionsPhotoTypes,List<String>> maltAdductionsPhotoNames = new HashMap<MaltAdductionsPhotoTypes, List<String>>();
    private static Map<SecurityPhotoTypes,List<String>> securityPhotoNames = new HashMap<SecurityPhotoTypes, List<String>>();

    private static List<String> energyPhotoNames=new ArrayList<String>();
    private static List<String> transmissionPhotoNames=new ArrayList<String>();
    private static List<String> workSiteAccessPhotoNames=new ArrayList<String>();
    private static List<String> meansOfAccessPhotoNames=new ArrayList<String>();
    private static List<String> airAccessPhotoNames=new ArrayList<String>();
    private static List<String> technicalZoneAccessPhotoNames=new ArrayList<String>();
    private static List<String> antiIntrusionPhotoNames=new ArrayList<String>();
    private static List<String> antiFallPhotoNames=new ArrayList<String>();
    private static List<String> securityPerimeterPhotoNames=new ArrayList<String>();
    private static List<String> signaleticsPhotoNames=new ArrayList<String>();
    private static List<String> lightsPhotoNames=new ArrayList<String>();
    private static List<String> groundsPhotoNames=new ArrayList<String>();
    private static List<String> diversPhotoNames=new ArrayList<String>();
    private static List<String> technicalEquipmentsPlacesPhotoNames=new ArrayList<String>();
    private static List<String> divisionaryBoardPhotoNames=new ArrayList<String>();
    private static List<String> coursesDiversPhotoNames=new ArrayList<String>();
    private static List<String> RFModulesPhotoNames=new ArrayList<String>();


    final static String ONE_DRIVE_CLIENT_ID = "28e52f7c-c97f-4296-ba95-ee04856a60f4";//App ID OneDrive
    final static String ONE_DRIVE_SCOPES [] = {"https://graph.microsoft.com/User.Read"};//Used for retrieve microsoft login token when already connected on microsoft account

    private static boolean listOfWorksitesRetrieveFromDB=false;

    public Constants() {
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);
        tmpFileDLFromOneDrive= new File(path, "tmpDLFromOneDrive.pdf");

        //Preparation of photo names
        generalViewAccessPhotoNames.add("vue_general_1.jpg");
        generalViewAccessPhotoNames.add("vue_general_2.jpg");
        generalViewAccessPhotoNames.add("vue_general_3.jpg");
        energyPhotoNames.add("emplacement_compteur_edf.jpg");
        energyPhotoNames.add("compteur_free.jpg");
        energyPhotoNames.add("disjoncteur_free.jpg");
        energyPhotoNames.add("TD_free.jpg");
        transmissionPhotoNames.add("emplacement_tete_FT.jpg");
        transmissionPhotoNames.add("PC_FT.jpg");
        transmissionPhotoNames.add("FO.jpg");
        transmissionPhotoNames.add("MAC_Nodebox.jpg");
        transmissionPhotoNames.add("FH.jpg");
        workSiteAccessPhotoNames.add("vue_d_ensemble.jpg");
        workSiteAccessPhotoNames.add("entrée_batiment_zone_technique.jpg");
        workSiteAccessPhotoNames.add("baie_flexi.jpg");
        workSiteAccessPhotoNames.add("baie_flexi_bis.jpg");
        meansOfAccessPhotoNames.add("porte_access.jpg");
        meansOfAccessPhotoNames.add("boite_a_cles.jpg");
        meansOfAccessPhotoNames.add("boite_a_cles_bis.jpg");
        airAccessPhotoNames.add("porte_condamnation.jpg");
        airAccessPhotoNames.add("cadenas_porte_condamnation.jpg");
        airAccessPhotoNames.add("emplacement_echelle_FM.jpg");
        airAccessPhotoNames.add("code_cadenas_echelle_FM.jpg");
        airAccessPhotoNames.add("grille_anti_intrusion.jpg");
        technicalZoneAccessPhotoNames.add("entree_zone_technique.jpg");
        antiIntrusionPhotoNames.add("porte_condamnation.jpg");
        antiIntrusionPhotoNames.add("picots_anti_intrusion.jpg");
        antiFallPhotoNames.add("crinoline.jpg");
        antiFallPhotoNames.add("Rail_soll.jpg");
        antiFallPhotoNames.add("Palier_repos.jpg");
        antiFallPhotoNames.add("point_ancrage.jpg");
        securityPerimeterPhotoNames.add("garde_corps.jpg");
        signaleticsPhotoNames.add("identité_site.jpg");
        signaleticsPhotoNames.add("port_EPI.jpg");
        signaleticsPhotoNames.add("ondes_electromagnetiques.jpg");
        signaleticsPhotoNames.add("soins_electrises.jpg");
        signaleticsPhotoNames.add("coffre_electrique.jpg");
        lightsPhotoNames.add("lampes.jpg");
        lightsPhotoNames.add("interrupteur.jpg");
        lightsPhotoNames.add("capteur.jpg");
        groundsPhotoNames.add("plaque_terre.jpg");
        groundsPhotoNames.add("barrette_coupure.jpg");
        groundsPhotoNames.add("interconnexions.jpg");
        diversPhotoNames.add("verfication_serrage_FPMA.jpg");
        diversPhotoNames.add("controles_periodiques.jpg");
        technicalEquipmentsPlacesPhotoNames.add("ZT_principale_ensemble.jpg");
        technicalEquipmentsPlacesPhotoNames.add("ZT_principale_zoomee.jpg");
        technicalEquipmentsPlacesPhotoNames.add("S1_ensemble.jpg");
        technicalEquipmentsPlacesPhotoNames.add("S1_zoomee.jpg");
        technicalEquipmentsPlacesPhotoNames.add("S2_ensemble.jpg");
        technicalEquipmentsPlacesPhotoNames.add("S2_zoomee.jpg");
        technicalEquipmentsPlacesPhotoNames.add("S3_ensemble.jpg");
        technicalEquipmentsPlacesPhotoNames.add("S3_zoomee.jpg");
        divisionaryBoardPhotoNames.add("TD_ensemble.jpg");
        divisionaryBoardPhotoNames.add("TD_ferme.jpg");
        divisionaryBoardPhotoNames.add("TD_ouvert.jpg");
        divisionaryBoardPhotoNames.add("Mini_TD.jpg");
        divisionaryBoardPhotoNames.add("Mini_TD_ouvert.jpg");
        coursesDiversPhotoNames.add("dans_pylone.jpg");
        coursesDiversPhotoNames.add("dans_pylone_bis.jpg");
        coursesDiversPhotoNames.add("au_sol.jpg");
        coursesDiversPhotoNames.add("au_sol_bis.jpg");
        RFModulesPhotoNames.add("RF_1800.jpg");
        RFModulesPhotoNames.add("RF_2600.jpg");
        RFModulesPhotoNames.add("RF_2100.jpg");
        RFModulesPhotoNames.add("RF_900.jpg");
        RFModulesPhotoNames.add("RF_700.jpg");

        maltAdductionsPhotoNames.put(MaltAdductionsPhotoTypes.ENERGY,energyPhotoNames);
        maltAdductionsPhotoNames.put(MaltAdductionsPhotoTypes.TRANSMISSION,transmissionPhotoNames);
        technicalEquipmentsPhotoNames.put(TechnicalEquipmentsPhotoTypes.COURSES_DIVERS,coursesDiversPhotoNames);
        technicalEquipmentsPhotoNames.put(TechnicalEquipmentsPhotoTypes.DIVSIONARY_BOARD,divisionaryBoardPhotoNames);
        technicalEquipmentsPhotoNames.put(TechnicalEquipmentsPhotoTypes.TECHNICAL_EQUIPMENTS_PLACES,technicalEquipmentsPlacesPhotoNames);
        securityPhotoNames.put(SecurityPhotoTypes.ANTI_INTRUSION,antiIntrusionPhotoNames);
        securityPhotoNames.put(SecurityPhotoTypes.ANTI_FALL,antiFallPhotoNames);
        securityPhotoNames.put(SecurityPhotoTypes.DIVERS,diversPhotoNames);
        securityPhotoNames.put(SecurityPhotoTypes.GROUNDS,groundsPhotoNames);
        securityPhotoNames.put(SecurityPhotoTypes.LIGHTS,lightsPhotoNames);
        securityPhotoNames.put(SecurityPhotoTypes.SIGNALETICS,signaleticsPhotoNames);
        securityPhotoNames.put(SecurityPhotoTypes.SECURITY_PERIMETER,securityPerimeterPhotoNames);
        coursesAccessPhotoNames.put(CoursesAccessPhotoTypes.AIR_ACCESS,airAccessPhotoNames);
        coursesAccessPhotoNames.put(CoursesAccessPhotoTypes.MEANS_OF_ACCESS,meansOfAccessPhotoNames);
        coursesAccessPhotoNames.put(CoursesAccessPhotoTypes.TECHNICAL_ZONE_ACCESS, technicalZoneAccessPhotoNames);
        coursesAccessPhotoNames.put(CoursesAccessPhotoTypes.WORKSITE_ACCESS,workSiteAccessPhotoNames);
        coursesAccessPhotoNames.put(CoursesAccessPhotoTypes.RF_MODULES,RFModulesPhotoNames);




    }

    public static boolean isListOfWorksitesRetrieveFromDB() {
        return listOfWorksitesRetrieveFromDB;
    }

    public static void setListOfWorksitesRetrieveFromDB(boolean listOfWorksitesRetrieveFromDB) {
        Constants.listOfWorksitesRetrieveFromDB = listOfWorksitesRetrieveFromDB;
    }

    public static Constants getInstance(){
        return instance;
    }

    public static File getTmpFileDLFromOneDrive() {
        return tmpFileDLFromOneDrive;
    }

    public static String getOneDriveClientId() {
        return ONE_DRIVE_CLIENT_ID;
    }

    public static String[] getOneDriveScopes() {
        return ONE_DRIVE_SCOPES;
    }

    public static List<String> getGeneralViewAccessPhotoNames() {
        return generalViewAccessPhotoNames;
    }

    public static Map<CoursesAccessPhotoTypes, List<String>> getCoursesAccessPhotoNames() {
        return coursesAccessPhotoNames;
    }

    public static Map<TechnicalEquipmentsPhotoTypes, List<String>> getTechnicalEquipmentsPhotoNames() {
        return technicalEquipmentsPhotoNames;
    }

    public static Map<MaltAdductionsPhotoTypes, List<String>> getMaltAdductionsPhotoNames() {
        return maltAdductionsPhotoNames;
    }

    public static Map<SecurityPhotoTypes, List<String>> getSecurityPhotoNames() {
        return securityPhotoNames;
    }
}
