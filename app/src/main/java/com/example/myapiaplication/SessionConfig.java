package com.example.myapiaplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;


public class SessionConfig {
    private Context context;
    private SharedPreferences sharedPreferences;
    private ListManager listManager;

    public SessionConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.sharedPref_initialize), Context.MODE_PRIVATE);
        listManager = new ListManager(context);
    }

    public void setPhone(String Phone) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.sharedPref_phone), Phone);
        editor.apply();
    }
    public String getPhone() {
        return sharedPreferences.getString(context.getResources().getString(R.string.sharedPref_phone),"");    }
    public void setMyModelList(List<MyModel> myModelList) {
        listManager.setMyModelList(context.getResources().getString(R.string.my_model_list_key), myModelList);
    }
    public List<MyModel> getMyModelList() {
        return listManager.getMyModelList(context.getResources().getString(R.string.my_model_list_key));
    }
    public void setMyTeacherModelList(List<MyTeacherModel> myModelList) {
        listManager.setMyTeacherModelList(context.getResources().getString(R.string.my_teacher_model_list_key), myModelList);
    }
    public List<MyTeacherModel> getMyTeacherModelList() {
        return listManager.getMyTeacherModelList(context.getResources().getString(R.string.my_teacher_model_list_key));
    }
    public void setMyStringList(List<String> myModelList) {
        listManager.setStringList(context.getResources().getString(R.string.string_list), myModelList);
    }
    public List<String> getMyStringList() {
        return listManager.getStringList(context.getResources().getString(R.string.string_list));
    }



    public void setNewMyModelList(List<MyModel> myModelList) {
        listManager.setMyModelList(context.getResources().getString(R.string.my_new_model_list_key), myModelList);
    }
    public List<MyModel> getNewMyModelList() {
        return listManager.getMyModelList(context.getResources().getString(R.string.my_new_model_list_key));
    }
    public void setOwnersModelList(List<OwnersModel> myModelList) {
        listManager.setOwnersModelList(context.getResources().getString(R.string.my_owner_model_list_key), myModelList);
    }
    public List<OwnersModel> getOwnersModelList() {
        return listManager.getOwnersModelList(context.getResources().getString(R.string.my_owner_model_list_key));
    }

    public void setRegisterationList(List<RegistrationModel> myModelList) {
        listManager.setRegisterationList(context.getResources().getString(R.string.registrationList), myModelList);
    }
    public List<RegistrationModel> getRegisterationList() {
        return listManager.getRegisterationList(context.getResources().getString(R.string.registrationList));
    }




    // Making for the swipe deletion in recycler view
    public void removeRegistrationItem(int position) {
        List<RegistrationModel> registrationList = getRegisterationList();
        if (registrationList != null && position >= 0 && position < registrationList.size()) {
            registrationList.remove(position);
            // Save the updated list
            listManager.setRegisterationList(context.getResources().getString(R.string.registrationList), registrationList);
        }
    }

    // Add a method to restore an item to the registration list
    public void restoreRegistrationItem(RegistrationModel item, int position) {
        List<RegistrationModel> registrationList = getRegisterationList();
        if (registrationList != null && position >= 0 && position <= registrationList.size()) {
            registrationList.add(position, item);
            // Save the updated list
            listManager.setRegisterationList(context.getResources().getString(R.string.registrationList), registrationList);
        }
    }





    /// For Storing Multiple List in single shared Preferences here the code is

    public void setListsContainer(ListsContainer listsContainer) {
        listManager.setListsContainer(listsContainer);
    }

    public ListsContainer getListsContainer() {
        return listManager.getListsContainer();
    }
/// To Set List In Main Activity write this code

  /*// Assuming you have an instance of SessionConfig
SessionConfig sessionConfig = new SessionConfig(this);

// Create and populate a ListsContainer
ListsContainer listsContainer = new ListsContainer();
listsContainer.setMyModelList(myModelList);
listsContainer.setMyTeacherModelList(myTeacherModelList);
listsContainer.setStringList(stringList);
// Set other lists in the ListsContainer as needed

// Store the ListsContainer
sessionConfig.setListsContainer(listsContainer);*/


// to get this list in any where

//write this code


    /*// Assuming you have an instance of SessionConfig
SessionConfig sessionConfig = new SessionConfig(this);

// Retrieve the ListsContainer from SharedPreferences
ListsContainer retrievedListsContainer = sessionConfig.getListsContainer();

// Retrieve the List<MyModel> myModelList from ListsContainer
List<MyModel> myModelList = retrievedListsContainer.getMyModelList();*/


}
