package com.example.tobiaszdobrowo.codenametreasure;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class NewEntryActivity extends AppCompatActivity {

    TextView datePicker;
    EditText namePicker, objectPicker;
    int year, month, day;
    Calendar mDate;
    String dPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        setTitle("Dodaj wpis");

        datePicker = findViewById(R.id.text_pick_date);
        namePicker = findViewById(R.id.new_name);
        objectPicker = findViewById(R.id.new_object);

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
                        datePicker.setText("Wybrana data: " + dPicker);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });
    }

    public void saveToDB(String nPicker, String oPicker) {

        database db = new database(this);

        if (dPicker == null) {dPicker = "Brak daty";}

        db.addObject(new Object(nPicker, dPicker, oPicker));

        List<Object> contacts = db.getAllObjects();

        for (Object cn : contacts) {
            String log = "Id: " + cn.getID()+" ,Name: " + cn.getName() + " ,Object: " + cn.getObject();
            // Writing Contacts to log
            Log.d("Name: ", log);}
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
                    CharSequence text = "Uzupełnij imię i nazwisko";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                if (oPicker.equals("")) {
                    CharSequence text = "Uzupełnij obiekt";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                if (!nPicker.equals("") && !oPicker.equals("")) {
                    saveToDB(nPicker, oPicker);
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
}
