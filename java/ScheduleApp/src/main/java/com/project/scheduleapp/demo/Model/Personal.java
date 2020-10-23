package com.project.scheduleapp.demo.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Personal {
    protected int uniqueID;
    protected String name;
    protected String userName;
    protected String password;
    protected int salaryPerHour;
    protected List<Shift> schedlist;
    protected int isAdmin;

    public Personal(int uniqueID, String name, String userName, String password, int salaryPerHour, int isAdmin) {
        this.uniqueID = uniqueID;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.salaryPerHour = salaryPerHour;
        this.schedlist = new ArrayList<>();
        this.isAdmin = isAdmin;
    }

    public Personal(int uniqueID, String name, String userName, String password, int salaryPerHour) {
        this.uniqueID = uniqueID;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.salaryPerHour = salaryPerHour;
        this.schedlist = new ArrayList<>();
        this.isAdmin = 0;
    }

    public List<Shift> getListAfterDate(){
        List<Shift> shifts = new ArrayList<>();
        LocalDateTime n = LocalDateTime.now();
        LocalDateTime thisDay = LocalDateTime.of(n.getYear(), n.getMonth(), n.getDayOfMonth(), 0, 0);
        for (Shift s : this.schedlist) {
            if(s.getStartDate().isAfter(thisDay)) {
                shifts.add(s);
            }
        }

        return shifts;
    }


    public List<Shift> getListBeforeDate(){
        List<Shift> shifts = new ArrayList<>();
        LocalDateTime n = LocalDateTime.now();
        LocalDateTime thisDay = LocalDateTime.of(n.getYear(), n.getMonth(), n.getDayOfMonth(), 0, 0);
        for (Shift s : this.schedlist) {
            if(s.getStartDate().isBefore(thisDay)) {
                shifts.add(s);
            }
        }

        return shifts;
    }

    public void showSalary(){

    }
    public void logIn(){

    }
    public void showSchedule(){

    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(int salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public List<Shift> getSchedlist() {
        return schedlist;
    }

    public void setSchedlist(List<Shift> schedlist) {
        this.schedlist = schedlist;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
