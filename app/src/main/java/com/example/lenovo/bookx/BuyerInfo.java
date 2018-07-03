package com.example.lenovo.bookx;

/**
 * Created by Lenovo on 20-02-2018.
 */

public class BuyerInfo {

    public String name;
    public String email;
    public String contact;
    public String loc;
    public String type;
    public String nameB;
    public String author;
    public String emailS;
    public String status;

    public BuyerInfo() {
    }

    public BuyerInfo(String name, String email, String contact, String loc, String type, String nameB, String author, String emailS, String status) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.loc = loc;
        this.type = type;
        this.nameB = nameB;
        this.author = author;
        this.emailS = emailS;
        this.status = status;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmailS() {
        return emailS;
    }

    public void setEmailS(String emailS) {
        this.emailS = emailS;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
