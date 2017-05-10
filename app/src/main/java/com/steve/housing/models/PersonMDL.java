package com.steve.housing.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by SOVAVY on 5/8/2017.
 */

//
//firstname		: String,
//        surname			: String,
//        othernames 		: String,
//        dob 			: Date,
//        birthPlace 		: String,
//        nationality 	: String,
//        nationalityType : String,
//        dualCitizenship	: [String],
//        ethnicity 		: String,
//        maritalStatus 	: String,
//        languages 		: {
//        spoken 		: [String],
//        written 	: [String]
//        },
//        phones			: [String],
//        email 			: String,
//        address 		: {
//        residential : String,
//        postal 		: String,
//        work 		: String
//        },
//        region 			: String,
//        districtType 	: String,
//        homeGps			: {
//        lat		: String,
//        lng		: String
//        },
//        what3words 		: {
//        home		: String,
//        work 		: String
//        },
//        photo 			: String,
//        employmentStatus: String,
//        employer 		: String,
//        occupation 		: String,
//        commencementDate: Date,
//        position 		: String,
//        employmentSector: String,
//        disability		: [String],
//        identification	: {
//        type  		: String,
//        number		: String,
//        picture		: String
//        },
//
//        createdBy 		: { type: Schema.Types.ObjectId, ref: 'Agent' },
//        createdDate 	: { type: Date, default: Date.now },
public class PersonMDL extends RealmObject {


    @PrimaryKey
    private String id;

    private String firstname;
    private String lastname;
    private String othername;
    private String maritalStatus;
    private String disability;
    private String dob;

    public String getNationalityType() {
        return nationalityType;
    }

    public void setNationalityType(String nationalityType) {
        this.nationalityType = nationalityType;
    }

    private String nationalityType;
    private String nationality;
    private String dualCityzenship;
    private String ethnicity;
    private String ownerPhoto;
    private String employer;
    private String employmentStatus;
    private String employmentSector;
    private String position;

    public String getWorkplaceLocation() {
        return workplaceLocation;
    }

    public void setWorkplaceLocation(String workplaceLocation) {
        this.workplaceLocation = workplaceLocation;
    }

    private String workplaceLocation;

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    private String profession;
    private String commencementDate;
    private String identificationType;
    private String identificationNumber;
    private String identificationPicture;
    private String PhoneNumber;
    private String additionalPhoneNumber;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    private String district ;
    private String languageWritten;
    private String languageSpoken;

    public String getLanguageSpokenWritten() {
        return languageSpokenWritten;
    }

    public void setLanguageSpokenWritten(String languageSpokenWritten) {
        this.languageSpokenWritten = languageSpokenWritten;
    }

    private String languageSpokenWritten;

    private String ResidentialAddress;
    private String PostalAddress;
    private String email;
    private String createdBy;
    private String createdAt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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


    public String getLanguageWritten() {
        return languageWritten;
    }

    public void setLanguageWritten(String languageWritten) {
        this.languageWritten = languageWritten;
    }

    public String getLanguageSpoken() {
        return languageSpoken;
    }

    public void setLanguageSpoken(String languageSpoken) {
        this.languageSpoken = languageSpoken;
    }


    public String getResidentialAddress() {
        return ResidentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        ResidentialAddress = residentialAddress;
    }

    public String getPostalAddress() {
        return PostalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        PostalAddress = postalAddress;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDualCityzenship() {
        return dualCityzenship;
    }

    public void setDualCityzenship(String dualCityzenship) {
        this.dualCityzenship = dualCityzenship;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getOwnerPhoto() {
        return ownerPhoto;
    }

    public void setOwnerPhoto(String ownerPhoto) {
        this.ownerPhoto = ownerPhoto;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getEmploymentSector() {
        return employmentSector;
    }

    public void setEmploymentSector(String employmentSector) {
        this.employmentSector = employmentSector;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(String commencementDate) {
        this.commencementDate = commencementDate;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAdditionalPhoneNumber() {
        return additionalPhoneNumber;
    }

    public void setAdditionalPhoneNumber(String additionalPhoneNumber) {
        this.additionalPhoneNumber = additionalPhoneNumber;
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