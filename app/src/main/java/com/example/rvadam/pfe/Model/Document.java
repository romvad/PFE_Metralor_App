package com.example.rvadam.pfe.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import android.net.Uri;

/**
 * Created by rvadam on 08/05/2018.
 */

public class Document implements Parcelable {

    private String name;// ex.: "APD"
    private String fileName; // ex.: APD_Metralor.pdf
    private FileStatus status;
    private String nameChoosedFile;
    private Uri filePath;

    public Document(String name, String fileName, FileStatus status, String nameChoosedFile) {
        this.name = name;
        this.fileName = fileName;
        this.status = status;
        this.nameChoosedFile = nameChoosedFile;
    }

    protected Document(Parcel in) {
        name = in.readString();
        fileName = in.readString();
        nameChoosedFile = in.readString();
    }

    public static final Creator<Document> CREATOR = new Creator<Document>() {
        @Override
        public Document createFromParcel(Parcel in) {
            return new Document(in);
        }

        @Override
        public Document[] newArray(int size) {
            return new Document[size];
        }
    };

    public Uri getFilePath() {
        return filePath;
    }

    public void setFilePath(Uri filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileStatus getStatus() {
        return status;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }

    public String getNameChoosedFile() {
        return nameChoosedFile;
    }

    public void setNameChoosedFile(String nameChoosedFile) {
        this.nameChoosedFile = nameChoosedFile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(fileName);
        parcel.writeString(nameChoosedFile);
    }
}
