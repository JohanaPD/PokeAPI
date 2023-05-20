package org.infantaelena.modelo.entidades;

import java.util.Objects;

/**
 * Clase que representa a un pokemon
 * @author
 * @version 1.0
 * @date 24/04/2023
 *
 */
public class Pokemon {
    private String nombre;
    private TipoPokemon tipo;
    private int salud;
    private int defensa;
    private int velocidad;

    public Pokemon() {
        this("",TipoPokemon.NORMAL,1,1,1);
    }

    public Pokemon(String nombre, TipoPokemon tipo, int salud, int defensa, int velocidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.salud = salud;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    public Pokemon(String nombre) {

        this.nombre = nombre.toUpperCase().trim();
    }

    public Pokemon(String nombre, TipoPokemon tipo) {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public void setTipo(TipoPokemon tipo) {
        this.tipo = tipo;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public String toString() {
        return "Nombre:" +  nombre +
                ", de tipo:" + tipo +
                "\n, salud:" + salud +
                ", defensa:" + defensa +
                ",  y velocidad:" + velocidad +"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon)) return false;
        Pokemon pokemon = (Pokemon) o;
        return getNombre().equals(pokemon.getNombre()) && getTipo() == pokemon.getTipo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getTipo());
    }
}

