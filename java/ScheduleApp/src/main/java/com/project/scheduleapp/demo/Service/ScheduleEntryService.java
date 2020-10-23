package com.project.scheduleapp.demo.Service;

import com.project.scheduleapp.demo.Model.Personal;
import com.project.scheduleapp.demo.Model.Shift;
import com.project.scheduleapp.demo.Repository.IScheduleEntryCrud;
import com.project.scheduleapp.demo.models.ScheduleEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleEntryService {

    @Autowired
    private IScheduleEntryCrud crud;

    public List<ScheduleEntry> getEntriesByScheduleId(int scheduleId){ return crud.getEntriesByScheduleId(scheduleId);}
    public Shift addShiftEntry(Shift shift, Personal personal){ return crud.addShiftEntry(shift, personal);}

    public List<ScheduleEntry> getAllEntries(){
        return crud.getAllEntries();
    }
    public ScheduleEntry addEntry(ScheduleEntry scheduleEntry){
        return crud.addEntry(scheduleEntry);
    }
    public ScheduleEntry updateEntry(ScheduleEntry scheduleEntry){
        return crud.updateEntry(scheduleEntry);
    }

    public Shift deleteShift(Integer Id){return crud.deleteShift(Id);}

}
