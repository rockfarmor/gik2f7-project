package com.project.scheduleapp.demo.controllers;

import com.project.scheduleapp.demo.Model.*;

import com.project.scheduleapp.demo.Service.CategoryService;
import com.project.scheduleapp.demo.Service.PersonalService;
import com.project.scheduleapp.demo.Service.ScheduleEntryService;

import com.project.scheduleapp.demo.helpers.Helper;
import com.project.scheduleapp.demo.helpers.SebbeDate;
import com.project.scheduleapp.demo.helpers.TidShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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

            Personal p = personalService.verifyLoginIn(email,password,scheduleEntryService, categoryService);
            if(p == null){
                model.addAttribute("invalidCredentials",true);
                return "logIn";
            }
            if(p.getPassword().equals(password) && p.getUserName().equals(email)){
                session.setAttribute("personal",p);
                List<Personal> personals = personalService.getAllPersonal(scheduleEntryService, categoryService);
                session.setAttribute("allPersonal", personals);
                List<Shift> shifts = null;
                session.setAttribute("allShifts", shifts);
                session.setAttribute("allCategories", categoryService.getAllCategory());
                session.setAttribute("allShifts", personalService.getAllShiftsEntries(categoryService,scheduleEntryService));
                //List<Shift> shifts

            }
        }

        //Home view attributer härifrån:
        Personal loggedin = (Personal)session.getAttribute("personal");
        model.addAttribute("personal", loggedin);
        model.addAttribute("entries", loggedin);
        model.addAttribute("shifts",loggedin.getListAfterDate());
        return "home";
    }

    @RequestMapping(value = "/tid")
    public String time(Model model, HttpSession session) {
        if(session.getAttribute("personal") == null) {
            return "logIn";
        }

        Personal loggedin = (Personal)session.getAttribute("personal");
        model.addAttribute("personal", loggedin);
        model.addAttribute("entries", loggedin);
        TidShift ts = new TidShift(loggedin.getListBeforeDate());
        model.addAttribute("ts", ts);

        return "tid";
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

        if(session.getAttribute("allShifts") == null){
            session.setAttribute("allShifts", personalService.getAllShiftsEntries(categoryService,scheduleEntryService));
        }



        boolean showMessage = false;
        List<Personal> personals = (List<Personal>)session.getAttribute("allPersonal");

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

                    Category c = categoryService.getCategoryById(Integer.parseInt(allFormRequest.get("skifttyp")));


                    Shift s = new Shift(-1,startTime, endTime, allFormRequest.get("beskrivning"), p, c);
                    //NU lägger vi till skiten
                    Shift ss = scheduleEntryService.addShiftEntry(s, p);

                    //Personal pp = personalService.verifyLoginIn(p.getUserName(),p.getPassword(),scheduleEntryService, categoryService);

                    model.addAttribute("addedName", p.getName());
                    model.addAttribute("addedShift", s.getCategory().getCategoryName());
                    model.addAttribute("addedStart", s.getStartDate().toString());
                    model.addAttribute("addedEnd", s.getEndDate().toString());
                    model.addAttribute("addedDesc", s.getDescription());

                    //update sessions, both personal and all personal
                    Personal loggedin = (Personal)session.getAttribute("personal");
                    loggedin =  personalService.verifyLoginIn(loggedin.getUserName(), loggedin.getPassword(),scheduleEntryService, categoryService);
                    session.setAttribute("personal", loggedin);

                    List<Personal> ps = personalService.getAllPersonal(scheduleEntryService, categoryService);

                    session.setAttribute("allShifts", personalService.getAllShiftsEntries(categoryService,scheduleEntryService));
                    session.setAttribute("allPersonal", ps);


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
                    //Update session with all new personals
                    List<Personal> ps = personalService.getAllPersonal(scheduleEntryService, categoryService);
                    session.setAttribute("allPersonal", ps);



                } else {
                    //Some error
                }

            }  else if(allFormRequest.get("formType").equals("skiftTypeAdd")){

                if(allFormRequest.get("skiftTyp") != null && allFormRequest.get("skiftBeskrivning") != null) {
                    String skiftTyp = allFormRequest.get("skiftTyp");
                    String skiftBeskrivning = allFormRequest.get("skiftBeskrivning");
                    System.out.println("jaaa");

                    if (!skiftTyp.isBlank() && !skiftBeskrivning.isBlank()) {
                        Category category = new Category(-1,skiftTyp,skiftBeskrivning);

                        categoryService.addCategory(category);
                        session.setAttribute("allCategories", categoryService.getAllCategory());
                    }
                }

            } else if(allFormRequest.get("formType").equals("deleteSkift")){
                System.out.println(allFormRequest);
                if(!allFormRequest.get("formId").isBlank()){
                    int formid = Integer.parseInt(allFormRequest.get("formId"));
                    scheduleEntryService.deleteShift(formid);
                    Personal loggedin = (Personal)session.getAttribute("personal");
                    loggedin =  personalService.verifyLoginIn(loggedin.getUserName(), loggedin.getPassword(),scheduleEntryService, categoryService);
                    session.setAttribute("personal", loggedin);

                    List<Personal> ps = personalService.getAllPersonal(scheduleEntryService, categoryService);
                    session.setAttribute("allPersonal", ps);
                    session.setAttribute("allShifts", personalService.getAllShiftsEntries(categoryService,scheduleEntryService));


                }
            }
        }


        personals = (List<Personal>)session.getAttribute("allPersonal");

        model.addAttribute("allPersonal", personals);
        model.addAttribute("show_message", showMessage);
        model.addAttribute("categorys", (List<Category>)session.getAttribute("allCategories"));
        List<Shift> shifts = (List<Shift>)session.getAttribute("allShifts");
        Collections.sort(shifts, (x, y) -> x.getStartDate().compareTo(y.getStartDate()));
        model.addAttribute("shifts", shifts);

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

        List<Personal> personals = (List<Personal>)session.getAttribute("allPersonal");
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



}



