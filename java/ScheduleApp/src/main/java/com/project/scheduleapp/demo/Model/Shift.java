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


    /**
     * Method that generates a date-string from this.startDate
     * Format: 2020-05-20
     * @return Formatted Date-string
     */

    public String getDayString(){
        return startDate.getYear() + "-" + startDate.getMonthValue() + "-" + startDate.getDayOfMonth();
    }

    /**
     * Method that compares a LocalDateTime obj with this.startDate if it's on the same day. (Can be different time)
     * @param day LocalDateTime obj to be compared
     * @return True if this.startDay and day is on the same day
     */
    public boolean sameDay(LocalDateTime day) {
        return day.getYear() == startDate.getYear() && day.getMonthValue() == startDate.getMonthValue() && day.getDayOfMonth() == startDate.getDayOfMonth();
    }

    /**
     * Returns duration between this.startDate and this.endDate in hours.
     * @return Returns duration between this.startDate and this.endDate in hours.
     */
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

    /**
     * Returns formatted String ("11h 44m") of duration between this.startDate and this.endDate
     * @return
     */
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

    /**
     * Compares this.startDate with input month if their month is same
     * @param month integer value month
     * @return
     */
    public boolean dateIsMonth(int month){
        return startDate.getDayOfMonth() == month;
    }

    /**
     * Method that converts a this.startDate to a 3-letter String of Swedish day name
     * @return
     */
    public String getSchedDay(){
        return Helper.getSchedDay(this.startDate);

    }


    /**
     * Method that formats this.startDate to String in Swedish. (Format example: 20 Januari)
     * @return
     */
    public String getLocalDateString(){
        String month = this.startDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv","SE"));
        month = StringUtils.capitalize(month);
        month = this.startDate.getDayOfMonth() + " " + month;

        return month;
    }

    /**
     * Method that formats this.startDate to a 3-letter Month string in Swedish. (Format example: Jan)
     * @return
     */
    public String getLocalDateMonthString(){
        String month = this.startDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv","SE"));
        month = StringUtils.capitalize(month);
        month = month.substring(0,3);

        return month;
    }


    /**
     * Method that formats this.startDate to String in Swedish. (Format example: Januari 2020)
     * @return
     */
    public String getLocalDateYearString(){
        String month = this.startDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv","SE"));
        month = StringUtils.capitalize(month);
        month = month + " " + this.startDate.getYear();

        return month;
    }

    /**
     * Method that formats string with this.startDate and this.endDate -> (Format example: 10:35 - 15:49)
     * @return
     */
    public String getStartEndHourMinute(){
        return getStartHourMinute() + " - " + getEndHourMinute();
    }

    /**
     * Method that formats this.startDate to string -> (Format example: 10:35)
     * @return
     */
    public String getStartHourMinute(){
        return Helper.getHourMinuteStr(this.startDate);
    }

    /**
     * Method that formats this.endDate to string -> (Format example: 10:35)
     * @return
     */
    public String getEndHourMinute(){
        return Helper.getHourMinuteStr(this.endDate);
    }


    public Category getCategory() {
        if(category == null){
            return Category.Category1;
        }
        return category;
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
