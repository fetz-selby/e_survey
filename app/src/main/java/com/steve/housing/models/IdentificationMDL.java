package com.steve.housing.models;

import com.google.gson.annotations.SerializedName;

import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 5/12/17.
 */


//        type  		: String,
//        number		: String,
//        picture		: String
//        },

public class IdentificationMDL {


    @PrimaryKey
    private String id;
    @SerializedName("type")
    private String identificationType;
    @SerializedName("number")
    private String identificationNumber;
    @SerializedName("picture")
    private String identificationPicture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getIdentificationPicture() {
        return identificationPicture;
    }

    public void setIdentificationPicture(String identificationPicture) {
        this.identificationPicture = identificationPicture;
    }


}
