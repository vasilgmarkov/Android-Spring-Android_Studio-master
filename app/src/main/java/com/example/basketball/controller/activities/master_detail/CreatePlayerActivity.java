package com.example.basketball.controller.activities.master_detail;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.EquipoCallback;
import com.example.basketball.controller.managers.PlayerCallback;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.controller.managers.TeamManager;
import com.example.basketball.model.Equipo;
import com.example.basketball.model.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by usu27 on 20/4/16.
 */
public class CreatePlayerActivity extends AppCompatActivity implements PlayerCallback, EquipoCallback {

    String[]  pos = {"Alero","Pivot","Base"};
    TextView name;
    TextView basket;
    TextView rebotes;
    TextView asistencias;
    static TextView birthDate;
    Spinner posicion,team;
    Button createButton;
    Player p = new Player();
    List<String> nombresTeam = new ArrayList<String>();
    List<Equipo> equipos = new ArrayList<Equipo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);

        name = (TextView) findViewById(R.id.nameP);
        basket = (TextView) findViewById(R.id.basketP);
        rebotes = (TextView) findViewById(R.id.rebotesP);
        asistencias = (TextView) findViewById(R.id.asistenciaP);
        posicion = (Spinner) findViewById(R.id.posP);
        birthDate = (TextView) findViewById(R.id.birthDate);
        team = (Spinner) findViewById(R.id.spinnerTeam);



        TeamManager.getInstance(this).getAllEquipo(CreatePlayerActivity.this);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, pos);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        posicion.setAdapter(spinnerArrayAdapter);

        createButton = (Button) findViewById(R.id.CreateButtonP);

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"datePicker");
            }
        });


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Snackbar.make(v, "Jugador Creado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                p.setName(name.getText().toString());
                p.setBaskets(Integer.parseInt(basket.getText().toString()));
                p.setRebotes(Integer.parseInt(rebotes.getText().toString()));
                p.setAsistencias(Integer.parseInt(asistencias.getText().toString()));
                p.setPos((String) posicion.getSelectedItem());
                p.setFechaNacimiento(birthDate.getText().toString());
                p.setEquipo(equipos.get(team.getSelectedItemPosition()));
                PlayerManager.getInstance(v.getContext()).createPlayer(CreatePlayerActivity.this, p);


                new CountDownTimer(2500, 1000) {
                    public void onFinish() {
                        Intent i = new Intent(v.getContext(),PlayerListActivity.class);
                        startActivity(i);
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();

            }
        });

    }

    @Override
    public void onSuccess(List<Player> playerList) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onSuccessE(List<Equipo> equiposList) {

            for (Equipo equipo : equiposList) {
                nombresTeam.add(equipo.getName());
            }
            for (Equipo equipo : equiposList) {
                equipos.add(equipo);
            }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, nombresTeam);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        team.setAdapter(spinnerArrayAdapter);

    }

    @Override
    public void onFailureE(Throwable t) {

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
                birthDate.setText(year+"-0" + month + "-0" + day );
            }
            else if (day<=9){
                birthDate.setText(year+"-" + month + "-0" + day );
            }
            else if (month<=9){
                birthDate.setText(year+"-0" + month + "-" + day );
            }
            else {
                birthDate.setText(year+"-" + month + "-" + day );

            }
        }

    }
}
