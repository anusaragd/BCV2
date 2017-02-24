package com.example.anusara.bcv2.Alarm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.anusara.bcv2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by anusara on 19-Feb-17.
 */
//public class DatePickerFragment {
//}

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    static int year;
    static int month;
    static int day;
    static int dayofyear;
    static int dayofweek;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        dayofyear = c.get(Calendar.DAY_OF_YEAR);
        dayofweek = c.get(Calendar.DAY_OF_WEEK);



        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public int getDate(String type){
        switch (type){
            case "year":return year;
            case "month":return month;
            case "day": return  day;
            case "dayofyear":return dayofyear;
            case "dayofweek":return dayofweek;
        }
        return 0;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
        Date date = new Date(year, month, day-1);
        String dayOfWeek = simpledateformat.format(date);



        // Do something with the date chosen by the user
        TextView tv1= (TextView) getActivity().findViewById(R.id.textview1);
        final Calendar c = Calendar.getInstance();
        c.set(year,month,day);
        //this.dayofweek = c.get(Calendar.DAY_OF_WEEK);
        this.year=year;
        this.month=month;
        this.day=day;
        tv1.setText(this.day + "/" + (month+1) + "/" + this.year);
        Log.v("date",this.month+"");
        TextView tva = (TextView)getActivity().findViewById(R.id.textView3);

        System.out.print(month);



    }
}
