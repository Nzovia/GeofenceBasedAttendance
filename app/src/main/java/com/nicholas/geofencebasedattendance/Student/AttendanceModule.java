package com.nicholas.geofencebasedattendance.Student;

public class AttendanceModule {
    private String id;
    private String attendanceName;
    private String attendanceNumber;
    private String attendanceDate;
    private String attendanceTime;

    public AttendanceModule() {
    }

    public AttendanceModule(String id, String attendanceName, String attendanceNumber,
                            String attendanceDate, String attendanceTime) {
        this.id = id;
        this.attendanceName = attendanceName;
        this.attendanceNumber = attendanceNumber;
        this.attendanceDate = attendanceDate;
        this.attendanceTime = attendanceTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttendanceName() {
        return attendanceName;
    }

    public void setAttendanceName(String attendanceName) {
        this.attendanceName = attendanceName;
    }

    public String getAttendanceNumber() {
        return attendanceNumber;
    }

    public void setAttendanceNumber(String attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}
