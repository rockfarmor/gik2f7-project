package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.Model.Category;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class CategoryCrud implements ICategoryCrud {
    private Connection con;
    @Override
    public Category addCategory(Category category) {
        try{

            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");


            String sqlSelectAllEntries = "INSERT INTO `Category`(`Category_Name`, `Description`) VALUES (?,?)";


            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);

            statement.setString(1,category.getCategoryName());
            statement.setString(2,category.getDescription());



            statement.executeUpdate();

            statement.close();
            con.close();
            return category;
        }
        catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }
}
