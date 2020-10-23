package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.Model.Personal;
import com.project.scheduleapp.demo.Service.CategoryService;
import com.project.scheduleapp.demo.Service.ScheduleEntryService;
import com.project.scheduleapp.demo.models.PersonalOld;

import java.util.List;


public interface IPersonalCrud {

    public List<Personal> getAllPersonal(ScheduleEntryService scheduleEntryService, CategoryService categoryService);
    public Personal getPersonalById(Integer Id,ScheduleEntryService scheduleEntryService, CategoryService categoryService);
    public Personal addEntry(Personal personal);
    public Personal updatePersonal(Personal personal);
    public Personal verifyLogIn(String username, String Password,ScheduleEntryService scheduleEntryService, CategoryService categoryService);
}
