package com.example.rvadam.pfe.Utils;

import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.Model.FileStatus;

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

    public static void updateStatusDocument(List<Document> listOfDocs, int position, FileStatus status){
        Document docToUpdate=listOfDocs.get(position);
        docToUpdate.setStatus(status);

    }

    public static void updateNameChooseFileDocument(List<Document> listOfDocs, int position, String chooseFile ){
        Document docToUpdate=listOfDocs.get(position);
        docToUpdate.setNameChoosedFile(chooseFile);

    }

    public static void updateNameUploadedFileDocument(List<Document> listOfDocs, int position, String nameFile ){
        Document docToUpdate=listOfDocs.get(position);
        docToUpdate.setName(nameFile);

    }


    public static String updateFilePathDocument(List<Document> listOfDocs, int position, Uri filePath, ContentResolver contentResolver){
        Document docToUpdate=listOfDocs.get(position);
        docToUpdate.setFilePath(filePath);

        return getFileName(filePath,contentResolver);

    }

    public static void updateFilePathDocument(List<Document> listOfDocs, int position, Uri filePath){
        Document docToUpdate=listOfDocs.get(position);
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

    public static Document getDocumentByPositionAndType(List<Document> listOfDocs, int position ){
        return listOfDocs.get(position);
    }
}


