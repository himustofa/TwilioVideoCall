package com.quickstart.twiliovideocall.models;

public class User {

    private String uid;
    private String name;
    private String phone;
    private String active;

    public User(String uid, String name, String phone, String active) {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.active = active;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
