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
public class Nutriologo extends Persona{
    public int cedulaProfesional;

    public Nutriologo() {
    }

    public Nutriologo(int cedulaProfesional, int idPersona, String nombre, String apellidos, int edad, String sexo, Date fechaNacimiento, String telefono, String email) {
        super(idPersona, nombre, apellidos, sexo, fechaNacimiento, telefono, email);
        this.cedulaProfesional = cedulaProfesional;
    }

    public int getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(int cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }


    
}
