package com.example.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.example.basketball.controller.services.PlayerService;
import com.example.basketball.model.Player;
import com.example.basketball.util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerManager {
    private static PlayerManager ourInstance;
    private List<Player> players;
    private Retrofit retrofit;
    private Context context;
    private PlayerService playerService;

    private PlayerManager(Context cntxt) {
        context = cntxt;
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        playerService = retrofit.create(PlayerService.class);
    }

    public static PlayerManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new PlayerManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    public synchronized void getAllPlayers(final PlayerCallback playerCallback) {
        Call<List<Player>> call = playerService.getAllPlayer(UserLoginManager.getInstance(context).getBearerToken());

        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }
    public synchronized void createPlayer(final PlayerCallback playerCallback,Player player) {

        Call<Player> call = playerService.createPlayer(UserLoginManager.getInstance(context).getBearerToken(), player);

        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
               // players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }
    public synchronized void updatePlayer(final PlayerCallback playerCallback,Player player) {

        Call<Player> call = playerService.updatePlayer(UserLoginManager.getInstance(context).getBearerToken(), player);

        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                // players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }
    public synchronized void getPlayersByName(final PlayerCallback playerCallback,String name) {

        Call <List<Player>>  call = playerService.getPlayerByName(UserLoginManager.getInstance(context).getBearerToken(), name);

        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

    public synchronized void getPlayersByBaskets(final PlayerCallback playerCallback,Integer name) {

        Call <List<Player>>  call = playerService.getPlayerByBaskets(UserLoginManager.getInstance(context).getBearerToken(), name);

        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }
    public synchronized void getPlayerByDateGreater(final PlayerCallback playerCallback,String fecha) {

        Call <List<Player>>  call = playerService.getPlayerByDateGreater(UserLoginManager.getInstance(context).getBearerToken(), fecha);

        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }
    public synchronized void getPlayerByDateLess(final PlayerCallback playerCallback,String fecha) {

        Call <List<Player>>  call = playerService.getPlayerByDateLess(UserLoginManager.getInstance(context).getBearerToken(), fecha);

        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }
    public synchronized void getPlayerByDateBw(final PlayerCallback playerCallback,String fecha, String fecha2) {

        Call <List<Player>>  call = playerService.getPlayerByDateBw(UserLoginManager.getInstance(context).getBearerToken(), fecha, fecha2);

        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

    public Player getPlayer(String id) {
        for (Player player : players) {
            if (player.getId().toString().equals(id)) {
                return player;
            }
        }

        return null;
    }
}
