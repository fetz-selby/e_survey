package com.steve.housing.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 5/12/17.
 */

public class AgentPropertyMDL extends RealmObject {

//        agent           : {
//        name 		: String,
//        address		: String,
//        city		: String,
//        phone		: String,
//        email		: String
//        },

    @PrimaryKey
    private String id;
    @SerializedName("name")
    private String AgentContactName;
    @SerializedName("address")
    private String AgentContactAddress;
    @SerializedName("city")
    private String AgentContactCity;
    @SerializedName("phone")
    private String AgentContactphone;
    @SerializedName("email")
    private String AgentContactEmail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAgentContactName() {
        return AgentContactName;
    }

    public void setAgentContactName(String agentContactName) {
        AgentContactName = agentContactName;
    }

    public String getAgentContactAddress() {
        return AgentContactAddress;
    }

    public void setAgentContactAddress(String agentContactAddress) {
        AgentContactAddress = agentContactAddress;
    }

    public String getAgentContactCity() {
        return AgentContactCity;
    }

    public void setAgentContactCity(String agentContactCity) {
        AgentContactCity = agentContactCity;
    }

    public String getAgentContactphone() {
        return AgentContactphone;
    }

    public void setAgentContactphone(String agentContactphone) {
        AgentContactphone = agentContactphone;
    }

    public String getAgentContactEmail() {
        return AgentContactEmail;
    }

    public void setAgentContactEmail(String agentContactEmail) {
        AgentContactEmail = agentContactEmail;
    }
}
