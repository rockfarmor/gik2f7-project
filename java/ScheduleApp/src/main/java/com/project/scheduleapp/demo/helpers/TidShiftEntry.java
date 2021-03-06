package com.project.scheduleapp.demo.helpers;

import com.project.scheduleapp.demo.Model.Shift;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TidShiftEntry {

    private LocalDateTime month;
    private List<Shift> shifts;


    public TidShiftEntry(LocalDateTime month) {
        this.month = month;
        this.shifts = new ArrayList<>();
    }


    public String getMonthString(){
        String mm = this.month.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv","SE"));
        mm = StringUtils.capitalize(mm);
        mm = mm + " " + this.month.getYear();

        return mm;
    }

    public String getPayString(){
        String res;
        int pay = 0;
        int totalHour = 0;
        for (Shift s : this.shifts) {
            int perHour = s.getAccount().getSalaryPerHour();
            pay += perHour * s.getTotalHour();
            totalHour += s.getTotalHour();
        }
        return "Timmar: " + totalHour + "h, Lön: " + pay + "kr";
    }


    public LocalDateTime getMonth() {
        return month;
    }

    public List<Shift> getShifts() {
        return shifts;
    }
}
