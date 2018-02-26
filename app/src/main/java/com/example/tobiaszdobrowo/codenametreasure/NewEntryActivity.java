package com.example.tobiaszdobrowo.codenametreasure;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.CalendarContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
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
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class NewEntryActivity extends AppCompatActivity {

    TextView datePicker;
    EditText namePicker, objectPicker;
    Switch eventSwitch;
    int year, month, day;
    Calendar mDate;
    String dPicker;
    boolean stateSwitch;
    int[] data = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        setTitle(R.string.newentry_text);

        datePicker = findViewById(R.id.text_pick_date);
        namePicker = findViewById(R.id.new_name);
        objectPicker = findViewById(R.id.new_object);

        eventSwitch = findViewById(R.id.event_switch);
        eventSwitch.setEnabled(false);

        mDate = Calendar.getInstance();

        day = mDate.get(Calendar.DAY_OF_MONTH);
        month = mDate.get(Calendar.MONTH);
        year = mDate.get(Calendar.YEAR);

        eventSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    stateSwitch = true;
                } else {
                    stateSwitch = false;
                }
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(NewEntryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        //akcja po zatwierdzeniu wyboru daty
                        month += 1;

                        dPicker = dayOfMonth + "." + month + "." + year;
                        data[0] = year;
                        data[1] = month-1;
                        data[2] = dayOfMonth;
                        datePicker.setText(getResources().getString(R.string.pickeddate_text) + dPicker);
                        eventSwitch.setEnabled(true);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });
    }

    public void saveToDB(String nPicker, String oPicker) {

        database db = new database(this);

        if (dPicker == null) {dPicker = getResources().getString(R.string.nodate_text);}

        db.addObject(new Object(nPicker, dPicker, oPicker));

        //List<Object> contacts = db.getAllObjects();

        /*for (Object cn : contacts) {
            String log = "Id: " + cn.getID()+" ,Name: " + cn.getName() + " ,Object: " + cn.getObject();
            // Writing Contacts to log
            Log.d("Name: ", log);}*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_check:

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                String nPicker = namePicker.getText().toString();
                String oPicker = objectPicker.getText().toString();

                if (nPicker.equals("")) {
                    CharSequence text = getResources().getString(R.string.checkname_text);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                if (oPicker.equals("")) {
                    CharSequence text = getResources().getString(R.string.checkobject_text);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                if (!nPicker.equals("") && !oPicker.equals("")) {
                    saveToDB(nPicker, oPicker);
                    if (stateSwitch) { addEvent(nPicker, oPicker); }
                    finish();
                }

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_new_entry, menu);
        Drawable drawable = menu.findItem(R.id.action_check).getIcon();

        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.colorPrimaryBlue));
        menu.findItem(R.id.action_check).setIcon(drawable);

        return true;
    }

    public void addEvent(String nPicker, String oPicker) {
        String title = oPicker + " - " + nPicker;
        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, title);
        //calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Beach House");
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Dodane przez Treasure.");
        Log.d(getClass().getName(), "day = " + data[2]);
        Log.d(getClass().getName(), "month = " + data[1]);
        Log.d(getClass().getName(), "year = " + data[0]);
        GregorianCalendar calDate = new GregorianCalendar(data[0], data[1], data[2]);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.getTimeInMillis());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.getTimeInMillis());

        startActivity(calIntent);
    }
}
