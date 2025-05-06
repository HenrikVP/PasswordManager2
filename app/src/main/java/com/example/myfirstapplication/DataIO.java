package com.example.myfirstapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataIO {

    String url = "http://10.131.202.230:8080/api/accounts";
    RequestQueue queue;
    private SharedPreferences prefs;
    private Gson gson;

    private static DataIO instance;
    private List<Account> accounts;

    private DataIO(Context context) {
        prefs = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        accounts = new ArrayList<>();
        gson = new Gson();
        queue = Volley.newRequestQueue(context);
        load();
        getAccountsFromApi();
    }

    public void getAccountsFromApi(){
        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                        if (response != null) {
                            Type type = new TypeToken<List<Account>>() {
                            }.getType();
                            accounts = gson.fromJson(response.toString(), type);
                        }
                },
                error -> {
                    Log.e("Volley", "getAccountsFromApi: " + error.getMessage());
                }
        );
        queue.add(jsonRequest);
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
