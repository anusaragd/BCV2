package com.example.anusara.bcv2.Alarm;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anusara.bcv2.R;

public class SetAlarmActivity extends AppCompatActivity {

    TextView tva;
    Button submit;
    DatePickerFragment date = new DatePickerFragment();
    TimePickerFragment time = new TimePickerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        tva = (TextView) findViewById(R.id.textView3);
        submit = (Button) findViewById(R.id.button);
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


}
