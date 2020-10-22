package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.Model.Personal;
import com.project.scheduleapp.demo.Service.ScheduleEntryService;
import com.project.scheduleapp.demo.models.PersonalOld;

import java.util.List;


public interface IPersonalCrud {

    public List<Personal> getAllPersonal(ScheduleEntryService scheduleEntryService);
    public Personal getPersonalById(Integer Id);
    public Personal addEntry(Personal Personal);
    public Personal updatePersonal(Personal Personal);
    public Personal verifyLogIn(String username, String Password);
}
