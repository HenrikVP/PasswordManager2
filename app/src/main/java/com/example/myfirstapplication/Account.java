package com.example.myfirstapplication;

import android.os.Parcelable;

import java.io.Serializable;

public class Account implements Serializable {
    private String accountName;
    private String userName;
    private String password;

    public Account(String accountName, String userName, String password) {
        this.accountName = accountName;
        this.userName = userName;
        this.password = password;
    }

    public String getAccountName() { return accountName;}
    public void setAccountName(String accountName) { this.accountName = accountName;}
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getPassword() {return password;}
    public void setPassword(String password) { this.password = password;}
}
