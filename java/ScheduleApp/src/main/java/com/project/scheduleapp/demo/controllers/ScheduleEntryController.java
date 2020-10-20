package com.project.scheduleapp.demo.controllers;

import com.project.scheduleapp.demo.Service.ScheduleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule/entry")
public class ScheduleEntryController {

    @Autowired
    private ScheduleEntryService scheduleEntryService;

    @GetMapping(path = "/entries")
    public String getAllEntries(Model model){
        model.addAttribute("entries", scheduleEntryService.getAllEntries());
        return "entriesview";//måste ändras till rätt view
    }

}
