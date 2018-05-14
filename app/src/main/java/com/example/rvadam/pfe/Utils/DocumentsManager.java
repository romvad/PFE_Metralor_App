package com.example.rvadam.pfe.Utils;

import com.example.rvadam.pfe.Model.CurrentStatesWorkSites;
import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.Model.DocumentTypes;
import com.example.rvadam.pfe.Model.FileStatus;
import com.example.rvadam.pfe.Model.WorkSite;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import java.util.List;

/**
 * Created by rvadam on 09/05/2018.
 */

public class DocumentsManager {

    private  static final String STATUS_UPDATE="update";
    private  static final String NAME_CHOOSED_FILE_UPDATE="choose file";

    public static void updateStatusDocument(DocumentTypes type, int position,String idWorkSite, FileStatus status){
        Document docToUpdate=getDocumentByPositionAndType(type,position,idWorkSite);
        docToUpdate.setStatus(status);

    }

    public static void updateNameChooseFileDocument(DocumentTypes type, int position,String idWorkSite, String chooseFile ){
        Document docToUpdate=getDocumentByPositionAndType(type,position,idWorkSite);
        docToUpdate.setNameChoosedFile(chooseFile);

    }

    public static void updateNameUploadedFileDocument(DocumentTypes type, int position,String idWorkSite, String nameFile ){
        Document docToUpdate=getDocumentByPositionAndType(type,position,idWorkSite);
        docToUpdate.setName(nameFile);

    }


    public static String updateFilePathDocument(DocumentTypes type, int position,String idWorkSite, Uri filePath, ContentResolver contentResolver){
        Document docToUpdate=getDocumentByPositionAndType(type,position,idWorkSite);
        docToUpdate.setFilePath(filePath);

        return getFileName(filePath,contentResolver);

    }

    public static void updateFilePathDocument(DocumentTypes type, int position,String idWorkSite, Uri filePath){
        Document docToUpdate=getDocumentByPositionAndType(type,position,idWorkSite);
        docToUpdate.setFilePath(filePath);

    }

    public static String getFileName(Uri uri, ContentResolver contentResolver) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public static Document getDocumentByPositionAndType(DocumentTypes type, int position, String idWorkSite ) {
        Document res=null;
        WorkSite w;
        switch (type) {
            case OTHER_DOCUMENTS:
                w = WorkSitesManager.getWorkSiteById(idWorkSite);
                res = w.getOtherDocuments().get(position);
                break;
            case PLAN_DOCUMENTS:
                w = WorkSitesManager.getWorkSiteById(idWorkSite);
                res = w.getPlanDocuments().get(position);

                break;
            case SECURITY_DOCUMENTS:
                w = WorkSitesManager.getWorkSiteById(idWorkSite);
                res = w.getSecurityDocuments().get(position);

                break;
            case PPSPS_DOCUMENTS:
                w = WorkSitesManager.getWorkSiteById(idWorkSite);
                res = w.getPpspsDocuments().get(position);

                break;
            default:;
        }
                return res;
    }

}


