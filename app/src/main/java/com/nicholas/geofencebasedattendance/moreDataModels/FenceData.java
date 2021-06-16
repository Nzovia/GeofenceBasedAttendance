package com.nicholas.geofencebasedattendance.moreDataModels;

public class FenceData {
    private String textLatitude;
    private String textLogtitude;
    private String unitTitle;
    private String unitCode;


    public FenceData(String latitude, String longitude, String unitname, String unitcode) {
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
