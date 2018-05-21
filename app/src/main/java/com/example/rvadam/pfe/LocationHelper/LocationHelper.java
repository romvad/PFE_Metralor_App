package com.example.rvadam.pfe.LocationHelper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.util.List;

/**
 * Created by rdelfoss on 20/05/2018.
 */

public class LocationHelper {
    private static final String TAG = "LocationHelper";

    private Context mContext;

    public LocationHelper(Context context) {
        this.mContext = context;
    }

    /**
     * to get latitude and longitude of an address
     *
     * @param strAddress address string
     * @return lat and lng in comma separated string
     */
    public String getLocationFromAddress(String strAddress) {

        Log.i(TAG,strAddress);
        Geocoder coder = new Geocoder(mContext);
        List<Address> address;

        try {

            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            return lat + "," + lng;
        } catch (Exception e) {
            return null;
        }
    }
}
