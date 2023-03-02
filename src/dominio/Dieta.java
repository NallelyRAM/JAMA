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
    public int idDieta;
    public Paciente nombrePaciente;
    public int numDieta;
    public Date fechaInicio;
    public Date fechaFinal;
    public int diaSemana;
    public Platillo categoria;

    public Dieta() {
    }

    public Dieta(int idDieta, Paciente nombrePaciente, int numDieta, Date fechaInicio, Date fechaFinal, int diaSemana, Platillo categoria) {
        this.idDieta = idDieta;
        this.nombrePaciente = nombrePaciente;
        this.numDieta = numDieta;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.diaSemana = diaSemana;
        this.categoria = categoria;
    }

    public int getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }

    public Paciente getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(Paciente nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public int getNumDieta() {
        return numDieta;
    }

    public void setNumDieta(int numDieta) {
        this.numDieta = numDieta;
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

    public Platillo getCategoria() {
        return categoria;
    }

    public void setCategoria(Platillo categoria) {
        this.categoria = categoria;
    }
    
    
}
