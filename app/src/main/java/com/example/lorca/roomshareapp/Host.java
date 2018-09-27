package com.example.lorca.roomshareapp;

/**
 * Created by Lorca on 21/04/2018.
 */

public class Host {
    public String hostName;
    public String company;
    public String address;
    public String description;
    public String enSuite;
    public String askingPrice;

    public Host(){

    }

    public Host(String hostName, String company, String address, String description, String enSuite, String askingPrice) {
        this.hostName = hostName;
        this.company = company;
        this.address = address;
        this.description = description;
        this.enSuite = enSuite;
        this.askingPrice = askingPrice;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnSuite() {
        return enSuite;
    }

    public void setEnSuite(String enSuite) {
        this.enSuite = enSuite;
    }

    public String getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(String askingPrice) {
        this.askingPrice = askingPrice;
    }
}
