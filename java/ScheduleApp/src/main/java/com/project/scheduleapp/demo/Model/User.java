package com.project.scheduleapp.demo.Model;

public class User {
    private String userID;
    private String password;
    private int admin;

    public User(String userID,String password,int admin){
        this.userID=userID;
        this.password=password;
        this.admin=admin;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
