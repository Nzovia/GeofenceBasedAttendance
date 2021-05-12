package com.nicholas.geofencebasedattendance.Student;

import android.widget.EditText;

public class studentItem {

    private String name;
    private String registrationNo;
    private String email;



    public studentItem() {
    }

    public studentItem(String name, String registrationNo, String email) {
        this.name = name;
        this.registrationNo = registrationNo;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
