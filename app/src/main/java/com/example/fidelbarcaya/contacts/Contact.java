package com.example.fidelbarcaya.contacts;

import java.util.Date;

/**
 * Created by fidel.barcaya on 7/9/2017.
 */

public class Contact {
    private transient String id;
    private String name;
    private String address;
    private String birthDate;
    private String phoneNumber;
    private String email;

    public Contact(String name, String address, String birthDate, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Contact() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
