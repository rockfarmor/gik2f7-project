package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.Model.Personalsss;
import com.project.scheduleapp.demo.Service.ScheduleEntryService;
import com.project.scheduleapp.demo.models.Personal;
import com.project.scheduleapp.demo.models.ScheduleEntry;

import java.util.List;


public interface IPersonalCrud {

    public List<Personalsss> getAllPersonal(ScheduleEntryService scheduleEntryService);
    public Personal getPersonalById(Integer Id);
    public Personal addEntry(Personal Personal);
    public Personal updatePersonal(Personal Personal);
    public Personal verifyLogIn(String username, String Password);
}
