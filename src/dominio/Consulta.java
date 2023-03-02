/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Consulta {
    public Paciente paciente;
    public Nutriologo nutriologo;
    public int talla;
    public float altura;
    public float peso;
    public Boolean dieta;
    public  ArrayList<Consulta> Consulta;

    public Consulta() {
    }

    public Consulta(Paciente paciente, Nutriologo nutriologo, int talla, float altura, float peso, Boolean dieta, ArrayList<Consulta> Consulta) {
        this.paciente = paciente;
        this.nutriologo = nutriologo;
        this.talla = talla;
        this.altura = altura;
        this.peso = peso;
        this.dieta = dieta;
        this.Consulta = Consulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Nutriologo getNutriologo() {
        return nutriologo;
    }

    public void setNutriologo(Nutriologo nutriologo) {
        this.nutriologo = nutriologo;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Boolean getDieta() {
        return dieta;
    }

    public void setDieta(Boolean dieta) {
        this.dieta = dieta;
    }

    public ArrayList<Consulta> getConsulta() {
        return Consulta;
    }

    public void setConsulta(ArrayList<Consulta> Consulta) {
        this.Consulta = Consulta;
    }
    
    
}
