package com.project.scheduleapp.demo.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String logIn() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String verifyLogin() {
        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        return "login";
    }

    @RequestMapping(value = "/schema", method = RequestMethod.GET)
    public String showSchedule() {
        return "schema";
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome() {
        return "home";
    }
}



