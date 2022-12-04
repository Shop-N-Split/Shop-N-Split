package com.akshaj02.shopnsplit;

public class Calculations {
    public static String calculate(String contacts, String moneyOwed) {
        //Split the money owed by the number of contacts
        double money = Double.parseDouble(moneyOwed);
        double count = contacts.split(",").length;
        double moneySplit = money/count;
        double total = moneySplit * (count-1);
        return Double.toString(total);
    }
}
