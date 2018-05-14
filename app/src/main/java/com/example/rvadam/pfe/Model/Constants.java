package com.example.rvadam.pfe.Model;

import android.os.Environment;

import java.io.File;

/**
 * Created by rvadam on 04/05/2018.
 */

public class Constants {

    private static final Constants instance=new Constants();
    private static File tmpFileDLFromOneDrive;
    final static String ONE_DRIVE_CLIENT_ID = "28e52f7c-c97f-4296-ba95-ee04856a60f4";//App ID OneDrive
    final static String ONE_DRIVE_SCOPES [] = {"https://graph.microsoft.com/User.Read"};//Used for retrieve microsoft login token when already connected on microsoft account

    private static boolean listOfWorksitesRetrieveFromDB=false;

    public Constants() {
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);
        tmpFileDLFromOneDrive= new File(path, "tmpDLFromOneDrive.pdf");
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
}
