package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.models.ScheduleEntry;

import java.util.List;

public interface IScheduleEntryCrud {

    public List<ScheduleEntry> getAllEntries();
    public List<ScheduleEntry> getEntriesByScheduleId(int scheduleId);
    public ScheduleEntry addEntry(ScheduleEntry scheduleEntry);
    public ScheduleEntry updateEntry(ScheduleEntry scheduleEntry);


}
