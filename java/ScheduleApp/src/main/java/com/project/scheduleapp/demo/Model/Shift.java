package com.project.scheduleapp.demo.Model;

import com.project.scheduleapp.demo.helpers.Helper;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Shift {
    private int shiftID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private boolean isReal;

    private Category category;
    private Personal account;


    public Shift(int shiftID, LocalDateTime startDate, LocalDateTime endDate, String description, Personal a, Category category) {
        this.shiftID = shiftID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isReal = true;
        this.account = a;
        this.category = category;
    }
    public Shift(int shiftID, LocalDateTime startDate, LocalDateTime endDate, String description, Personal a) {
        this.shiftID = shiftID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isReal = true;
        this.account = a;
        this.category = null;
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




    public int getTotalHour(){

        LocalDateTime tempDateTime = LocalDateTime.from( this.startDate );

        long years = tempDateTime.until( this.endDate, ChronoUnit.YEARS );
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( this.endDate, ChronoUnit.MONTHS );
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( this.endDate, ChronoUnit.DAYS );
        tempDateTime = tempDateTime.plusDays( days );


        long hours = tempDateTime.until( this.endDate, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( this.endDate, ChronoUnit.MINUTES );
        tempDateTime = tempDateTime.plusMinutes( minutes );

        long seconds = tempDateTime.until( this.endDate, ChronoUnit.SECONDS );

        return (int)hours;
    }

    public String getTotalTime(){

        LocalDateTime tempDateTime = LocalDateTime.from( this.startDate );

        long years = tempDateTime.until( this.endDate, ChronoUnit.YEARS );
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( this.endDate, ChronoUnit.MONTHS );
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( this.endDate, ChronoUnit.DAYS );
        tempDateTime = tempDateTime.plusDays( days );


        long hours = tempDateTime.until( this.endDate, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( this.endDate, ChronoUnit.MINUTES );
        tempDateTime = tempDateTime.plusMinutes( minutes );

        long seconds = tempDateTime.until( this.endDate, ChronoUnit.SECONDS );

        String s = hours + "h ";
        if(minutes > 0){
            s += minutes + "m";
        }

        return s;
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


    public String getSchedDay(){
        return Helper.getSchedDay(this.startDate);

    }

    public String getLocalDateString(){

        String month = this.startDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv","SE"));
        month = StringUtils.capitalize(month);
        month = this.startDate.getDayOfMonth() + " " + month;

        return month;
    }

    public String getLocalDateYearString(){

        String month = this.startDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv","SE"));
        month = StringUtils.capitalize(month);
        month = month + " " + this.startDate.getYear();

        return month;
    }

    public String getStartEndHourMinute(){
        return getStartHourMinute() + " - " + getEndHourMinute();
    }

    public String getStartHourMinute(){
        return Helper.getHourMinuteStr(this.startDate);
    }

    public String getEndHourMinute(){
        return Helper.getHourMinuteStr(this.endDate);
    }

    public Category getCategory() {
        if(category == null){
            return Category.Category1;
        }
        return category;
    }


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
