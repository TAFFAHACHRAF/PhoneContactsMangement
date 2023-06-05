package com.example.contact.entities;

public class Contact {
    private long CONTACT_ID;
    String CONTACT_FIRSTNAME;
    String CONTACT_LASTNAME;
    String CONTACT_EMAIL;
    String CONTACT_PHONE;
    String CONTACT_TYPE;
    String CONTACT_GENDER;

    public Contact(){

    }

    public Contact(long CONTACT_ID, String CONTACT_FIRSTNAME, String CONTACT_LASTNAME, String CONTACT_EMAIL, String CONTACT_PHONE, String CONTACT_TYPE, String CONTACT_GENDER) {
        this.CONTACT_ID = CONTACT_ID;
        this.CONTACT_FIRSTNAME = CONTACT_FIRSTNAME;
        this.CONTACT_LASTNAME = CONTACT_LASTNAME;
        this.CONTACT_EMAIL = CONTACT_EMAIL;
        this.CONTACT_PHONE = CONTACT_PHONE;
        this.CONTACT_TYPE = CONTACT_TYPE;
        this.CONTACT_GENDER = CONTACT_GENDER;
    }

    public Contact(Contact contact) {
        this.CONTACT_ID = contact.CONTACT_ID+1;
        this.CONTACT_FIRSTNAME = contact.CONTACT_FIRSTNAME;
        this.CONTACT_LASTNAME = contact.CONTACT_LASTNAME;
        this.CONTACT_EMAIL = contact.CONTACT_EMAIL;
        this.CONTACT_PHONE = contact.CONTACT_PHONE;
        this.CONTACT_TYPE = contact.CONTACT_TYPE;
        this.CONTACT_GENDER = contact.CONTACT_GENDER;
    }

    public long getCONTACT_ID() {
        return CONTACT_ID;
    }

    public void setCONTACT_ID(long CONTACT_ID) {
        this.CONTACT_ID = CONTACT_ID;
    }

    public String getCONTACT_FIRSTNAME() {
        return CONTACT_FIRSTNAME;
    }

    public void setCONTACT_FIRSTNAME(String CONTACT_FIRSTNAME) {
        this.CONTACT_FIRSTNAME = CONTACT_FIRSTNAME;
    }

    public String getCONTACT_LASTNAME() {
        return CONTACT_LASTNAME;
    }

    public void setCONTACT_LASTNAME(String CONTACT_LASTNAME) {
        this.CONTACT_LASTNAME = CONTACT_LASTNAME;
    }

    public String getCONTACT_EMAIL() {
        return CONTACT_EMAIL;
    }

    public void setCONTACT_EMAIL(String CONTACT_EMAIL) {
        this.CONTACT_EMAIL = CONTACT_EMAIL;
    }

    public String getCONTACT_PHONE() {
        return CONTACT_PHONE;
    }

    public void setCONTACT_PHONE(String CONTACT_PHONE) {
        this.CONTACT_PHONE = CONTACT_PHONE;
    }

    public String getCONTACT_TYPE() {
        return CONTACT_TYPE;
    }

    public void setCONTACT_TYPE(String CONTACT_TYPE) {
        this.CONTACT_TYPE = CONTACT_TYPE;
    }

    public String getCONTACT_GENDER() {
        return CONTACT_GENDER;
    }

    public void setCONTACT_GENDER(String CONTACT_GENDER) {
        this.CONTACT_GENDER = CONTACT_GENDER;
    }
}