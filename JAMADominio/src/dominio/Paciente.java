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
public class Paciente extends Persona {

    
    private int idPaciente;
    private String motivoConsulta;
    private Dieta dieta;
    private float peso;
    private float estatura;
    private float talla;

    public Paciente() {
    }

    
    public Paciente(int idPersona, String nombre, String apellidos, String sexo, Date fechaNacimiento, String telefono, String email) {
        super(idPersona, nombre, apellidos, sexo, fechaNacimiento, telefono, email);
    }

    public Paciente(String motivoConsulta, Dieta dieta, float peso, float estatura, float talla) {
        this.motivoConsulta = motivoConsulta;
        this.dieta = dieta;
        this.peso = peso;
        this.estatura = estatura;
        this.talla = talla;
    }

    public Paciente(String motivoConsulta, Dieta dieta, float peso, float estatura, float talla, String nombre, String apellidos, int edad, String sexo, Date fechaNacimiento, String telefono, String email) {
        super(nombre, apellidos, sexo, fechaNacimiento, telefono, email);
        this.motivoConsulta = motivoConsulta;
        this.dieta = dieta;
        this.peso = peso;
        this.estatura = estatura;
        this.talla = talla;
    }

    public Paciente(int idPaciente, String motivoConsulta, Dieta dieta, float peso, float estatura, float talla) {
        this.idPaciente = idPaciente;
        this.motivoConsulta = motivoConsulta;
        this.dieta = dieta;
        this.peso = peso;
        this.estatura = estatura;
        this.talla = talla;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public void setEdad(int edad) {
        this.edad = edad;
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

    
    @Override
    public String toString() {
    return "Paciente{" +
            "idPersona=" + super.getIdPersona() +
            ", nombre='" + super.getNombre() + '\'' +
            ", apellidos='" + super.getApellidos() + '\'' +
            ", edad=" + super.getEdad() +
            ", sexo='" + super.getSexo() + '\'' +
            ", fechaNacimiento=" + super.getFechaNacimiento() +
            ", telefono='" + super.getTelefono() + '\'' +
            ", email='" + super.getEmail() + '\'' +
            "motivoConsulta='" + motivoConsulta + '\'' +
            ", dieta=" + dieta +
            ", peso=" + peso +
            ", estatura=" + estatura +
            ", talla=" + talla +
            '}';
}

    

    
}
