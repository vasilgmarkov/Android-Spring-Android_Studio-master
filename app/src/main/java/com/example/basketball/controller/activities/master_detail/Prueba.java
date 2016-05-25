package com.example.basketball.controller.activities.master_detail;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.basketball.R;

import java.util.Calendar;

/**
 * Created by usu27 on 25/4/16.
 */
public class Prueba extends AppCompatActivity {

    static TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba);
        t = (TextView) findViewById(R.id.textView7);
    }

    public  static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            month++;
            if (day<=9 && month<=9) {
                t.setText("0" + day + "-0" + month + "-" + year);
            }
            else if (day<=9){
                t.setText("0" + day + "-" + month + "-" + year);
            }
            else if (month<=9){
                t.setText(day + "-0" + month + "-" + year);
            }
            else {
                t.setText(day + "-" + month + "-" + year);
            }
        }

    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(this.getFragmentManager(),"datePicker");
    }




}
