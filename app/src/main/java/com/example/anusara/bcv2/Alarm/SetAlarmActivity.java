package com.example.anusara.bcv2.Alarm;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.anusara.bcv2.MainActivity;
import com.example.anusara.bcv2.Member.MPost.MPostActivity;
import com.example.anusara.bcv2.Member.MPost.MPostaddActivity;
import com.example.anusara.bcv2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SetAlarmActivity extends AppCompatActivity {

    Button submit;

    String date;
    String time;

    TextView tv1;
    TextView tv2;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        tv1 = (TextView) findViewById(R.id.textview1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        submit = (Button) findViewById(R.id.button);

        startService(new Intent(this, YourService.class));


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv3.setText(date + "  " + time);
                saveTime(date+time);
            }
        });
    }

    public void showDatePickerDialog(View v) {
        /*DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");*/

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        System.out.println("the selected " + mDay);
        DatePickerDialog dialog = new DatePickerDialog(SetAlarmActivity.this,
                new mDateSetListener(), mYear, mMonth, mDay);
        dialog.show();


    }

    public void showTimePickerDialog(View v) {
        /*DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");*/

        Calendar c = Calendar.getInstance();
        int HOUR_OF_DAY = c.get(Calendar.HOUR_OF_DAY);
        int MINUTE = c.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(SetAlarmActivity.this,
                new mTimeSetListener(), HOUR_OF_DAY, MINUTE, true);
        dialog.show();
    }

    public void saveTime(String time) {
        SharedPreferences.Editor editor = getSharedPreferences("time_alarm", MODE_PRIVATE).edit();
        Log.e( "saveTime: ", time);
        editor.putString("dateTime", time);
        editor.commit();
    }

    class mDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            tv1.setText(String.format("%02d", dayOfMonth)
                    + "/" + String.format("%02d", monthOfYear)
                    + "/" + year);

            date = tv1.getText().toString();

            Log.e("showDatePickerDialog: ", date);
        }
    }

    class mTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            tv2.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));

            time = tv2.getText().toString();

            Log.e("onTimeSet: ", time);
        }
    }


}
