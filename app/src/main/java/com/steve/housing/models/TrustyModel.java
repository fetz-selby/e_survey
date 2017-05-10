package com.steve.housing.models;

import io.realm.RealmObject;

/**
 * Created by SOVAVY on 5/10/2017.
 */

public class TrustyModel extends RealmObject {
    //        trustBeneficiaries: [{
//        name 		: String,
//        percentage 	: Number,
//        address 	: String,
//        city 		: String,
//        phone 		: String
//        }],

    private String id;
    private String trustBeneficiaryName;
    private float trustBeneficiaryPercentage;
    private String trustBeneficiaryAddress;
    private String trustBeneficiaryCity;
    private String getTrustBeneficiaryPhone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrustBeneficiaryName() {
        return trustBeneficiaryName;
    }

    public void setTrustBeneficiaryName(String trustBeneficiaryName) {
        this.trustBeneficiaryName = trustBeneficiaryName;
    }

    public float getTrustBeneficiaryPercentage() {
        return trustBeneficiaryPercentage;
    }

    public void setTrustBeneficiaryPercentage(float trustBeneficiaryPercentage) {
        this.trustBeneficiaryPercentage = trustBeneficiaryPercentage;
    }

    public String getTrustBeneficiaryAddress() {
        return trustBeneficiaryAddress;
    }

    public void setTrustBeneficiaryAddress(String trustBeneficiaryAddress) {
        this.trustBeneficiaryAddress = trustBeneficiaryAddress;
    }

    public String getTrustBeneficiaryCity() {
        return trustBeneficiaryCity;
    }

    public void setTrustBeneficiaryCity(String trustBeneficiaryCity) {
        this.trustBeneficiaryCity = trustBeneficiaryCity;
    }

    public String getGetTrustBeneficiaryPhone() {
        return getTrustBeneficiaryPhone;
    }

    public void setGetTrustBeneficiaryPhone(String getTrustBeneficiaryPhone) {
        this.getTrustBeneficiaryPhone = getTrustBeneficiaryPhone;
    }


}
