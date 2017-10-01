package com.example.jrforest_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jacqueline on 2017-09-17.
 */

public class Counter {
    private Date date;
    private int initial;
    private int current;
    private String name;
    private String comment;


    public Counter(String name, int initial){
        date = new Date();
        this.initial = initial;
        this.current = initial;
        this.name = name;
        this.comment = "";


    }

    public Counter(String name, int initial, String comment){
        date = new Date();
        this.initial = initial;
        this.current = initial;
        this.name = name;
        this.comment = comment;
    }


    public void setCurrent (int current){
        this.current = current;

    }

    public int getCurrent(){
        return current;
    }

    public void setInitial(int initial){
        this.initial = initial;
    }

    public int getInitial(){
        return initial;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }

    public void increment() {current++;}

    public void decrement() {current--;}

    public void reset() {current = initial;}

    public void updateDate(){
        date = new Date();
    }

    public String getDate(){
        String simpleDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return simpleDate;
    }


}
