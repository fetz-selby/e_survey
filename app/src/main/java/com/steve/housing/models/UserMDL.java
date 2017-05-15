package com.steve.housing.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 5/14/17.
 */

//{"success":true,"message":"Login successful",
//        "agent":{"_id":"591082e6225a490004349316",
//        "district":"59091f695d0ba232bf920fbe",
//        "email":"agent@gmail.com",
//        "phone":"02484884499",
//        "surname":"Meulenteen",
//        "firstname":"Jen",
//        "__v":0,"status":"A",
//        "modifiedDate":"2017-05-08T14:38:30.118Z"
//        ,"createdDate":"2017-05-08T14:38:30.118Z",
//        "identification":[],
//        "id":"591082e6225a490004349316"}}

public class UserMDL extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private String id;
    @SerializedName("firstname")
    private String firstName;
    @SerializedName("surname")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("district")
    private String districtMDL;

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    private String webId;


    private String createdDate;


    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistrictMDL() {
        return districtMDL;
    }

    public void setDistrictMDL(String districtMDL) {
        this.districtMDL = districtMDL;
    }
}
