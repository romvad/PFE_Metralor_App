package com.example.rvadam.pfe.operationTests;

import com.example.rvadam.pfe.Model.Document;
import com.example.rvadam.pfe.Model.FileStatus;
import com.example.rvadam.pfe.Utils.DocumentsManager;

import java.util.ArrayList;

/**
 * Created by rvadam on 10/05/2018.
 */

public class SampleDocuments {

    public static ArrayList<Document> getSampleOtherDocument(){
        ArrayList<Document> result=new ArrayList<Document>();
        Document d1=new Document("d1","d1up.pdf", FileStatus.READY,"-");
        Document d2= new Document("D2","",FileStatus.NOT_UPLOADED,"-");
        result.add(d1);
        result.add(d2);

        return result;
    }
}
