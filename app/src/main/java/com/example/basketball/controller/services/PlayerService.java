package com.example.basketball.controller.services;

import com.example.basketball.model.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Alfredo on 28/02/2016.
 */
public interface PlayerService {
    @GET("/api/players")
    Call<List<Player>> getAllPlayer(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization);

    @POST("api/players") //Se tiene que cambiar en un interfaz propia
    Call<Player> createPlayer(


            @Header("Authorization") String Authorization,
            @Body Player player);

    @PUT("api/players")
    Call<Player> updatePlayer(


            @Header("Authorization") String Authorization,
            @Body Player player);

    @GET("/api/players/byName/{name}")
    Call<List<Player>> getPlayerByName(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization,
            @Path("name") String name);

    @GET("/api/bp-by-canastas/{canastasTotales}")
    Call<List<Player>> getPlayerByBaskets(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization,
            @Path("canastasTotales") Integer canastasTotales);
    @GET("/api/fet-by-date/{fecha}")
    Call<List<Player>> getPlayerByDateGreater(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization,
            @Path("fecha") String fecha);
    @GET("/api/fet-by-datel/{fecha}")
    Call<List<Player>> getPlayerByDateLess(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization,
            @Path("fecha") String fecha);
    @GET("/api/fet-by-datebw/{fecha}/{fecha2}")
    Call<List<Player>> getPlayerByDateBw(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization,
            @Path("fecha") String fecha,
            @Path("fecha2") String fecha2);
}
