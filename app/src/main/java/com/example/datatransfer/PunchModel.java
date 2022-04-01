package com.example.datatransfer;

import com.google.firebase.database.Exclude;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Data model for Punch
 */
public class PunchModel {

    @Exclude
    private String key;

    private long id;
    private long accountID;
    private double force;
    private long date;

    //empty constructor
    public PunchModel(){

    }
    public PunchModel(long id, long accountID, double force, long date) {
        this.id = id;
        this.accountID = accountID;
        this.force = force;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String toString (int attempt, String dateFormat, String numFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat, Locale.CANADA);
        Date date = new Date(this.date);
        DecimalFormat myFormat = new DecimalFormat(numFormat);

        return String.format("Attempt %d:\nForce: %sN\nDate: %s\n\n", attempt, myFormat.format(force), df.format(date));
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}