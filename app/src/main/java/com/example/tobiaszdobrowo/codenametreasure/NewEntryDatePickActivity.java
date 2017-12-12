package com.example.tobiaszdobrowo.codenametreasure;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by tobiaszdobrowo on 12.12.2017.
 */

public class NewEntryDatePickActivity extends AppCompatActivity {

    TextView datePicker;
    int year, month, day;
    Calendar mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry_date);

        datePicker = findViewById(R.id.text_pick_date);

        mDate = Calendar.getInstance();

        day = mDate.get(Calendar.DAY_OF_MONTH);
        month = mDate.get(Calendar.MONTH);
        year = mDate.get(Calendar.YEAR);

        //month = month + 1;

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(NewEntryDatePickActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        //akcja po zatwierdzeniu wyboru daty

                    }
                }, year, month, day);

                datePickerDialog.show();

            }
        });

    }

}
