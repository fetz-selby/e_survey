package com.steve.housing.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

//package com.steve.housing.models;
//
//import io.realm.RealmObject;
//
///**
// * Created by SOVAVY on 5/10/2017.
// */
//
public class DistrictMDL extends RealmObject {

    @PrimaryKey
    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String region;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        region = region;}
}
