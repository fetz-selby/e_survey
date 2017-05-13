package com.steve.housing.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 5/12/17.
 */


//        location		: {
//        gps 		: { lat: Number, lng: Number },
//        region 		: { type: Schema.Types.ObjectId, ref: 'Region' },
//        town 		: String,
//        district	: { type: Schema.Types.ObjectId },
//        what3words	: String
//        },

public class LocationMDL extends RealmObject{
    @PrimaryKey
    private String id;
    @SerializedName("gps")
    private GpsCoordinatesMDL gps;
    @SerializedName("region")
    private RegionMDL regionMDL;
    @SerializedName("district")
    private DistrictMDL districtMDL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GpsCoordinatesMDL getGps() {
        return gps;
    }

    public void setGps(GpsCoordinatesMDL gps) {
        this.gps = gps;
    }

    public RegionMDL getRegionMDL() {
        return regionMDL;
    }

    public void setRegionMDL(RegionMDL regionMDL) {
        this.regionMDL = regionMDL;
    }

    public DistrictMDL getDistrictMDL() {
        return districtMDL;
    }

    public void setDistrictMDL(DistrictMDL districtMDL) {
        this.districtMDL = districtMDL;
    }


}
