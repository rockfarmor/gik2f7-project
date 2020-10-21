package com.project.scheduleapp.demo.helpers;

import com.project.scheduleapp.demo.Model.Account;
import com.project.scheduleapp.demo.Model.Personalsss;
import com.project.scheduleapp.demo.Model.Shift;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper {

    public static String getJavaScript(ArrayList<Personalsss> accs, Personalsss you){
        HashMap<String, ArrayList<Shift>> shiftMap = new HashMap<>();
        String jscript = "";
        for (Personalsss a: accs) {
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





}
