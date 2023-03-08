/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Consulta {

    private Paciente paciente;
    private Nutriologo nutriologo;
    private int talla;
    private float altura;
    private float peso;
    private Boolean dieta;
    private ArrayList<Consulta> Consulta;
    private Date fecha;

    public Consulta() {
    }

    public Consulta(Paciente paciente, Nutriologo nutriologo, int talla, float altura, float peso, Boolean dieta, ArrayList<Consulta> Consulta, Date fecha) {
        this.paciente = paciente;
        this.nutriologo = nutriologo;
        this.talla = talla;
        this.altura = altura;
        this.peso = peso;
        this.dieta = dieta;
        this.Consulta = Consulta;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    @Override
    public String toString() {
        return "Consulta{" + "paciente=" + paciente + ", nutriologo=" + nutriologo + ", talla=" + talla + ", altura=" + altura + ", peso=" + peso + ", dieta=" + dieta + ", Consulta=" + Consulta + ", fecha=" + fecha + '}';
    }

}
