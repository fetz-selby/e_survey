package com.steve.housing.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by SOVAVY on 5/8/2017.
 */

public class PersonMDL extends RealmObject {

    @PrimaryKey
    private String id;

    private String firstname;
    private String lastname;
    private String othername;
    private String imagedata;
    private String maritalStatus;
    private String disability;
    private String idCardImage;
    private String idCardType;
    private String getIdCardDigit;

    public String getIdCardImage() {
        return idCardImage;
    }

    public void setIdCardImage(String idCardImage) {
        this.idCardImage = idCardImage;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getGetIdCardDigit() {
        return getIdCardDigit;
    }

    public void setGetIdCardDigit(String getIdCardDigit) {
        this.getIdCardDigit = getIdCardDigit;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getImagedata() {
        return imagedata;
    }

    public void setImagedata(String imagedata) {
        this.imagedata = imagedata;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }
}