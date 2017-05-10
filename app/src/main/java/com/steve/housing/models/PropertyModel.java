package com.steve.housing.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by SOVAVY on 5/7/2017.
 */

//
//pins 			: String,
//        propertyType	: String,
//        classification 	: String,
//        ownershipType 	: {
//        status 		: { type: String, enum: ['Legal', 'Illegal'] },
//        registered  : { type: Boolean },
//        titleNumber : String,
//        indentureNumber: String
//        },
//        location		: {
//        gps 		: { lat: Number, lng: Number },
//        region 		: { type: Schema.Types.ObjectId, ref: 'Region' },
//        town 		: String,
//        district	: { type: Schema.Types.ObjectId },
//        what3words	: String
//        },
//        address 		: String,
//        familyUnits     : Number,
//        electricitySource: String,
//        partnership		:[{
//        name 		: String,
//        percentage	: Number
//        }],
//        partnershipType : String,
//
//        emergency		: {
//        name 		: String,
//        address		: String,
//        city		: String,
//        phone		: String,
//        email		: String
//        },
//        agent           : {
//        name 		: String,
//        address		: String,
//        city		: String,
//        phone		: String,
//        email		: String
//        },
//        propertyManager	: {
//        name 		: String,
//        address		: String,
//        city		: String,
//        phone		: String,
//        email		: String,
//        licenseNumber: String
//        },
//        landTrust		: Boolean,
//        trustBeneficiaries: [{
//        name 		: String,
//        percentage 	: Number,
//        address 	: String,
//        city 		: String,
//        phone 		: String
//        }],
//        owners 			: [{ type: Schema.Types.ObjectId, ref: 'People' }],

public class PropertyModel extends RealmObject {
    private String id;
    private String pins ;
    private String propertyType;
    private String classification;
    private String ownershipType;
    private boolean status;
    private boolean registered;
    private String titleNumber;
    private String identureNumber;
    private float longitude ;
    private float latidude;
    private DistrictModel district;
    private String address;
    private String familyUnit;
    private String electricitySource;
    private String partnershipName;
    private float partnershipPercentage;
    private String partnershipType;
    private RealmList<PersonMDL> ownerList;
    private String emergencyContactName;
    private String emergencyContactAddress;
    private String emergencyContactCity;
    private String emergencyContactphone;
    private String emergencyContactPhone;
    private String emergencyContactEmail;
    private String propertyManagerContactName;
    private String propertyManagerContactAddress;
    private String propertyManagerContactCity;
    private String propertyManagerContactphone;
    private String propertyManagerPhone;
    private String propertyManagerEmail;
    private boolean landTrust;
    private RealmList<TrustyModel> trusties;
    private String createdBy ;
    private String createdAt;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPins() {
        return pins;
    }

    public void setPins(String pins) {
        this.pins = pins;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getTitleNumber() {
        return titleNumber;
    }

    public void setTitleNumber(String titleNumber) {
        this.titleNumber = titleNumber;
    }

    public String getIdentureNumber() {
        return identureNumber;
    }

    public void setIdentureNumber(String identureNumber) {
        this.identureNumber = identureNumber;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatidude() {
        return latidude;
    }

    public void setLatidude(float latidude) {
        this.latidude = latidude;
    }

    public DistrictModel getDistrict() {
        return district;
    }

    public void setDistrict(DistrictModel district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFamilyUnit() {
        return familyUnit;
    }

    public void setFamilyUnit(String familyUnit) {
        this.familyUnit = familyUnit;
    }

    public String getElectricitySource() {
        return electricitySource;
    }

    public void setElectricitySource(String electricitySource) {
        this.electricitySource = electricitySource;
    }

    public String getPartnershipName() {
        return partnershipName;
    }

    public void setPartnershipName(String partnershipName) {
        this.partnershipName = partnershipName;
    }

    public float getPartnershipPercentage() {
        return partnershipPercentage;
    }

    public void setPartnershipPercentage(float partnershipPercentage) {
        this.partnershipPercentage = partnershipPercentage;
    }

    public String getPartnershipType() {
        return partnershipType;
    }

    public void setPartnershipType(String partnershipType) {
        this.partnershipType = partnershipType;
    }

    public RealmList<PersonMDL> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(RealmList<PersonMDL> ownerList) {
        this.ownerList = ownerList;
    }

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

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getEmergencyContactEmail() {
        return emergencyContactEmail;
    }

    public void setEmergencyContactEmail(String emergencyContactEmail) {
        this.emergencyContactEmail = emergencyContactEmail;
    }

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

    public String getPropertyManagerPhone() {
        return propertyManagerPhone;
    }

    public void setPropertyManagerPhone(String propertyManagerPhone) {
        this.propertyManagerPhone = propertyManagerPhone;
    }

    public String getPropertyManagerEmail() {
        return propertyManagerEmail;
    }

    public void setPropertyManagerEmail(String propertyManagerEmail) {
        this.propertyManagerEmail = propertyManagerEmail;
    }

    public boolean isLandTrust() {
        return landTrust;
    }

    public void setLandTrust(boolean landTrust) {
        this.landTrust = landTrust;
    }

    public RealmList<TrustyModel> getTrusties() {
        return trusties;
    }

    public void setTrusties(RealmList<TrustyModel> trusties) {
        this.trusties = trusties;
    }









}
