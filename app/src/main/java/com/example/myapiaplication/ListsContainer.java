package com.example.myapiaplication;

import java.util.List;

public class ListsContainer {


    /// For storing Multiple list in single shared preferences class
    private List<MyModel> myModelList;
    private List<MyTeacherModel> myTeacherModelList;
    private List<String> stringList;
    // Add more lists here as needed

    // Getters and setters for each list
    public List<MyModel> getMyModelList() {
        return myModelList;
    }

    public void setMyModelList(List<MyModel> myModelList) {
        this.myModelList = myModelList;
    }

    public List<MyTeacherModel> getMyTeacherModelList() {
        return myTeacherModelList;
    }

    public void setMyTeacherModelList(List<MyTeacherModel> myTeacherModelList) {
        this.myTeacherModelList = myTeacherModelList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
    // Add getters and setters for other lists as needed
}
