package com.example.anusara.bcv2.Alarm;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anusara.bcv2.Member.MPost.MPostActivity;
import com.example.anusara.bcv2.Member.MPost.MPostaddActivity;
import com.example.anusara.bcv2.R;

public class SetAlarmActivity extends AppCompatActivity {

    TextView tva;
    Button submit;
    DatePickerFragment date = new DatePickerFragment();
    TimePickerFragment time = new TimePickerFragment();

    static int hour;
    static int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);


        submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tva = (TextView) findViewById(R.id.textView3);
            }
        });
        //tva = (TextView) findViewById(R.id.textView3);
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
