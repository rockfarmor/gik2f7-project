package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.Model.Category;
import com.project.scheduleapp.demo.Model.Personal;
import com.project.scheduleapp.demo.Model.Shift;
import com.project.scheduleapp.demo.Service.CategoryService;
import com.project.scheduleapp.demo.Service.ScheduleEntryService;
import com.project.scheduleapp.demo.models.PersonalOld;
import com.project.scheduleapp.demo.models.ScheduleEntry;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class PersonalCrud implements IPersonalCrud {
    private Connection con;


    @Override
    public List<Personal> getAllPersonal(ScheduleEntryService scheduleEntryService, CategoryService categoryService) {
        List<Personal> personals = new ArrayList<>();
        try{

            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");

            Statement statement = con.createStatement();
            String sqlSelectAllEntries = "SELECT * FROM Personal";
            ResultSet resultSet = statement.executeQuery(sqlSelectAllEntries);
            List<Category> categoryList = categoryService.getAllCategory();
            while (resultSet.next()){
                Personal person = new Personal(resultSet.getInt("Id"), resultSet.getString("Name"), resultSet.getString("UserName"), resultSet.getString("Password"), resultSet.getInt("Salary"), resultSet.getInt("is_admin"));
                personals.add(person);

                List<ScheduleEntry> scheduleEntries = scheduleEntryService.getEntriesByScheduleId(person.getUniqueID());



                if(scheduleEntries != null) {
                    for (ScheduleEntry s : scheduleEntries) {
                        //int shiftID, LocalDateTime startDate, LocalDateTime endDate, String description
                        if (categoryList != null) {
                            for(Category a : categoryList){
                                if(a.getCategoryId() ==  s.getCategory_Id()){
                                    Shift shift = new Shift(s.getEntry_Id(), s.getStart_Date().toLocalDateTime(), s.getEnd_Date().toLocalDateTime(), s.getDescription(), person,a);
                                    person.getSchedlist().add(shift);
                                }
                            }

                        }



                    }
                }

            }//end while
            resultSet.close();
            statement.close();
            con.close();
            return personals;
        }//end try
        catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Personal getPersonalById(Integer Id,ScheduleEntryService scheduleEntryService, CategoryService categoryService) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");

            String sqlSelectAllEntries = "SELECT * FROM Personal WHERE Id=?";
            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);
            statement.setInt(1,Id);

            ResultSet resultSet = statement.executeQuery();
            //PersonalOld person = new PersonalOld();
            Personal person  = null;
            List<Category> categoryList = categoryService.getAllCategory();
            while(resultSet.next()) {
                person = new Personal(resultSet.getInt("Id"), resultSet.getString("Name"), resultSet.getString("UserName"), resultSet.getString("Password"), resultSet.getInt("Salary"), resultSet.getInt("is_admin"));
                List<ScheduleEntry> scheduleEntries = scheduleEntryService.getEntriesByScheduleId(person.getUniqueID());



                if(scheduleEntries != null) {
                    for (ScheduleEntry s : scheduleEntries) {
                        //int shiftID, LocalDateTime startDate, LocalDateTime endDate, String description
                        if (categoryList != null) {
                            for(Category a : categoryList){
                                if(a.getCategoryId() ==  s.getCategory_Id()){
                                    Shift shift = new Shift(s.getEntry_Id(), s.getStart_Date().toLocalDateTime(), s.getEnd_Date().toLocalDateTime(), s.getDescription(), person,a);
                                    person.getSchedlist().add(shift);
                                }
                            }

                        }



                    }
                }


            }
            resultSet.close();
            statement.close();
            con.close();
            return person;

        }  catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Personal addEntry(Personal personal) {

        try{

            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");


            String sqlSelectAllEntries = "INSERT INTO `Personal`(`Name`, `UserName`, `Password`, `Salary`,`is_admin`, `Schedule_Id`, `is_logged_in`) VALUES (?,?,?,?,?,?,?)";


            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);

            statement.setString(1,personal.getName());
            statement.setString(2,personal.getUserName());
            statement.setString(3,personal.getPassword());
            statement.setInt(4,personal.getSalaryPerHour());
            statement.setInt(5,personal.getIsAdmin());
            statement.setInt(6,-1);
            statement.setInt(7,0);

            statement.executeUpdate();

            statement.close();
            con.close();
            return personal;
        }
        catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    @Override
    public Personal updatePersonal(Personal personal) {
        return null;
    }

    @Override
    public Personal verifyLogIn(String Username, String Password,ScheduleEntryService scheduleEntryService, CategoryService categoryService) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");

            String sqlSelectAllEntries = "SELECT * FROM Personal WHERE Username=? AND Password=?";
            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);
            statement.setString(1,Username);
            statement.setString(2,Password);

            ResultSet resultSet = statement.executeQuery();
            //PersonalOld person = new PersonalOld();
            Personal person  = null;
            List<Category> categoryList = categoryService.getAllCategory();
            while(resultSet.next()) {
                person = new Personal(resultSet.getInt("Id"), resultSet.getString("Name"), resultSet.getString("UserName"), resultSet.getString("Password"), resultSet.getInt("Salary"), resultSet.getInt("is_admin"));

                List<ScheduleEntry> scheduleEntries = scheduleEntryService.getEntriesByScheduleId(person.getUniqueID());


                if(scheduleEntries != null) {
                    for (ScheduleEntry s : scheduleEntries) {
                        //int shiftID, LocalDateTime startDate, LocalDateTime endDate, String description
                        if (categoryList != null) {
                            for(Category a : categoryList){
                                if(a.getCategoryId() ==  s.getCategory_Id()){
                                    Shift shift = new Shift(s.getEntry_Id(), s.getStart_Date().toLocalDateTime(), s.getEnd_Date().toLocalDateTime(), s.getDescription(), person,a);
                                    person.getSchedlist().add(shift);
                                }
                            }

                        }



                    }
                }
            }
            resultSet.close();
            statement.close();
            con.close();
            return person;

        }  catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public List<Shift> getAllShiftsEntries(CategoryService categoryService,ScheduleEntryService scheduleEntryService){
        List<Shift> shifts = new ArrayList<>();

        List<Personal> persons= getAllPersonal(scheduleEntryService,categoryService);
        for(Personal p: persons){
            for(Shift s:p.getSchedlist()){
                shifts.add(s);
            }

        }


        return shifts;
    }

}
