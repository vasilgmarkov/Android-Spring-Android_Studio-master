package com.example.basketball.model;

/**
 * Created by Alfredo on 28/02/2016.
 */
public class Player {
    Long id;
    String name;
    Integer baskets;
    Integer rebotes;
    Integer asistencias;
    String posicionCampo;
    String fechaNacimiento;
    Equipo equipo;


    public Player() {
    }

    public Player(Long id, String name, Integer baskets, Integer rebotes, Integer asistencias, String posicionCampo, String fechaNacimiento, Equipo equipo) {
        this.id = id;
        this.name = name;
        this.baskets = baskets;
        this.rebotes = rebotes;
        this.asistencias = asistencias;
        this.posicionCampo = posicionCampo;
        this.fechaNacimiento = fechaNacimiento;
        this.equipo = equipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaskets() {
        return baskets;
    }

    public void setBaskets(Integer baskets) {
        this.baskets = baskets;
    }

    public Integer getRebotes() {
        return rebotes;
    }

    public void setRebotes(Integer rebotes) {
        this.rebotes = rebotes;
    }

    public Integer getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Integer asistencias) {
        this.asistencias = asistencias;
    }

    public String getPos() {
        return posicionCampo;
    }

    public void setPos(String pos) {
        this.posicionCampo = pos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != null ? !id.equals(player.id) : player.id != null) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (baskets != null ? !baskets.equals(player.baskets) : player.baskets != null)
            return false;
        if (rebotes != null ? !rebotes.equals(player.rebotes) : player.rebotes != null)
            return false;
        if (asistencias != null ? !asistencias.equals(player.asistencias) : player.asistencias != null)
            return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(player.fechaNacimiento) : player.fechaNacimiento != null)
            return false;
        return !(posicionCampo != null ? !posicionCampo.equals(player.posicionCampo) : player.posicionCampo != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (baskets != null ? baskets.hashCode() : 0);
        result = 31 * result + (rebotes != null ? rebotes.hashCode() : 0);
        result = 31 * result + (asistencias != null ? asistencias.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (posicionCampo != null ? posicionCampo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", baskets=" + baskets +
                ", rebotes=" + rebotes +
                ", asistencias=" + asistencias +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", posicionCampo='" + posicionCampo + '\'' +
                '}';
    }
}
