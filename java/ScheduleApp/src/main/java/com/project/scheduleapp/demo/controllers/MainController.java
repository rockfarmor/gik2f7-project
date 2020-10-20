package com.project.scheduleapp.demo.controllers;

import com.project.scheduleapp.demo.Model.Account;
import com.project.scheduleapp.demo.Model.SebbeDate;
import com.project.scheduleapp.demo.Model.Shift;
import com.project.scheduleapp.demo.Model.User;
import com.project.scheduleapp.demo.Service.test;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
    public String showSchedule(HttpSession session) {
        if(session.getAttribute("user") == null) {

            return "logIn";

        }
        SebbeDate sdate = new SebbeDate();
        sdate.dagar(2020,10);
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



