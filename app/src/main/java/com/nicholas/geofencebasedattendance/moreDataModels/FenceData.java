package com.nicholas.geofencebasedattendance.moreDataModels;

public class FenceData {
    private String id;
    private String textLatitude;
    private String textLogtitude;
    private String unitTitle;
    private String unitCode;


    public FenceData( ) {
    }

    public FenceData(String id, String textLatitude, String textLogtitude, String unitTitle, String unitCode) {
        this.id = id;
        this.textLatitude = textLatitude;
        this.textLogtitude = textLogtitude;
        this.unitTitle = unitTitle;
        this.unitCode = unitCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTextLatitude() {
        return textLatitude;
    }

    public void setTextLatitude(String textLatitude) {
        this.textLatitude = textLatitude;
    }

    public String getTextLogtitude() {
        return textLogtitude;
    }

    public void setTextLogtitude(String textLogtitude) {
        this.textLogtitude = textLogtitude;
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
