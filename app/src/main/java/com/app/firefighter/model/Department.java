package com.app.firefighter.model;

public class Department {
    private String key;
    private String name;
    private String phone;
    private String userName;
    private String password;

    public Department() {
    }

    public Department(String key, String name, String phone, String userName, String password) {
        this.key = key;
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
