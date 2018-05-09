package com.example.rvadam.pfe.Model;

/**
 * Created by rvadam on 09/05/2018.
 */

public enum DocumentTypes {
    OTHER_DOCUMENTS(1),SECURITY_DOCUMENTS(2),PLAN_DOCUMENTS(3),PPSPS_DOCUMENTS(4),DEFAULT(-1);
    private int value;


    DocumentTypes(int i) {
        this.value=i;
    }

    public int getValue() {
        return value;
    }

    // utility method to retrieve instance by int value
    public static DocumentTypes forValue(int value) {
        // iterating values
        for (DocumentTypes n: values()) {
            // matches argument
            if (n.getValue() == value) return n;
        }
        // no match, returning DEFAULT
            return DEFAULT;
    }
}
