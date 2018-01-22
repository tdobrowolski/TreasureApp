package com.example.tobiaszdobrowo.codenametreasure;

/**
 * Created by tobiaszdobrowo on 03.12.2017.
 */

public class Object {

    // private variables
    int _id;
    String _name;
    String _date;
    String _object;

    public Object() {

    }

    public Object(int id, String name, String date, String object) {
        this._id = id;
        this._name = name;
        this._date = date;
        this._object = object;
    }

    public Object(String name, String date, String object) {
        this._name = name;
        this._date = date;
        this._object = object;
    }

    // ID
    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    // name
    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    // date
    public String getDate() {
        return this._date;
    }

    public void setDate(String date) {
        this._date = date;
    }

    // object
    public String getObject() {
        return this._object;
    }

    public void setObject(String object) {
        this._object = object;
    }
}