package com.project.scheduleapp.demo.controllers;

import com.project.scheduleapp.demo.Model.*;

import com.project.scheduleapp.demo.Service.PersonalService;
import com.project.scheduleapp.demo.Service.ScheduleEntryService;

import com.project.scheduleapp.demo.helpers.Helper;
import com.project.scheduleapp.demo.models.PersonalOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
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

    @Autowired
    private ScheduleEntryService scheduleEntryService;
    @Autowired
    private PersonalService personalService;





    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showlogIn() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String verifyLogin(@RequestParam String email,@RequestParam String password,HttpSession session,Model model) {
        for(int c=0;c<userlist.length;c++){
            Personal p;
            p=personalService.verifyLoginIn(email,password);
            if(p.getPassword().equals(password) && p.getUserName().equals(email)){
                session.setAttribute("personal",p);
                if(p.getIsAdmin() == 1){
                    return "admin";
                }else{
                    model.addAttribute("personal",p);
                    //System.out.println(a);
                    model.addAttribute("entries",scheduleEntryService.getAllEntries());
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

    @RequestMapping(value = "/admin")
    public String adminView(Model model, HttpSession session, @RequestParam Map<String, String> allFormRequest) {
        if(session.getAttribute("personal") == null) {
            return "logIn";
        }

        List<Personal> personals = personalService.getAllPersonal(scheduleEntryService);

        model.addAttribute("allPersonal", personals);

        if(allFormRequest.get("formType") != null) {
            if (allFormRequest.get("formType").equals("skiftAdd")) {
                System.out.println("SkiftAdd");

                System.out.println(allFormRequest);

                //{formType=skiftAdd, employee=1, skifttyp=3, date=2020-10-20, startTid=12:00:00, slutTid=20:00:00, beskrivning=}

                String stime = allFormRequest.get("date") + " " + allFormRequest.get("startTid");
                String etime = allFormRequest.get("date") + " " + allFormRequest.get("slutTid");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startTime = LocalDateTime.parse(stime, formatter);
                LocalDateTime endTime = LocalDateTime.parse(etime, formatter);

                int personalId = Integer.parseInt(allFormRequest.get("employee"));
                Personal p = null;

                for (Personal p_: personals) {
                    if(p_.getUniqueID() == personalId){
                        p = p_;
                        break;
                    }
                }
                if(p != null){
                    System.out.println(startTime);
                    System.out.println(endTime);

                    Shift s = new Shift(-1,startTime, endTime, allFormRequest.get("beskrivning"), p);
                    //NU lägger vi till skiten
                    scheduleEntryService.addShiftEntry(s, p);

                }



            }
        }





        return "admin";
    }




    @RequestMapping(value = "/schema", method = RequestMethod.GET)
    public String showSchedule(Model model, HttpSession session, @RequestParam(defaultValue = "-1") String gYear, @RequestParam(defaultValue = "-1") String gMonth) {
        if(session.getAttribute("personal") == null) {

            return "logIn";

        }

        int y = Integer.parseInt(gYear);
        int m = Integer.parseInt(gMonth);
        LocalDateTime now = LocalDateTime.now();
        if(!(y > 1995 && m >= 0 && m <= 12)) {
            y = now.getYear();
            m = now.getMonthValue();
        }

        LocalDateTime thisMonth =LocalDateTime.of(y, m, 5, 12, 0);
        LocalDateTime prevMonth =LocalDateTime.of(y, m, 5, 12, 0).minusMonths(1);
        LocalDateTime nextMonth =LocalDateTime.of(y, m, 5, 12, 0).plusMonths(1);;

        String nextUrl = "/schema?gYear="+nextMonth.getYear()+"&gMonth="+nextMonth.getMonthValue();
        String prevUrl = "/schema?gYear="+prevMonth.getYear()+"&gMonth="+prevMonth.getMonthValue();

        model.addAttribute("prevUrl", prevUrl);
        model.addAttribute("nextUrl", nextUrl);

        ArrayList<Personal> personals = (ArrayList<Personal>) personalService.getAllPersonal(scheduleEntryService);

        for (Personal p: personals){

            for (Shift s:p.getSchedlist()) {
                System.out.println(p.getName() + "-> " + s.getStartDate());
            }

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


        String jscript = Helper.getJavaScript(personals, personals.get(0));

        model.addAttribute("jScript", jscript);
        

        model.addAttribute("calendar_month", thisMonth.getMonth().toString() + " " + thisMonth.getYear());
        model.addAttribute("month_int", m);


        SebbeDate sdate = new SebbeDate();
        sdate.dagar(y,m);

        int rows = (int)sdate.getRowsthismonth();
        int cols = 7;

        Shift[][] calShift = new Shift[rows][];

        int l = 0;
        for (int i = 0; i < rows; i++) {
            calShift[i] = new Shift[cols];
            for (int j = 0; j < cols; j++) {
                LocalDateTime t  = LocalDateTime.of(y, m, 1, 0, 0).minusDays(sdate.getDaystolastmonth()).plusDays(l);
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
    public String showHome(HttpSession session, Model model) {
        if(session.getAttribute("personal") == null) {
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
        Timestamp a;

        //List<ScheduleEntry> scheduleEntries = scheduleEntryService.getAllEntries();
        List<Personal> personals = personalService.getAllPersonal(scheduleEntryService);

        for (Personal p: personals){

            for (Shift s:p.getSchedlist()) {
                System.out.println(p.getName() + "-> " + s.getStartDate());
            }



        }




        System.out.println(personals);
        //System.out.println(scheduleEntries);


        a= scheduleEntryService.getAllEntries().get(0).getStart_Date();


        Date date = new Date(a.getTime());

        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);
        System.out.println(calendar.get(calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(calendar.MINUTE));
        

       // System.out.println(scheduleEntryService.getAllEntries().get(0).getStart_Date());
        model.addAttribute("account",a1);
        //System.out.println(a);

        //model.addAttribute("personal",personalService.getPersonalById(1));
        model.addAttribute("entries",scheduleEntryService.getAllEntries());

        return "home";
    }
}



