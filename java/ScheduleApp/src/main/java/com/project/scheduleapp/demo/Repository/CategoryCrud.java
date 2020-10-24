package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.Model.Category;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class CategoryCrud implements ICategoryCrud {
    private Connection con;
    @Override
    public Category addCategory(Category category) {
        try{

            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");


            String sqlSelectAllEntries = "INSERT INTO `Category`(`Category_Name`,`Description`) VALUES (?,?)";


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

    @Override
    public List<Category> getAllCategory() {
        List<Category> categorylist = new ArrayList<>();

        try{

            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");

            Statement statement = con.createStatement();
            String sqlSelectAllEntries = "SELECT * FROM Category ORDER BY Category_Id";
            ResultSet resultSet = statement.executeQuery(sqlSelectAllEntries);
            while (resultSet.next()){
                Category category = new Category(resultSet.getInt("Category_Id"),resultSet.getString("Category_Name"),resultSet.getString("Description"));
                categorylist.add(category);




            }//end while
            resultSet.close();
            statement.close();
            con.close();
            return categorylist;
        }//end try
        catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/CiW6XfVzNP", "CiW6XfVzNP", "RYRF7gAMIn");

            String sqlSelectAllEntries = "SELECT * FROM Category WHERE Category_Id=?";
            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);
            statement.setInt(1,categoryId);

            ResultSet resultSet = statement.executeQuery();

            Category category = null;
            while(resultSet.next()) {
                category = new Category(resultSet.getInt("Category_Id"),resultSet.getString("Category_Name"),resultSet.getString("Description"));

            }
            resultSet.close();
            statement.close();
            con.close();
            return category;

        }  catch (SQLException ex){
            Logger.getLogger(ScheduleEntryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
