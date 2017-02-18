package com.example.anusara.bcv2.Alarm;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.anusara.bcv2.R;

import java.util.Calendar;

/**
 * Created by anu_s on 2/6/2559.
 */
//public class TimePickerFragment extends DialogFragment {
//}

/**
 * Created by jahid on 12/10/15.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    static int hour;
    static int minute;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
         hour = c.get(Calendar.HOUR_OF_DAY);
         minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
    public int getDate(String type){
        switch (type){
            case "hour":return hour;
            case  "minute":return minute;
        }
        return 0;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        TextView tv1=(TextView) getActivity().findViewById(R.id.textView2);

        this.hour=hourOfDay;
        this.minute=minute;
        tv1.setText(view.getCurrentHour()+" : "+view.getCurrentMinute());

    }

}
