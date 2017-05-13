package com.steve.housing.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 5/12/17.
 */

public class EmergencyContactMDL extends RealmObject{


    //        emergency		: {
//        name 		: String,
//        address		: String,
//        city		: String,
//        phone		: String,
//        email		: String
//        },
    @PrimaryKey
    private String id;
    @SerializedName("name")
    private String emergencyContactName;
    @SerializedName("address")
    private String emergencyContactAddress;
    @SerializedName("city")
    private String emergencyContactCity;
    @SerializedName("phone")
    private String emergencyContactphone;
    @SerializedName("email")
    private String emergencyContactEmail;

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactAddress() {
        return emergencyContactAddress;
    }

    public void setEmergencyContactAddress(String emergencyContactAddress) {
        this.emergencyContactAddress = emergencyContactAddress;
    }

    public String getEmergencyContactCity() {
        return emergencyContactCity;
    }

    public void setEmergencyContactCity(String emergencyContactCity) {
        this.emergencyContactCity = emergencyContactCity;
    }

    public String getEmergencyContactphone() {
        return emergencyContactphone;
    }

    public void setEmergencyContactphone(String emergencyContactphone) {
        this.emergencyContactphone = emergencyContactphone;
    }


    public String getEmergencyContactEmail() {
        return emergencyContactEmail;
    }

    public void setEmergencyContactEmail(String emergencyContactEmail) {
        this.emergencyContactEmail = emergencyContactEmail;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
