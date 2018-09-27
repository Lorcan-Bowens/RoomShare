package com.example.lorca.roomshareapp;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Lorca on 09/04/2018.
 */

public class User {
    public String uID;
    public String name;
    public String sex;
    public String phone;


    public User() {

    }

    public User(String name, String sex, String phone, String uID) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.uID = uID;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}