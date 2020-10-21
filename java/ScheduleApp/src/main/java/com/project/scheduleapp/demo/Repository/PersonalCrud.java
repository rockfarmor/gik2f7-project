package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.models.Personal;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class PersonalCrud implements IPersonalCrud {
    private Connection con;
    @Override
    public Personal getPersonalById(Integer Id) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");

            String sqlSelectAllEntries = "SELECT * FROM Personal WHERE Id=?";
            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);
            statement.setInt(1,Id);

            ResultSet resultSet = statement.executeQuery();
            Personal person = new Personal();
            while(resultSet.next()) {
                person.setId(resultSet.getInt("Id"));
                person.setSchedule_id(resultSet.getInt("Schedule_Id"));
                person.setName(resultSet.getString("Name"));
                person.setUsername(resultSet.getString("UserName"));
                person.setPassword(resultSet.getString("Password"));
                person.setSalary(resultSet.getInt("Salary"));
                person.setIs_admin(resultSet.getInt("is_admin"));
                person.setIs_logged_in(resultSet.getInt("is_logged_in"));
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
    public Personal addEntry(Personal Personal) {
        return null;
    }

    @Override
    public Personal updatePersonal(Personal Personal) {
        return null;
    }
}
