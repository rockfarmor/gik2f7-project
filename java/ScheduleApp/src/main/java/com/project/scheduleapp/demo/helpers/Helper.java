package com.project.scheduleapp.demo.helpers;

import com.project.scheduleapp.demo.Model.Personal;
import com.project.scheduleapp.demo.Model.Shift;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static String getJavaScript(ArrayList<Personal> accs, Personal you){
        HashMap<String, ArrayList<Shift>> shiftMap = new HashMap<>();
        String jscript = "";
        for (Personal a: accs) {
            for (Shift shift: a.getSchedlist()) {
                String d = shift.getDayString();
                if(!shiftMap.containsKey(d))
                    shiftMap.put(d, new ArrayList());
                shiftMap.get(d).add(shift);
            }
        }

        jscript = "{";

        for(String key : shiftMap.keySet()){
            jscript += "'" + key + "': [";
            for (Shift s:shiftMap.get(key)){
                jscript += "new Shift('" + s.getAccount().getName() + "',";
                jscript += "'" + s.getStartDate().getHour() +":" + s.getStartDate().getMinute() + "',";
                jscript += "'" + s.getEndDate().getHour() +":" + s.getEndDate().getMinute() + "',";
                jscript += "'" + s.getStartDate().getDayOfMonth() + "',";
                jscript += "'" + "Lör" + "',";
                if(s.getAccount().equals(you)) {
                    jscript += "" + "true" + ",";

                } else {
                    jscript += "" + "false" + ",";
                }
                jscript += "'" + "Middag" + "',";

                jscript += "),";
            }
            jscript += "],";

        }
        jscript += "}";

        return jscript;
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

        System.out.println(day);
        System.out.println(day.getDayOfWeek().toString());
        System.out.println("awdadawda");
        return dagar.get(day.getDayOfWeek().toString());
    }





}
