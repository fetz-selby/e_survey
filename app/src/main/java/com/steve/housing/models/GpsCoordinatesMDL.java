package com.steve.housing.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 5/12/17.
 */

public class GpsCoordinatesMDL extends RealmObject {

//    gps 		: { lat: Number, lng: Number },

    @PrimaryKey
    private String id;

    @SerializedName("longitude")
    private float longitude;

    @SerializedName("latitude")
    private float latitude;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }


    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}

