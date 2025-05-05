package com.example.myfirstapplication;

import java.util.ArrayList;
import java.util.List;

public class DataIO {

    private static DataIO instance;
    private List<Account> accounts;

    private DataIO() {
        accounts = new ArrayList<>();
    }

    public static DataIO getInstance(){
        if (instance == null) instance = new DataIO();
        return instance;
    }
    //TODO make load and save account methods

    public List<Account> getAccounts(){
        return accounts;
    }
}
