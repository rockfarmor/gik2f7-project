package com.project.scheduleapp.demo.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TidShift {


    private List<TidShiftEntry> tseList;

    public TidShift(List<Shift> shifts) {
        tseList = new ArrayList<>();

        LocalDateTime curMonth = null;
        TidShiftEntry tse = null;

        Calendar c = Calendar.getInstance();

        for (Shift s : shifts) {
            if(curMonth == null){
                curMonth = s.getStartDate().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
                tse = new TidShiftEntry(curMonth);
                tseList.add(tse);
            }

            if(s.getStartDate().getMonthValue() != curMonth.getMonthValue()){
                curMonth = s.getStartDate().withDayOfMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
                tse = new TidShiftEntry(curMonth);
                tseList.add(tse);
            }

            tse.getShifts().add(s);



        }

        for (TidShiftEntry ts: tseList) {

            System.out.println("____ " + ts.getMonth());
            for (Shift s : ts.getShifts()) {
                System.out.println(s.getStartDate());
            }

        }

    }

    public List<TidShiftEntry> getTseList() {
        return tseList;
    }
}
