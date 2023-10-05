package com.example.myapiaplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapiaplication.MyModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListManager {
    private static final String PREF_NAME = "MyPrefs";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public ListManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void setMyModelList(String key, List<MyModel> myModelList) {
        String json = gson.toJson(myModelList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    public List<MyModel> getMyModelList(String key) {
        String json = sharedPreferences.getString(key, "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<MyModel>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }
    public void setMyTeacherModelList(String key, List<MyTeacherModel> myModelList) {
        String json = gson.toJson(myModelList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }


    public List<MyTeacherModel> getMyTeacherModelList(String key) {
        String json = sharedPreferences.getString(key, "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<MyTeacherModel>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }

    public void setStringList(String key, List<String> stringList) {
        String json = gson.toJson(stringList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    // Retrieve a list of String objects
    public List<String> getStringList(String key) {
        String json = sharedPreferences.getString(key, "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<String>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }

    public void setNewMyModelList(String key, List<MyModel> myModelList) {
        String json = gson.toJson(myModelList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    public List<MyModel> getNewMyModelList(String key) {
        String json = sharedPreferences.getString(key, "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<MyModel>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }
    public void setOwnersModelList(String key, List<OwnersModel> myModelList) {
        String json = gson.toJson(myModelList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    public List<OwnersModel> getOwnersModelList(String key) {
        String json = sharedPreferences.getString(key, "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<OwnersModel>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }


    public void setRegisterationList(String key, List<RegistrationModel> myModelList) {
        String json = gson.toJson(myModelList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    public List<RegistrationModel> getRegisterationList(String key) {
        String json = sharedPreferences.getString(key, "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<RegistrationModel>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }





    // For Storing Multiple  List in Sigle lIst Conatiner Class
    public void setListsContainer(ListsContainer listsContainer) {
        String json = gson.toJson(listsContainer);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lists_container", json);
        editor.apply();
    }

    public ListsContainer getListsContainer() {
        String json = sharedPreferences.getString("lists_container", "");
        if (!json.isEmpty()) {
            return gson.fromJson(json, ListsContainer.class);
        } else {
            return new ListsContainer();
        }
    }
}
