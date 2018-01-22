package com.example.tobiaszdobrowo.codenametreasure;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class NewEntryActivity extends AppCompatActivity {

    TextView dateTextView;
    TextView datePicker;
    EditText namePicker, objectPicker;
    int year, month, day;
    Calendar mDate;
    String dPicker;
    Switch notificationPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        setTitle("Dodaj wpis");

        /*List<Object> contacts = db.getAllObjects();

        for (Object cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Object: " + cn.getObject();
            // Writing Contacts to log
            Log.d("Name: ", log);}*/

        datePicker = findViewById(R.id.text_pick_date);
        namePicker = findViewById(R.id.new_name);
        objectPicker = findViewById(R.id.new_object);
        notificationPicker = findViewById(R.id.notification_switch);

        mDate = Calendar.getInstance();

        day = mDate.get(Calendar.DAY_OF_MONTH);
        month = mDate.get(Calendar.MONTH);
        year = mDate.get(Calendar.YEAR);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(NewEntryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        //akcja po zatwierdzeniu wyboru daty

                        dPicker = dayOfMonth + "." + month+1 + "." + year;
                        Log.d("string", dPicker);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        notificationPicker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    // wlaczony
                } else {
                    // wylaczony
                }

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_check:

                String nPicker = namePicker.getText().toString();
                String oPicker = objectPicker.getText().toString();

                database db = new database(this);

                db.addObject(new Object(nPicker, dPicker, oPicker));


                List<Object> contacts = db.getAllObjects();

                for (Object cn : contacts) {
                    String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Object: " + cn.getObject();
                    // Writing Contacts to log
                    Log.d("Name: ", log);}

                // NOTIFIKACJA TESTOWA


                // KONIEC NOTYFIKACJI TESTOWEJ

                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }



}
