package com.example.tobiaszdobrowo.codenametreasure;

import java.util.Random;

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

    // statyczne tablice, na podstawie których zostaną uzupełnione obiekty
    /*private static String[] sNames = {"Ezio Auditore",
            "Desmond Miles",
            "Haytham Kenway",
            "Edward Kenway",
            "Jacob Frye"};

    private static String[] sDates = {"24.12.2017",
            "28.01.2018",
            "12.03.2018",
            "10.12.2017",
            "23.12.2018"};

    private static String[] sObjects = {"- 24.99 PLN",
            "+ 34.99 PLN",
            "Książka",
            "- 10 PLN",
            "Gra"};

    public Object() {
        Random random = new Random();

        // ustawiamy losowe imię
        mName = sNames[random.nextInt(sNames.length)];
        mDate = sDates[random.nextInt(sDates.length)];
        mObject = sObjects[random.nextInt(sObjects.length)];
    }

    public String getName() {
        return mName;
    }

    public String getDate() {
        return mDate;
    }

    public String getObject() {
        return mObject;
    }*/

}
