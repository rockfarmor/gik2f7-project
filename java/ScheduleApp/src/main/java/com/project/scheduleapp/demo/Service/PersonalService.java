package com.project.scheduleapp.demo.Service;

import com.project.scheduleapp.demo.Model.Personal;
import com.project.scheduleapp.demo.Model.Shift;
import com.project.scheduleapp.demo.Repository.IPersonalCrud;
import com.project.scheduleapp.demo.models.PersonalOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {
    @Autowired
    private IPersonalCrud crud;

    public List<Personal> getAllPersonal(ScheduleEntryService scheduleEntryService, CategoryService categoryService){
        return crud.getAllPersonal(scheduleEntryService,categoryService);
    };
    public Personal getPersonalById(Integer Id,ScheduleEntryService scheduleEntryService, CategoryService categoryService){
        return crud.getPersonalById(Id,scheduleEntryService, categoryService);
    }
    public Personal verifyLoginIn(String Username, String Password,ScheduleEntryService scheduleEntryService, CategoryService categoryService){
        return crud.verifyLogIn(Username,Password,scheduleEntryService, categoryService);
    }
    public Personal addEntry(Personal personal){
        return crud.addEntry(personal);
    }
    public Personal updatePersonal(Personal personal){

        return crud.updatePersonal(personal);
    }
    public List<Shift> getAllShiftsEntries(CategoryService categoryService, ScheduleEntryService scheduleEntryService){return crud.getAllShiftsEntries(categoryService,scheduleEntryService);}


    
}
