package com.project.scheduleapp.demo.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.GregorianCalendar;


public class SebbeDate {
    private int daystolastmonth;
    private int daystonextmonth;
    private int daysthismonth;
    private double rowsthismonth;




    public static String sayDayName(Date d) {
        DateFormat f = new SimpleDateFormat("EEEE");
        try {
            return f.format(d);
        }
        catch(Exception e) {
            e.printStackTrace();
            return "";
        }

    }
    public String daya(int year,int month,int day){
        Date date1 =
                (new GregorianCalendar
                        (year, month, day)).getTime();


        return SebbeDate.sayDayName(date1);
    }


    public int getDay(int year,int month){
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        return daysInMonth;



    }

    public int getDaystolastmonth() {
        return daystolastmonth;
    }

    public void setDaystolastmonth(int daystolastmonth) {
        this.daystolastmonth = daystolastmonth;
    }

    public int getDaystonextmonth() {
        return daystonextmonth;
    }

    public void setDaystonextmonth(int daystonextmonth) {
        this.daystonextmonth = daystonextmonth;
    }

    public int getDaysthismonth() {
        return daysthismonth;
    }

    public void setDaysthismonth(int daysthismonth) {
        this.daysthismonth = daysthismonth;
    }

    public double getRowsthismonth() {
        return rowsthismonth;
    }

    public void setRowsthismonth(double rowsthismonth) {
        this.rowsthismonth = rowsthismonth;
    }

    public void dagar(int year,int month){
        SebbeDate dat=new SebbeDate();
        String dagar [] = {"måndag","tisdag","onsdag","torsdag","fredag","lördag","söndag"};
        String dagensdag = dat.daya(year,month-1,1);
        String sistadag = dat.daya(year,month-1,getDay(year,month));

        this.daysthismonth=getDay(year,month);
        int goback=0;
        int goforward=0;
        int a = 0;
        for(int c=0;c<dagar.length;c++){
            if(dagensdag.equals(dagar[c])){

                goback = (6-(6-c));
                this.daystolastmonth=goback;

            }
        }
        for(int c=0;c<dagar.length;c++){
            if(sistadag.equals(dagar[c])){
                goforward=c;
                a=6-goforward;
                this.daystonextmonth=a;
                System.out.println("Sista dagen i månaden är "+ sistadag);
                System.out.println("Antal dagar som går in på nästa månad är " + a);
                System.out.println("I månad " + month + "ska sista dagen vara "+ dagar[goforward+a]);
            }
        }
        double antalrader=0;
        this.rowsthismonth= (getDay(year,month)+goback+a)/7;





    }
}


