package com.project.scheduleapp.demo.Service;

import com.project.scheduleapp.demo.Model.Personal;
import com.project.scheduleapp.demo.Repository.IPersonalCrud;
import com.project.scheduleapp.demo.models.PersonalOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {
    @Autowired
    private IPersonalCrud crud;

    public List<Personal> getAllPersonal(ScheduleEntryService scheduleEntryService){ return crud.getAllPersonal(scheduleEntryService);};
    public Personal getPersonalById(Integer Id){
        return crud.getPersonalById(Id);
    }
    public Personal verifyLoginIn(String Username, String Password){return crud.verifyLogIn(Username,Password);}
    public Personal addEntry(Personal personal){
        return crud.addEntry(personal);
    }
    public Personal updatePersonal(Personal personal){
        return crud.updatePersonal(personal);
    }

    
}
