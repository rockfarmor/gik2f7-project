package com.project.scheduleapp.demo.Repository;


import com.project.scheduleapp.demo.Service.ScheduleEntryService;
import com.project.scheduleapp.demo.models.ScheduleEntry;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ScheduleEntryCrud implements IScheduleEntryCrud{

    private Connection con;
    @Override
    public List<ScheduleEntry> getAllEntries(){
        List<ScheduleEntry> scheduleEntries = new ArrayList<>();
        try{
            con = DriverManager.getConnection("jbdc:mysql://mysql13.loopia.se", "grupp7@a284338", "grupp7dalarna");
            Statement statement = con.createStatement();
            String sqlSelectAllEntries = "SELECT * FROM Shedule_Entry";
            ResultSet resultSet = statement.executeQuery(sqlSelectAllEntries);
            while (resultSet.next()){
                ScheduleEntry scheduleEntry = new ScheduleEntry();
                scheduleEntry.setEntry_Id(resultSet.getInt("Entry_Id"));
                scheduleEntry.setSchedule_Id(resultSet.getInt("Schedule_Id"));
                scheduleEntry.setCategory_Id(resultSet.getInt("Category_Id"));
                scheduleEntry.setStart_Date(resultSet.getDate("Start_Date"));
                scheduleEntry.setEnd_Date(resultSet.getDate("End_Date"));
                scheduleEntry.setDescription(resultSet.getString("Description"));
                scheduleEntries.add(scheduleEntry);


            }//end while
            resultSet.close();
            statement.close();
            con.close();
            return scheduleEntries;
        }//end try
        catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ScheduleEntry addEntry(ScheduleEntry scheduleEntry) {
        return null;
    }

    @Override
    public ScheduleEntry updateEntry(ScheduleEntry scheduleEntry) {
        return null;
    }

}