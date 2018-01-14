package com.example.tobiaszdobrowo.codenametreasure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobiaszdobrowo on 19.12.2017.
 */

public class database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "wpisy"; // nazwa DB

    private static final String TABLE_OBJECTS = "objects"; // nazwa tabeli

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_OBJECT = "object";

    public database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_OBJECTS_TABLE = "CREATE TABLE " + TABLE_OBJECTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_OBJECT + " TEXT" + ")";
        db.execSQL(CREATE_OBJECTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBJECTS);

        onCreate(db);
    }

    public void addObject(Object object) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, object.getName());
        values.put(KEY_DATE, object.getDate());
        values.put(KEY_OBJECT, object.getObject());

        db.insert(TABLE_OBJECTS, null, values);
        db.close();
    }

    public Object getObject(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_OBJECTS, new String[] { KEY_ID,
                KEY_NAME, KEY_DATE, KEY_OBJECT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Object object = new Object(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3));

        return object;

    }

    public List<Object> getAllObjects() {

        List<Object> objectList = new ArrayList<Object>();

        String selectQuery = "SELECT    * FROM " + TABLE_OBJECTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Object object = new Object();
                object.setID(Integer.parseInt(cursor.getString(0)));
                object.setName(cursor.getString(1));
                object.setDate(cursor.getString(2));
                object.setObject(cursor.getString(3));
                objectList.add(object);
            } while (cursor.moveToNext());
        }

        return objectList;
    }

    public int getObjectsCount() {

        String countQuery = "SELECT * FROM " + TABLE_OBJECTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateObject(Object object) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, object.getName());
        values.put(KEY_DATE, object.getDate());
        values.put(KEY_OBJECT, object.getObject());

        return db.update(TABLE_OBJECTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(object.getID()) });
    }

    public void deleteObject(Object object) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_OBJECTS, KEY_ID + " = ?",
                new String[] { String.valueOf(object.getID()) });
        db.close();
    }

}
