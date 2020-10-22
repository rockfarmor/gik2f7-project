package com.project.scheduleapp.demo.controllers;

import com.project.scheduleapp.demo.Service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ScheduleEntryController {

    @Autowired
    private PersonalService personalService;

    @GetMapping(path = "/hej")
    public String getAllEntries(Model model){
        model.addAttribute("personal",personalService.getPersonalById(1));
        return "testview";//måste ändras till rätt view
    }

}
