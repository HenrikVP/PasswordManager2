package com.example.myfirstapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataIO {

    private SharedPreferences prefs;
    private Gson gson;

    private static DataIO instance;
    private List<Account> accounts;

    private DataIO(Context context) {
        prefs = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        accounts = new ArrayList<>();
        gson = new Gson();
        load();
    }

    private void save(){
        String json = gson.toJson(accounts);
        prefs.edit().putString("Accounts",json).apply();
    }

    private void load(){
        String json = prefs.getString("Accounts", null);
        if (json != null){
            Type type = new TypeToken<List<Account>>() {}.getType();
            accounts = gson.fromJson(json, type);
        }
        //else {save();}

//        String json = prefs.getString("Accounts", null);
//        if (json != null){
//            Type type = new TypeToken<List<Account>>() {}.getType();
//            accounts = gson.fromJson(json, type);
//        }
//        else {save();}
    }

    public void addAccount(Account account){
        accounts.add(account);
        save();
    }

    public static DataIO getInstance(Context context){
        if (instance == null) instance = new DataIO(context);
        return instance;
    }
    //TODO make load and save account methods

    public List<Account> getAccounts(){
        load();
        return accounts;
    }
}
