package com.example.basketball.controller.services;


import com.example.basketball.model.Equipo;
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
/**
 * Created by usu27 on 22/4/16.
 */
public interface EquipoService {
    @GET("/api/equipos")
    Call<List<Equipo>> getAllEquipo(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization);

}
