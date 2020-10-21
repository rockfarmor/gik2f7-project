package com.project.scheduleapp.demo.controllers;

import com.project.scheduleapp.demo.models.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class PersonalController {
    @Autowired
    private PersonalService personalService;

    @GetMapping(path = "/personal")
    public String getAllPersonal (Model model){
        model.addAttribute("personal",personalService.getAllPersonal());
        return "/person"; //Måste ändras till rätt pathe
    }
}
