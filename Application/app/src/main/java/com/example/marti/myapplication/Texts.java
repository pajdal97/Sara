package com.example.marti.myapplication;

/**
 * Created by ggjimmy on 4/7/17.
 */

public class Texts {
    private String ym;//years and month
    private String hm;//hours and minutes
    private long secondsRemain;

    public Texts(String ym, String hm,long secondsRemain){
        this.ym = ym;
        this.hm = hm;
        this.secondsRemain = secondsRemain;
    }
    public String getYm(){
        return this.ym;
    }
    public String getHm(){
        return this.hm;
    }
    public long getSecondsRemain(){
        return secondsRemain;
    }
    public void setSecondsRemain(long seconds){
        this.secondsRemain = seconds;
    }
}
