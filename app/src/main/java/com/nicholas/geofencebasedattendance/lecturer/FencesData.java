package com.nicholas.geofencebasedattendance.lecturer;

public class FencesData {
    private String id;
    private String latitude;
    private String logtitude;
    private String unittitle;
    private String unitcode;

    public FencesData() {
    }
    public FencesData(String id,String latitude, String logtitude, String unittitle, String unitcode) {
        this.id = id;
        this.latitude = latitude;
        this.logtitude = logtitude;
        this.unittitle = unittitle;
        this.unitcode = unitcode;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLogtitude() {
        return logtitude;
    }

    public void setLogtitude(String logtitude) {
        this.logtitude = logtitude;
    }

    public String getUnittitle() {
        return unittitle;
    }

    public void setUnittitle(String unittitle) {
        this.unittitle = unittitle;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
}
