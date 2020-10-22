package com.project.scheduleapp.demo.Model;

public class Admin extends Personal {
    private int adminID;

    public Admin(int uniqueID, String name, String userName, String password, int salaryPerHour) {
        super(uniqueID, name, userName, password, salaryPerHour);
    }


    public void addPersonal(){

    }
    public void assignWorkShift(){

    }
    public void addCategory(){

    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

}
