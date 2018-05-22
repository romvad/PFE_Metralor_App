package com.example.rvadam.pfe.Utils;

/**
 * Created by rvadam on 21/05/2018.
 */

public enum GeneratedPDFDocumentSizes {
    MARGIN_TOP_HEADER(5),MARGIN_BOTTOM_HEADER(7),MARGIN_BETWEEN_HEADERS(80),MARGIN_LEFT(20),MARGIN_RIGHT(20),Y_TITLE(0),MARGIN_BOTTOM_TYPE(5),MARGIN_BOTTOM_NAME(7),MARGIN_BOTTOM_PHOTO(10),MARGIN_BETWEEN_PHOTO(20),
    SIZE_TEXT_HEADER(15),SIZE_TYPE(30),SIZE_TITLE(50),SIZE_PHOTO_NAME(25);

    private int size;

    GeneratedPDFDocumentSizes(int size){
        this.size = size;
    }

    public int getSize(){
        return size;
    }

    private void setSize(int size){
        this.size = size;
    }
}
