package com.vania.mycontactlist;

public class Contact {
    private int contactID;
    private String contactName;
    private String streetAddress;
    private String phoneNumber;
    private String email;

    public Contact() {
        contactID = -1;
    }

    public int getContactID() {
        return contactID;
    }
    public void setContactID(int id) {
        this.contactID = id;
    }

    public String getContactName() {
        return contactName;
    }
    public void setContactName(String name) {
        this.contactName = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String address) {
        this.streetAddress = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}