package com.project.scheduleapp.demo.Model;

import com.project.scheduleapp.demo.helpers.Helper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Shift {
    private int shiftID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private boolean isReal;

    private Category category;
    private Personal account;


    public Shift(int shiftID, LocalDateTime startDate, LocalDateTime endDate, String description, Personal a) {
        this.shiftID = shiftID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isReal = true;
        this.account = a;
        this.category = Category.Category1;
    }

    public Shift(int shiftID, LocalDateTime startDate, LocalDateTime endDate, String description) {
        this.shiftID = shiftID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isReal = true;
        this.account = null;
    }

    public String getDayString(){
        return startDate.getYear() + "-" + startDate.getMonthValue() + "-" + startDate.getDayOfMonth();
    }

    public boolean sameDay(LocalDateTime day) {
        return day.getYear() == startDate.getYear() && day.getMonthValue() == startDate.getMonthValue() && day.getDayOfMonth() == startDate.getDayOfMonth();
    }

    public boolean dateIsMonth(int month){
        return startDate.getDayOfMonth() == month;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isReal() {
        return isReal;
    }

    public void setReal(boolean real) {
        isReal = real;
    }

    public int getStartYear() {
        return this.startDate.getYear();
    }

    public  int getStartMonth() {
        return this.startDate.getMonthValue();
    }

    public  int getStartDay() {
        return this.startDate.getDayOfMonth();
    }

    public int getEndYear() {
        return this.endDate.getYear();
    }

    public  int getEndMonth() {
        return this.endDate.getMonthValue();
    }

    public  int getEndDay() {
        return this.endDate.getDayOfMonth();
    }

    public Personal getAccount() {
        return account;
    }

<<<<<<< HEAD
    public String getSchedDay(){
        System.out.println(Helper.getSchedDay(this.startDate));

        return Helper.getSchedDay(this.startDate);

    }



=======
    public Category getCategory() {
        return category;
    }
>>>>>>> 5c8de75ad80f544c50ce14d1f69cd25b9b0a2031

    @Override
    public String toString() {
        return "Shift{" +
                "shiftID=" + shiftID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", isReal=" + isReal +
                ", account=" + account +
                '}';
    }
}
