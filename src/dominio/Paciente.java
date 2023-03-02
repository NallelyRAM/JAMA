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
    public String motivoConsulta;
    public Dieta dieta;

    public Paciente() {
    }

    public Paciente(String motivoConsulta, Dieta dieta, int idPersona, String nombre, String apellidos, int edad, String sexo, Date fechaNacimiento, String telefono, String email) {
        super(idPersona, nombre, apellidos, edad, sexo, fechaNacimiento, telefono, email);
        this.motivoConsulta = motivoConsulta;
        this.dieta = dieta;
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
    
    
}
