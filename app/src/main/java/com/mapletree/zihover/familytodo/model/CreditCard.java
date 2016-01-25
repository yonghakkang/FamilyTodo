package com.mapletree.zihover.familytodo.model;

/**
 * Created by yonghak on 2016-01-25.
 */
public class CreditCard {

    private int id;
    private String name;
    private String phoneNumber;

    public CreditCard() {
    }
    public CreditCard(String name, String phoneNumber) {
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
