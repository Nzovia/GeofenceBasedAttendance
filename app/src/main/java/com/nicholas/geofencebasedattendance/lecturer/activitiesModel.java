package com.nicholas.geofencebasedattendance.lecturer;

public class activitiesModel {
    private String id;
    private String title;
    private String description;
    private String time;
    private String comment;

    public activitiesModel() {
    }

    public activitiesModel(String id, String title, String description, String time, String comment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
