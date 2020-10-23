package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.Model.Category;

import java.util.List;

public interface ICategoryCrud {

    public Category addCategory(Category category);
    public List<Category> getAllCategory();
    public Category getCategoryById(Integer categoryId);
}
