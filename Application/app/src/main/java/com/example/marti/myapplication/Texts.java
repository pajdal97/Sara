package com.example.marti.myapplication;

/**
 * Created by ggjimmy on 4/7/17.
 */

public class Texts {
    private String ym;//years and month
    private String hm;//hours and minutes

    public Texts(String ym, String hm){
        this.ym = ym;
        this.hm = hm;
    }
    public String getYm(){
        return this.ym;
    }
    public String getHm(){
        return this.hm;
    }
}
