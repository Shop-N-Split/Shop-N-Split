package com.akshaj02.shopnsplit;

public class SplitModel {
    private String date;
    private String moneyOwed;
    private String moneyPaid;
    private String expense;
    //private String text2;

    SplitModel(String expense, String date, String moneyOwed, String moneyPaid) {
        this.date = date;
        this.moneyOwed = moneyOwed;
        this.moneyPaid = moneyPaid;
        this.expense = expense;
    }


    public String getDate() {
        return date;
    }

    public String getMoneyOwed() {
        return moneyOwed;
    }

    public String getMoneyPaid() {
        return moneyPaid;
    }

    public String getExpense() {
        return expense;
    }
}
