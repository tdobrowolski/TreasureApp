package com.example.tobiaszdobrowo.codenametreasure;

import java.util.Random;

/**
 * Created by tobiaszdobrowo on 03.12.2017.
 */

public class Object {
    private String mName;
    private String mDate;
    private String mObject;

    // statyczne tablice, na podstawie których zostaną uzupełnione obiekty
    private static String[] sNames = {"Ezio Auditore",
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
    }

}
