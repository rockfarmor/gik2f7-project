package com.project.scheduleapp.demo.Model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Shift {
    private int shiftID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private boolean isReal;

    private Account account;


    public Shift(int shiftID, LocalDateTime startDate, LocalDateTime endDate, String description, Account a) {
        this.shiftID = shiftID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isReal = true;
        this.account = a;
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

    public Account getAccount() {
        return account;
    }
}
