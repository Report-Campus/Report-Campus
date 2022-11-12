package com.example.report_campus.model;

public class Reporte {

    private String Titulo, Reporte;

    private Reporte(){};

    public Reporte(String titulo, String texto) {
        this.Titulo = titulo;
        this.Reporte = texto;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }

    public String getReporte() {
        return Reporte;
    }

    public void setReporte(String texto) {
        this.Reporte = texto;
    }
}


