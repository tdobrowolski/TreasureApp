package com.example.tobiaszdobrowo.codenametreasure;

import java.util.Random;

/**
 * Created by tobiaszdobrowo on 03.12.2017.
 */

public class Object {
    private String mName;

    // statyczne tablice, na podstawie których zostaną uzupełnione obiekty
    private static String[] sNames = {"Ezio Auditore",
            "Desmond Miles",
            "Haytham Kenway",
            "Edward Kenway",
            "Jacob Frye"};

    public Object() {
        Random random = new Random();

        // ustawiamy losowe imię
        mName = sNames[random.nextInt(sNames.length)];
    }

    public String getName() {
        return mName;
    }

}
