package com.project.scheduleapp.demo.helpers;

import com.project.scheduleapp.demo.Model.Personal;
import com.project.scheduleapp.demo.Model.Shift;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Helper {

    public static String getJavaScript(ArrayList<Personal> accs, Personal you){
        HashMap<String, ArrayList<Shift>> shiftMap = new HashMap<>();
        HashMap<String, String> dateMap = new HashMap<>();
        String jscript = "";
        for (Personal a: accs) {
            for (Shift shift: a.getSchedlist()) {
                String d = shift.getDayString();
                if(!shiftMap.containsKey(d)) {
                    shiftMap.put(d, new ArrayList());
                    dateMap.put(d, shift.getLocalDateString());
                }
                shiftMap.get(d).add(shift);
            }
        }

        jscript = "{";

        for(String key : shiftMap.keySet()){
            jscript += "'" + key + "': [";
            for (Shift s:shiftMap.get(key)){
                jscript += "new Shift('" + s.getAccount().getName() + "',";
                jscript += "'" + s.getStartHourMinute() + "',";
                jscript += "'" + s.getEndHourMinute() + "',";
                jscript += "'" + s.getStartDate().getDayOfMonth() + "',";
                jscript += "'" + Helper.getSchedDay(s.getStartDate()) + "',";
                if(s.getAccount().getUniqueID()==you.getUniqueID()) {
                    jscript += "" + "true" + ",";

                } else {
                    jscript += "" + "false" + ",";
                }
                jscript += "'" + "Middag" + "',";
                jscript += "'" + "20 Augusti" + "',";

                jscript += "),";
            }
            jscript += "],";

        }
        jscript += "}";


        return jscript;
    }

    public static String getJavaScriptDateMap(Shift[][] calShift, int rows, int cols){
        HashMap<String, String> dateMap = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Shift s = calShift[i][j];
                dateMap.put(s.getDayString(), s.getLocalDateString());


            }
        }

        String jscript = "";

        jscript += "\n";
        jscript += "//asd";
        //Construct obj för convertions between key and

        jscript += "\n";
        jscript += "{";
        for(String key : dateMap.keySet()) {
            jscript += "'" + key + "':'" + dateMap.get(key) + "',";
        }
        jscript += "}";


        return jscript;
    }

    public static String getLocalDateYearString(LocalDateTime date){

        String month = date.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv","SE"));
        month = StringUtils.capitalize(month);
        month = month + " " + date.getYear();

        return month;
    }

    public static String getSchedDay(LocalDateTime day){
        Map<String,String> dagar = new HashMap<>();
        dagar.put("MONDAY","Mån");
        dagar.put("TUESDAY","Tis");
        dagar.put("WEDNESDAY","Ons");
        dagar.put("THURSDAY","Tor");
        dagar.put("FRIDAY","Fri");
        dagar.put("SATURDAY","Lör");
        dagar.put("SUNDAY","Sön");
        return dagar.get(day.getDayOfWeek().toString());
    }

    public static String getHourMinuteStr(LocalDateTime date){
        String res = String.format("%02d", date.getHour()) + ":" + String.format("%02d", date.getMinute());
        return res;
    }



}
