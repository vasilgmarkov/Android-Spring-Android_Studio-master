package com.example.basketball.controller.activities.master_detail;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.PlayerCallback;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.Player;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FindPlayers extends AppCompatActivity implements PlayerCallback{
    Spinner spinner;
  static   EditText editText1,editText2;
    Button find;
    ListView playersList;
    String[]  options = {"Find by name","Find by baskets","Find by birth date grater than","Find by birth date lesser than","Find by birth date between"};
    Integer SpinnerPos;
    ListAdapter adaptador;
    List<String> nombres = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_players);

        spinner = (Spinner) findViewById(R.id.spinnerSearchOptions);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        find = (Button) findViewById(R.id.Buscar);
        playersList = (ListView) findViewById(R.id.listViewPlayers);

        editText2.setVisibility(View.INVISIBLE);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, options);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               editText1.setText("");
               editText2.setText("");
               if (position == 4) {
                   editText1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           DialogFragment newFragment = new DatePickerFragment();
                           newFragment.show(getFragmentManager(),"datePicker");
                       }
                   });
                   editText2.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           DialogFragment newFragment = new DatePickerFragment2();
                           newFragment.show(getFragmentManager(),"datePicker");
                       }
                   });
                   editText2.setVisibility(View.VISIBLE);
                   editText1.setHint("DD-MM-AAAA");
                   editText2.setHint("DD-MM-AAAA");
               }
               else if(position == 2 || position == 3){
                   editText1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           DialogFragment newFragment = new DatePickerFragment();
                           newFragment.show(getFragmentManager(),"datePicker");
                       }
                   });
                   editText1.setEnabled(true);
                   editText1.setHint("DD-MM-AAAA");
                   editText2.setVisibility(View.INVISIBLE);
               }
               else if (position==1){
                   editText1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                       }
                   });
                   editText1.setInputType(InputType.TYPE_CLASS_NUMBER);
                   editText2.setVisibility(View.INVISIBLE);
                   editText1.setHint("");
                   editText2.setHint("");
               }
               else {
                   editText1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                       }
                   });
                   editText1.setInputType(InputType.TYPE_CLASS_TEXT);
                   editText2.setVisibility(View.INVISIBLE);
                   editText1.setHint("");
                   editText2.setHint("");
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpinnerPos = spinner.getSelectedItemPosition();
                // by name
                if (SpinnerPos == 0){
                    PlayerManager.getInstance(v.getContext()).getPlayersByName(FindPlayers.this, editText1.getText().toString());
                }
                //by baskets <
                if (SpinnerPos == 1){
                    PlayerManager.getInstance(v.getContext()).getPlayersByBaskets(FindPlayers.this, Integer.parseInt(editText1.getText().toString()));
                }
                //by birth date <
                if (SpinnerPos == 2){
                    PlayerManager.getInstance(v.getContext()).getPlayerByDateGreater(FindPlayers.this, editText1.getText().toString());
                }
                //by birth date >
                if (SpinnerPos == 3){
                    PlayerManager.getInstance(v.getContext()).getPlayerByDateLess(FindPlayers.this, editText1.getText().toString());
                }
                //< by birth date >
                if (SpinnerPos == 4){
                    PlayerManager.getInstance(v.getContext()).getPlayerByDateBw(FindPlayers.this, editText1.getText().toString(), editText2.getText().toString());
                }

            }
        });

    }

    @Override
    public void onSuccess(final List<Player> playerList2) {
        if (playerList2 == null){
            nombres.clear();
        }else {
            nombres.clear();
            for (Player player : playerList2) {
                nombres.add(player.getName());
            }
        }

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres);
        playersList.setAdapter(adaptador);
    }

    @Override
    public void onFailure(Throwable t) {

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
            editText1.setText("0" + day + "-0" + month + "-" + year);
        }
        else if (day<=9){
            editText1.setText("0" + day + "-" + month + "-" + year);
        }
        else if (month<=9){
            editText1.setText(day + "-0" + month + "-" + year);
        }
        else {
            editText1.setText(day + "-" + month + "-" + year);
        }
    }

    }
    public  static class DatePickerFragment2 extends DialogFragment
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
                editText2.setText("0" + day + "-0" + month + "-" + year);
            }
            else if (day<=9){
                editText2.setText("0" + day + "-" + month + "-" + year);
            }
            else if (month<=9){
                editText2.setText(day + "-0" + month + "-" + year);
            }
            else {
                editText2.setText(day + "-" + month + "-" + year);
            }
        }

    }
}
