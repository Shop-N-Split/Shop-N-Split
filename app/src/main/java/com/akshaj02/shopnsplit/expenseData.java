package com.akshaj02.shopnsplit;

public class expenseData {

    String expenseName;
    String expenseAmount;
    String expenseDate;
    String expenseSplit;

    //create a constructor
    public expenseData(String expenseName, String expenseAmount, String expenseDate, String expenseSplit) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.expenseSplit = expenseSplit;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public String getExpenseSplit() {
        return expenseSplit;
    }
}
