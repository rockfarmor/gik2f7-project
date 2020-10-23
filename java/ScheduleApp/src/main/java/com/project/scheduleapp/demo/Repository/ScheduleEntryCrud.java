package com.project.scheduleapp.demo.Repository;


import com.project.scheduleapp.demo.Model.Category;
import com.project.scheduleapp.demo.Model.Personal;
import com.project.scheduleapp.demo.Model.Shift;
import com.project.scheduleapp.demo.Service.CategoryService;
import com.project.scheduleapp.demo.Service.ScheduleEntryService;
import com.project.scheduleapp.demo.models.ScheduleEntry;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ScheduleEntryCrud implements IScheduleEntryCrud {

    private Connection con;


    @Override
    public List<ScheduleEntry> getEntriesByScheduleId(int scheduleId){
        List<ScheduleEntry> scheduleEntries = new ArrayList<>();
        try{

            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");




            String sqlSelectAllEntries = "SELECT * FROM Schedule_Entry WHERE Schedule_Id =? ORDER BY Start_Date";
            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);
            statement.setInt(1,scheduleId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                ScheduleEntry scheduleEntry = new ScheduleEntry();
                scheduleEntry.setEntry_Id(resultSet.getInt("Entry_Id"));
                scheduleEntry.setSchedule_Id(resultSet.getInt("Schedule_Id"));
                scheduleEntry.setCategory_Id(resultSet.getInt("Category_Id"));
                scheduleEntry.setStart_Date(resultSet.getTimestamp("Start_Date"));
                scheduleEntry.setEnd_Date(resultSet.getTimestamp("End_Date"));
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
    public List<ScheduleEntry> getAllEntries(){
        List<ScheduleEntry> scheduleEntries = new ArrayList<>();
        try{

            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");

            Statement statement = con.createStatement();
            String sqlSelectAllEntries = "SELECT * FROM Schedule_Entry ORDER BY Start_Date";
            ResultSet resultSet = statement.executeQuery(sqlSelectAllEntries);
            while (resultSet.next()){
                ScheduleEntry scheduleEntry = new ScheduleEntry();
                scheduleEntry.setEntry_Id(resultSet.getInt("Entry_Id"));
                scheduleEntry.setSchedule_Id(resultSet.getInt("Schedule_Id"));
                scheduleEntry.setCategory_Id(resultSet.getInt("Category_Id"));
                scheduleEntry.setStart_Date(resultSet.getTimestamp("Start_Date"));
                scheduleEntry.setEnd_Date(resultSet.getTimestamp("End_Date"));
                scheduleEntry.setDescription(resultSet.getString("Description"));
                scheduleEntries.add(scheduleEntry);


            }
            resultSet.close();
            statement.close();
            con.close();
            return scheduleEntries;
        }
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
    public Shift addShiftEntry(Shift shift, Personal personal) {

        try{

            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");

            /*Statement statement = con.createStatement();
            String sqlSelectAllEntries = "SELECT * FROM Schedule_Entry";
            ResultSet resultSet = statement.executeQuery(sqlSelectAllEntries);
            */
            //String sqlSelectAllEntries = "INSERT into Schedule_Entry WHERE Username=? AND Password=?";
            String sqlSelectAllEntries = "INSERT INTO `Schedule_Entry`(`Schedule_Id`, `Category_Id`, `Start_Date`, `End_Date`, `Description`) VALUES (?,?,?,?,?)";
            Timestamp start = Timestamp.valueOf(shift.getStartDate());
            Timestamp end = Timestamp.valueOf(shift.getEndDate());

            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);
            statement.setInt(1, personal.getUniqueID());
            statement.setInt(2, shift.getCategory().getCategoryId()); //Category id
            statement.setTimestamp(3, start);
            statement.setTimestamp(4, end);
            statement.setString(5,shift.getDescription());

            statement.executeUpdate();

            statement.close();
            con.close();
            return shift;
        }
        catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    @Override
    public ScheduleEntry updateEntry(ScheduleEntry scheduleEntry) {
        return null;
    }

    @Override
    public Shift deleteShift(Integer Id) {
        try{

            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");


            String sqlSelectAllEntries = "DELETE FROM `Schedule_Entry` WHERE Entry_Id=?";

            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);
            statement.setInt(1, Id);

            statement.executeUpdate();
            statement.close();
            con.close();
            return null;
        }
        catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

}
