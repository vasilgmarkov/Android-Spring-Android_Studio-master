package com.example.basketball.model;

import java.util.Set;

/**
 * Created by usu27 on 22/4/16.
 */
public class Equipo {
    Long id;
    String nombre;
    String fechaCreacion;
    String pais;
    Set<Player> players;

    public Equipo() {
    }

    public Equipo(Long id, String nombre, String fechaCreacion, String pais, Set<Player> players) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.pais = pais;
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public String getFecha() {
        return fechaCreacion;
    }

    public void setFecha(String fecha) {
        this.fechaCreacion = fecha;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipo equipo = (Equipo) o;

        if (id != null ? !id.equals(equipo.id) : equipo.id != null) return false;
        if (nombre != null ? !nombre.equals(equipo.nombre) : equipo.nombre != null) return false;
        if (fechaCreacion != null ? !fechaCreacion.equals(equipo.fechaCreacion) : equipo.fechaCreacion != null)
            return false;
        if (pais != null ? !pais.equals(equipo.pais) : equipo.pais != null) return false;
        return !(players != null ? !players.equals(equipo.players) : equipo.players != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (players != null ? players.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", pais='" + pais + '\'' +
                ", players=" + players +
                '}';
    }
}
