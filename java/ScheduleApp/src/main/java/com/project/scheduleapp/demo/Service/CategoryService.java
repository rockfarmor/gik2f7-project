package com.project.scheduleapp.demo.Service;

import com.project.scheduleapp.demo.Model.Category;
import com.project.scheduleapp.demo.Repository.ICategoryCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private ICategoryCrud crud;

    public Category addCategory(Category category){return crud.addCategory(category);}


}
