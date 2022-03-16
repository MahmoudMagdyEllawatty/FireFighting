package com.app.firefighter.model;

public class Complaint {
    private String key;
    private String name;
    private String email;
    private String phone;
    private String message;

    public Complaint() {
    }

    public Complaint(String key, String name, String email, String phone, String message) {
        this.key = key;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
