package com.example.report_campus.model;

public class Reporte {

    private String titulo, reporte;

    private Reporte(){};

    public void Reporte(String titulo, String texto) {
        this.titulo = titulo;
        this.reporte = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String texto) {
        this.reporte = texto;
    }
}


