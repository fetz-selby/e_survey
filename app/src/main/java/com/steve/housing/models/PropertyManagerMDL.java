package com.steve.housing.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 5/12/17.
 */

public class PropertyManagerMDL extends RealmObject{

    //        propertyManager	: {
//        name 		: String,
//        address		: String,
//        city		: String,
//        phone		: String,
//        email		: String,
//        licenseNumber: String
//        },
    @PrimaryKey
    private String id;
    @SerializedName("name")
    private String propertyManagerContactName;
    @SerializedName("address")
    private String propertyManagerContactAddress;
    @SerializedName("city")
    private String propertyManagerContactCity;
    @SerializedName("phone")
    private String propertyManagerContactphone;
    @SerializedName("email")
    private String propertyManagerEmail;
    @SerializedName("licenseNumber")
    private String propertyManagerLicenseNumber;


    public String getPropertyManagerContactName() {
        return propertyManagerContactName;
    }

    public void setPropertyManagerContactName(String propertyManagerContactName) {
        this.propertyManagerContactName = propertyManagerContactName;
    }

    public String getPropertyManagerContactAddress() {
        return propertyManagerContactAddress;
    }

    public void setPropertyManagerContactAddress(String propertyManagerContactAddress) {
        this.propertyManagerContactAddress = propertyManagerContactAddress;
    }

    public String getPropertyManagerContactCity() {
        return propertyManagerContactCity;
    }

    public void setPropertyManagerContactCity(String propertyManagerContactCity) {
        this.propertyManagerContactCity = propertyManagerContactCity;
    }

    public String getPropertyManagerContactphone() {
        return propertyManagerContactphone;
    }

    public void setPropertyManagerContactphone(String propertyManagerContactphone) {
        this.propertyManagerContactphone = propertyManagerContactphone;
    }

    public String getPropertyManagerEmail() {
        return propertyManagerEmail;
    }

    public void setPropertyManagerEmail(String propertyManagerEmail) {
        this.propertyManagerEmail = propertyManagerEmail;
    }

    public String getPropertyManagerLicenseNumber() {
        return propertyManagerLicenseNumber;
    }

    public void setPropertyManagerLicenseNumber(String propertyManagerLicenseNumber) {
        this.propertyManagerLicenseNumber = propertyManagerLicenseNumber;
    }
}
