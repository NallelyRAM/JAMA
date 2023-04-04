/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Dieta {

    private int idDieta;
    private String nombreDieta;
    private Date fechaInicio;
    private Date fechaFinal;
    private int diaSemana;
    private Desayuno desayuno;
    private Comida comida;
    private Cena cena;

    public Dieta() {
    }

    public Dieta(String nombreDieta, Date fechaInicio, Date fechaFinal, int diaSemana, Desayuno desayuno, Comida comida, Cena cena) {
        this.nombreDieta = nombreDieta;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.diaSemana = diaSemana;
        this.desayuno = desayuno;
        this.comida = comida;
        this.cena = cena;
    }
    
    
    public Dieta(int idDieta, String nombreDieta, Date fechaInicio, Date fechaFinal, int diaSemana, Desayuno desayuno, Comida comida, Cena cena) {
        this.nombreDieta = nombreDieta;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.diaSemana = diaSemana;
        this.desayuno = desayuno;
        this.comida = comida;
        this.cena = cena;
        this.idDieta = idDieta;
    }
    
    public String getNombreDieta() {
        return nombreDieta;
    }

    public void setNombreDieta(String nombreDieta) {
        this.nombreDieta = nombreDieta;
    }

    public int getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Desayuno getDesayuno() {
        return desayuno;
    }

    public void setDesayuno(Desayuno desayuno) {
        this.desayuno = desayuno;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public Cena getCena() {
        return cena;
    }

    public void setCena(Cena cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Dieta{"
                + "nombreDieta='" + nombreDieta + '\''
                + ", fechaInicio=" + fechaInicio
                + ", fechaFinal=" + fechaFinal
                + ", diaSemana=" + diaSemana
                + ",\n\t desayuno=" + desayuno.toString().replace("\n", "\n\t\t")
                + ",\n\t comida=" + comida.toString().replace("\n", "\n\t\t")
                + ",\n\t cena=" + cena.toString().replace("\n", "\n\t\t")
                + "}\n\n";
    }

}
