package com.project.scheduleapp.demo.Model;

import java.time.LocalDateTime;
import java.util.Date;

public class Shift {
    private int shiftID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;


    public Shift(int shiftID, LocalDateTime startDate, LocalDateTime endDate, String description) {
        this.shiftID = shiftID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
