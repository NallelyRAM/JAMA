/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Persona {

    public int idPersona;
    public String nombre;
    public String apellidos;
    public int edad;
    public String sexo;
    public Date fechaNacimiento;
    public String telefono;
    public String email;

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String apellidos, String sexo, Date fechaNacimiento, String telefono, String email) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.edad = calcularEdad();
    }

    public Persona(String nombre, String apellidos, String sexo, Date fechaNacimiento, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.edad = calcularEdad();
    }
    
    

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public final int calcularEdad() {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // Obtener la fecha de nacimiento de la persona
        LocalDate myFechaNacimiento = LocalDate.parse(formatoFecha.format(this.fechaNacimiento));

        // Calcular la edad
        int myEdad = Period.between(myFechaNacimiento, fechaActual).getYears();

        return myEdad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", sexo=" + sexo + ", fechaNacimiento=" + fechaNacimiento + ", telefono=" + telefono + ", email=" + email + '}';
    }

}
