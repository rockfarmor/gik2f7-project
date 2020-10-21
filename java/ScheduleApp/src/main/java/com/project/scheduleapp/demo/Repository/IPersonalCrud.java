package com.project.scheduleapp.demo.Repository;

import com.project.scheduleapp.demo.models.Personal;


public interface IPersonalCrud {

    public Personal getPersonalById(Integer Id);
    public Personal addEntry(Personal Personal);
    public Personal updatePersonal(Personal Personal);
}
