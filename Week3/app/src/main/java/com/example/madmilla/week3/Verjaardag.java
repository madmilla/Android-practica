package com.example.madmilla.week3;

/**
 * Created by madmilla on 28-5-15.
 */
public class Verjaardag {

    private int _id;
    private String _name;
    private String _date;

    public Verjaardag(){}

    public Verjaardag(int id, String name, String date){
        this._id = id;
        this._name = name;
        this._date = date;
    }

    public Verjaardag(String name, String date){
        this._name = name;
        this._date = date;
    }

    public void setID(int id){
        this._id = id;
    }

    public int getID(){
        return this._id;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getName(){
        return _name;
    }

    public void setDate(String date){
        this._date = date;
    }

    public String getDate(){
        return _date;
    }
}
