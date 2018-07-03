package com.example.lenovo.bookx;

import com.google.firebase.database.IgnoreExtraProperties;


/**
 * Created by Lenovo on 16-06-2017.
 */

public class UserInformation {


    public String author;
    public  String email;
    public String name;
    public String nameB;
    public String phone;
    public String price;
    public String subject;
    public String type;
    public String status;

    public UserInformation() {
    }

    public UserInformation(String author, String email, String name, String nameB, String phone, String price, String subject, String type, String status) {
        this.author = author;
        this.email = email;
        this.name = name;
        this.nameB = nameB;
        this.phone = phone;
        this.price = price;
        this.subject = subject;
        this.type = type;
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
