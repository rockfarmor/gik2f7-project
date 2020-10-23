package com.project.scheduleapp.demo.controllers;

import com.project.scheduleapp.demo.Model.*;

import com.project.scheduleapp.demo.Service.CategoryService;
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
import java.sql.SQLOutput;
import java.sql.Timestamp;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class MainController {

    @Autowired
    private ScheduleEntryService scheduleEntryService;
    @Autowired
    private PersonalService personalService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showlogIn() {
        return "logIn";
    }

    @RequestMapping(value = "/home")
    public String verifyLogin(Model model, HttpSession session, @RequestParam Map<String, String> allFormRequest) {
        if((allFormRequest.get("email") == null || allFormRequest.get("password") == null) && session.getAttribute("personal") == null){
            model.addAttribute("invalidCredentials",true);
            return "logIn";
        }
        String email = "";
        String password = "";

        if(!(allFormRequest.get("email") == null || allFormRequest.get("password") == null)) {
            email = allFormRequest.get("email");
            password = allFormRequest.get("password");

            Personal p = personalService.verifyLoginIn(email,password,scheduleEntryService);
            if(p == null){
                model.addAttribute("invalidCredentials",true);
                return "logIn";
            }
            if(p.getPassword().equals(password) && p.getUserName().equals(email)){
                session.setAttribute("personal",p);
            }
        }

        //Home view attributer härifrån:
        Personal loggedin = (Personal)session.getAttribute("personal");
        model.addAttribute("personal", loggedin);
        model.addAttribute("entries", loggedin);
        model.addAttribute("shifts",loggedin.getSchedlist());
        return "home";
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
        boolean showMessage = false;
        List<Personal> personals = personalService.getAllPersonal(scheduleEntryService);


        if(allFormRequest.get("formType") != null) {
            //skiftAdd,skiftTypeAdd,userAdd
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

                    showMessage = true;

                    Shift s = new Shift(-1,startTime, endTime, allFormRequest.get("beskrivning"), p);
                    //NU lägger vi till skiten
                    scheduleEntryService.addShiftEntry(s, p);
                    Personal pp = personalService.verifyLoginIn(p.getUserName(),p.getPassword(),scheduleEntryService);

                    model.addAttribute("addedName", pp.getName());
                    model.addAttribute("addedShift", s.getCategory().getCategoryName());
                    model.addAttribute("addedStart", s.getStartDate().toString());
                    model.addAttribute("addedEnd", s.getEndDate().toString());
                    model.addAttribute("addedDesc", s.getDescription());


                    if(pp == null){
                        return "logIn";
                    }
                    session.setAttribute("personal", pp);

                }


                //skiftAdd,skiftTypeAdd,userAdd
            } else if(allFormRequest.get("formType").equals("userAdd")){
                System.out.println("userAdd");
                System.out.println(allFormRequest);
                String username = allFormRequest.get("username");
                String name = allFormRequest.get("name");
                String password = allFormRequest.get("password");
                String salaryStr = allFormRequest.get("salary");
                int salary = 0;
                if(!salaryStr.isEmpty())
                    salary = Integer.parseInt(salaryStr);
                int iadmin = 0;
                if(allFormRequest.get("isadmin") != null)
                    iadmin = 1;

                if(!username.isEmpty() && !name.isEmpty() && !password.isEmpty() && salary > 0){
                    //int uniqueID, String name, String userName, String password, int salaryPerHour, int isAdmin
                    int sal = 10;
                    Personal newPerson = new Personal(-1, name, username, password, salary,iadmin);

                    personalService.addEntry(newPerson);
                    System.out.println("NY PERSSON LADES TILL!!!!!!!!!!1111111111");



                    System.out.println(newPerson);
                    personals = personalService.getAllPersonal(scheduleEntryService);
                } else {
                    System.out.println("PRINT ERROR!!!!!!!!");
                }

            }  else if(allFormRequest.get("formType").equals("skiftTypeAdd")){
                System.out.println("skiftTypeAdd");


                if(allFormRequest.get("skiftTyp") != null && allFormRequest.get("skiftBeskrivning") != null) {
                    String skiftTyp = allFormRequest.get("skiftTyp");
                    String skiftBeskrivning = allFormRequest.get("skiftBeskrivning");
                    System.out.println("jaaa");

                    if (!skiftTyp.isBlank() && !skiftBeskrivning.isBlank()) {
                        Category category = new Category(-1,skiftTyp,skiftBeskrivning);

                        categoryService.addCategory(category);
                        System.out.println("heeej");
                    }
                }



            }
        }



        model.addAttribute("allPersonal", personals);
        model.addAttribute("show_message", showMessage);
        model.addAttribute("categorys",categoryService.getAllCategory());


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
        Personal loggedin = (Personal)session.getAttribute("personal");
        String jscript = Helper.getJavaScript(personals, loggedin);

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
                Shift s = new Shift(-1, t, t,"noshift");
                s.setReal(false);
                for (Shift st : loggedin.getSchedlist()) {
                    if(st.sameDay(t)) {
                        s = st;
                        break;
                    }
                }
                calShift[i][j] = s;
                l++;
            }
        }

        String jscript2 = Helper.getJavaScriptDateMap(calShift, rows, cols);

        model.addAttribute("calShift", calShift);
        model.addAttribute("prevUrl", prevUrl);
        model.addAttribute("nextUrl", nextUrl);
        model.addAttribute("jScript", jscript);
        model.addAttribute("jScript2", jscript2);
        model.addAttribute("calendar_month", Helper.getLocalDateYearString(thisMonth));
        model.addAttribute("month_int", m);

        return "schema";
    }


    /*
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(HttpSession session, Model model) {
        if(session.getAttribute("personal") == null) {
            return "logIn";

        }
        Map<String,String> dagar = new HashMap<>();
        dagar.put("MONDAY","Mån");
        dagar.put("TUESDAY","Tis");
        dagar.put("WEDNESDAY","Ons");
        dagar.put("THURSDAY","Tor");
        dagar.put("FRIDAY","Fri");
        dagar.put("SATURDAY","Lör");
        dagar.put("SUNDAY","Sön");
        Timestamp a;


        Personal loggedin = (Personal)session.getAttribute("personal");
        //System.out.println(loggedin.getSchedlist().get(0).getDescription());
        //System.out.println(a);
        if(loggedin!= null) {
            model.addAttribute("dagar",dagar);
            model.addAttribute("entries", loggedin);
            model.addAttribute("shifts", loggedin.getSchedlist());
            //System.out.println(loggedin.getSchedlist().get(0).getStartDate().getDayOfWeek());

            //System.out.println(dagar["loggedin.getSchedlist().get(0).getStartDate().getDayOfWeek()"]);
            //System.out.println(dagar.get(loggedin.getSchedlist().get(0).getStartDate().getDayOfWeek().toString()));
            //  System.out.println(dagar.get(c));


            //<td th:text="${dagar.get('__${shifts.getStartDate().getDayOfWeek().toString()}__')}">
            return "home";
        }
        return "logIn";

    }
    */

}



