package com.app.firefighter.model;

public class Accident {
    private String key;
    private String address;
    private String location;
    private String notes;
    private String userName;
    private int state;
    private Department department;


    public Accident() {
    }

    public Accident(String key, String address, String location, String notes, String userName, int state, Department department) {
        this.key = key;
        this.address = address;
        this.location = location;
        this.notes = notes;
        this.userName = userName;
        this.state = state;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
