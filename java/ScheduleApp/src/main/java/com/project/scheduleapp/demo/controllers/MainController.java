package com.project.scheduleapp.demo.controllers;

import com.project.scheduleapp.demo.Model.Account;
import com.project.scheduleapp.demo.Model.SebbeDate;
import com.project.scheduleapp.demo.Model.Shift;
import com.project.scheduleapp.demo.Model.User;
import com.project.scheduleapp.demo.Repository.ScheduleEntryCrud;
import com.project.scheduleapp.demo.Service.test;
import com.project.scheduleapp.demo.models.ScheduleEntry;
import org.apache.tomcat.jni.Local;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import java.util.*;


@Controller
public class MainController {
    User u1 = new User("admin@admin.se","admin",1);
    User u2 = new User("sebbe@sebbe.se","123",0);
    User [] userlist ={u1,u2};
    Account a1=new Account(1,"sebbe","seb123","1234",150);
    Account a2=new Account(2,"johan","johan123","qwert",200);
    LocalDateTime a = LocalDateTime.of(2020, 10, 20, 15, 00);
    LocalDateTime b = LocalDateTime.of(2020, 10, 20, 22, 00);
    LocalDateTime c = LocalDateTime.of(2020, 10, 21, 15, 00);
    LocalDateTime d = LocalDateTime.of(2020, 10, 21, 23, 30);
    Shift s1 = new Shift(1,a,b,"Woking");
    Shift s2 = new Shift(2,c,d,"Running");







    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showlogIn() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String verifyLogin(@RequestParam String email,@RequestParam String password,HttpSession session,Model model) {
        for(int c=0;c<userlist.length;c++){
            if(email.equals(userlist[c].getUserID()) && password.equals(userlist[c].getPassword())){
                session.setAttribute("user",userlist[c]);
                if(userlist[c].getAdmin() == 1){
                    return "admin";
                }else{
                    return "home";
                }
            }
        }
        model.addAttribute("invalidCredentials",true);
        return "logIn";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(HttpSession session) {
        session.invalidate();
        return "logIn";
    }

    @RequestMapping(value = "/schema", method = RequestMethod.GET)
    public String showSchedule(Model model, HttpSession session) {
        if(session.getAttribute("user") == null) {

            return "logIn";

        }



        Account h1 = new Account(100,"Johan Nilsson","johni198","asd",200);
        Account h2 = new Account(100,"Sebbe Nilsson","johni198","asd",200);
        Account h3 = new Account(100,"Axel Axelsson","jasd","asd",200);

        ArrayList<Account> accs = new ArrayList<>();
        accs.add(h1);
        accs.add(h2);
        accs.add(h3);


        h1.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 1, 12, 0), LocalDateTime.of(2020, 10, 1, 17, 0), "FUCK YA",h1));
        h1.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 2, 12, 0), LocalDateTime.of(2020, 10, 2, 17, 0), "FUCK YA",h1));
        h1.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 5, 12, 0), LocalDateTime.of(2020, 10, 5, 17, 0), "FUCK YA",h1));
        h1.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 10, 12, 0), LocalDateTime.of(2020, 10, 10, 20, 0), "FUCK YA",h1));
        h1.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 19, 12, 0), LocalDateTime.of(2020, 10, 19, 17, 0), "FUCK YA",h1));
        h1.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 25, 12, 0), LocalDateTime.of(2020, 10, 25, 17, 0), "FUCK YA",h1));

        h2.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 1, 12, 0), LocalDateTime.of(2020, 10, 1, 16, 0), "FUCK YA",h2));
        h2.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 2, 15, 0), LocalDateTime.of(2020, 10, 2, 23, 0), "FUCK YA",h2));
        h2.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 5, 14, 0), LocalDateTime.of(2020, 10, 5, 22, 0), "FUCK YA",h2));
        h2.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 10, 14, 0), LocalDateTime.of(2020, 10, 10, 23, 0), "FUCK YA",h2));
        h2.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 19, 14, 0), LocalDateTime.of(2020, 10, 20, 01, 0), "FUCK YA",h2));
        h2.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 25, 12, 0), LocalDateTime.of(2020, 10, 25, 17, 0), "FUCK YA",h2));


        h3.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 10, 12, 0), LocalDateTime.of(2020, 10, 10, 22, 0), "FUCK YA",h3));
        h3.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 11, 12, 0), LocalDateTime.of(2020, 10, 11, 23, 0), "FUCK YA",h3));
        h3.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 12, 15, 0), LocalDateTime.of(2020, 10, 12, 22, 0), "FUCK YA",h3));
        h3.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 13, 13, 0), LocalDateTime.of(2020, 10, 13, 23, 0), "FUCK YA",h3));
        h3.getSchedlist().add(new Shift(1, LocalDateTime.of(2020, 10, 14, 15, 0), LocalDateTime.of(2020, 10, 14, 21, 0), "FUCK YA",h3));


        HashMap<String, ArrayList<Shift>> shiftMap = new HashMap<>();
        String jscript = "";
        for (Account a: accs) {

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
                if(s.getAccount().equals(h1)) {
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

        System.out.println(jscript);

        /*
            data = {"2020-10-10": [new Shift("Johan Nilsson", "18:00", "20:00", 10, "Lör", true, "Middag"), new Shift("Sebbe", "12:00", "20:00", 10, "Lör", false, "Lunch/Middag")], "2020-10-20":[new Shift("Sebbe", "15:00", "20:00", 20, "Lör", false, "Middag")] }

         */
        model.addAttribute("jScript", jscript);






        model.addAttribute("calendar_month", "Oktober");

        model.addAttribute("month_int", 10);

        model.addAttribute("s1", h1);

        SebbeDate sdate = new SebbeDate();
        sdate.dagar(2020,10);

        LocalDateTime startDate = LocalDateTime.of(2020, 10, 1, 0, 0).minusDays(sdate.getDaystolastmonth());
        LocalDateTime endDate = LocalDateTime.of(2020, 10, sdate.getDaysthismonth(), 0, 0).plusDays(sdate.getDaystonextmonth());

        int rows = (int)sdate.getRowsthismonth();
        int cols = 7;

        LocalDateTime[][] cal = new LocalDateTime[rows][];
        Shift[][] calShift = new Shift[rows][];

        int l = 0;
        for (int i = 0; i < rows; i++) {
            cal[i] = new LocalDateTime[cols];
            for (int j = 0; j < cols; j++) {
                cal[i][j] = LocalDateTime.of(2020, 10, 1, 0, 0).minusDays(sdate.getDaystolastmonth()).plusDays(l);
                l++;
            }
        }

        l = 0;
        for (int i = 0; i < rows; i++) {
            calShift[i] = new Shift[cols];
            for (int j = 0; j < cols; j++) {
                //cal[i][j] = LocalDateTime.of(2020, 10, 1, 0, 0).minusDays(sdate.getDaystolastmonth()).plusDays(l);
                LocalDateTime t  = LocalDateTime.of(2020, 10, 1, 0, 0).minusDays(sdate.getDaystolastmonth()).plusDays(l);
                Shift s = new Shift(-1, t,t,"noshift");
                s.setReal(false);
                for (Shift st : h1.getSchedlist()) {
                    if(st.sameDay(t)) {
                        s = st;
                        break;
                    }
                }
                calShift[i][j] = s;
                l++;
            }
        }


        model.addAttribute("calShift", calShift);


        return "schema";
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(HttpSession session,Model model) {
        if(session.getAttribute("user") == null) {
            return "logIn";

        }
        Map<String,String> dagar = new HashMap<>();
        dagar.put("MONDAY","Mån");
        dagar.put("TUESDAY","Tis");
        dagar.put("WEDNESDAY","Ons");
        dagar.put("THRUSDAY","Tor");
        dagar.put("FRIDAY","Fri");
        dagar.put("SATURDAY","Lör");
        dagar.put("SUNDAY","Sön");

        a1.getSchedlist().add(s1);
        a1.getSchedlist().add(s2);
        model.addAttribute("schedlist", a1.getSchedlist());
        model.addAttribute("account",a1);
        model.addAttribute("dagar",dagar);

        return "home";
    }
}



