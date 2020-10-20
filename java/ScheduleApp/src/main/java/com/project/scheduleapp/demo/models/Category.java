package com.project.scheduleapp.demo.models;

public class Category {

    private Integer Category_Id;
    private String Category_Name;
    private String Description;

    public Integer getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(Integer category_Id) {
        Category_Id = category_Id;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String category_Name) {
        Category_Name = category_Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
