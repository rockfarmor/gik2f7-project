package com.project.scheduleapp.demo.models;

import java.sql.Timestamp;
import java.util.Date;

public class ScheduleEntry {

    private Integer Entry_Id;
    private Integer Schedule_Id;
    private Integer Category_Id;
    private Timestamp Start_Date;
    private Timestamp End_Date;
    private String Description;

    public Integer getEntry_Id() {
        return Entry_Id;
    }

    public void setEntry_Id(Integer entry_Id) {
        Entry_Id = entry_Id;
    }

    public Integer getSchedule_Id() {
        return Schedule_Id;
    }

    public void setSchedule_Id(Integer schedule_Id) {
        Schedule_Id = schedule_Id;
    }

    public Integer getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(Integer category_Id) {
        Category_Id = category_Id;
    }

    public Timestamp getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(Timestamp start_Date) {
        Start_Date = start_Date;
    }

    public Timestamp getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(Timestamp end_Date) {
        End_Date = end_Date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
