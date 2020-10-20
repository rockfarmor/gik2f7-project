package com.project.scheduleapp.demo.Model;

import java.util.List;

public class Account extends Personal {
    private int accountID;

    public Account(int uniqueID, String name, String userName, String password, int salaryPerHour) {
        super(uniqueID, name, userName, password, salaryPerHour);
    }


    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
