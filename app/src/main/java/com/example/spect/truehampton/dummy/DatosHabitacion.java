package com.example.spect.truehampton.dummy;

public class DatosHabitacion {

    private int imagen;
    private String datos;
    private String titulo;

    public DatosHabitacion(int imagen, String datos, String titulo) {
        this.imagen = imagen;
        this.datos = datos;
        this.titulo = titulo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
