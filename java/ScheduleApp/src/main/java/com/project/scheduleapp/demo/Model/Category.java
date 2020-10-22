package com.project.scheduleapp.demo.Model;

public class Category {

    public static Category Category1 = new Category(1, "Kock", "Lagar mat");
    public static Category Category2 = new Category(2, "Reception", "Står i reception");



    private int categoryId;
    private String categoryName;
    private String description;


    public Category(int categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
