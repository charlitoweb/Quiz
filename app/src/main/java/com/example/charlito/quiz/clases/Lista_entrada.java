package com.example.charlito.quiz.clases;

/**
 * Created by Charlito on 23/10/2014.
 */
public class Lista_entrada {
    private String usuario;
    private String puntuacion;

    public Lista_entrada (String usuario, String puntuacion) {
        this.usuario = usuario;
        this.puntuacion = puntuacion;
    }

    public String get_usuario() {
        return usuario;
    }

    public String get_puntuacion() {
        return puntuacion;
    }
}