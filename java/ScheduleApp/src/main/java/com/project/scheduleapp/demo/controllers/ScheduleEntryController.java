package com.project.scheduleapp.demo.controllers;

import com.project.scheduleapp.demo.Repository.IPersonalCrud;
import com.project.scheduleapp.demo.Service.PersonalService;
import com.project.scheduleapp.demo.Service.ScheduleEntryService;
import com.project.scheduleapp.demo.models.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
