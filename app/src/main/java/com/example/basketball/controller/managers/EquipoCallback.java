package com.example.basketball.controller.managers;

import com.example.basketball.model.Equipo;
import com.example.basketball.model.Player;

import java.util.List;

public interface EquipoCallback {
    void onSuccessE(List<Equipo> equiposList);

    void onFailureE(Throwable t);
}
