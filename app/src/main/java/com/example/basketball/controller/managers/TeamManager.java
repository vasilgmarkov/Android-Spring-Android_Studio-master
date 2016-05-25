package com.example.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.example.basketball.controller.services.EquipoService;
import com.example.basketball.controller.services.PlayerService;
import com.example.basketball.model.Equipo;
import com.example.basketball.model.Player;
import com.example.basketball.util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamManager {
    private static TeamManager ourInstance;
    private List<Equipo> equipos;
    private Retrofit retrofit;
    private Context context;
    private EquipoService equipoService;

    private TeamManager(Context cntxt) {
        context = cntxt;
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        equipoService = retrofit.create(EquipoService.class);
    }

    public static TeamManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new TeamManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    public synchronized void getAllEquipo(final EquipoCallback equipoCallback) {
        Call<List<Equipo>> call = equipoService.getAllEquipo(UserLoginManager.getInstance(context).getBearerToken());

        call.enqueue(new Callback<List<Equipo>>() {
            @Override
            public void onResponse(Call<List<Equipo>> call, Response<List<Equipo>> response) {
                equipos = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    equipoCallback.onSuccessE(equipos);
                } else {
                    equipoCallback.onFailureE(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Equipo>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                equipoCallback.onFailureE(t);
            }
        });
    }


    public Equipo getEquipos(String id) {
        for (Equipo equipo : equipos) {
            if (equipo.getId().toString().equals(id)) {
                return equipo;
            }
        }

        return null;
    }
}
