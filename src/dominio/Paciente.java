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
public class Paciente extends Persona{
    private String motivoConsulta;
    private Dieta dieta;
    private float peso;
    private float estatura;
    private float talla;

    public Paciente() {
    }
    
    public Paciente(int idPersona, String nombre, String apellidos, int edad, String sexo, Date fechaNacimiento, String telefono, String email) {
        super(idPersona, nombre, apellidos, edad, sexo, fechaNacimiento, telefono, email);

    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    
    public String getMotivoConsulta() {
        return motivoConsulta;
    }
    
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

   public void imprimir(){
       System.out.println(this.nombre + " " + this.apellidos + " " + this.sexo + " " + this.telefono + " " + this.email);
   }
    
    public void imprimirTodo(){
       System.out.println(this.nombre + " " + this.apellidos + " " + this.sexo + " " + this.telefono + " " + this.email + " " + this.peso+ " " + this.estatura + " " + this.talla);
   }
}   
