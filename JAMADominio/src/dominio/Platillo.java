/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author Usuario
 */
public class Platillo {

    private int id;
    private String nombre;
    private String ingredientes;
    private String acompanante;
    private int numCalorias;
    private byte[] foto;

    public Platillo() {
    }

    public Platillo(String nombre, String ingredientes, String acompanante, int numCalorias, byte[] foto) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.acompanante = acompanante;
        this.numCalorias = numCalorias;
        this.foto = foto;
    }

    public Platillo(int id, String nombre, String ingredientes, String acompanante, int numCalorias, byte[] foto) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.acompanante = acompanante;
        this.numCalorias = numCalorias;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getAcompanante() {
        return acompanante;
    }

    public void setAcompanante(String acompanante) {
        this.acompanante = acompanante;
    }

    public int getNumCalorias() {
        return numCalorias;
    }

    public void setNumCalorias(int numCalorias) {
        this.numCalorias = numCalorias;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Platillo ["
                + "id=" + id
                + ", nombre=" + nombre
                + ", ingredientes=" + ingredientes
                + ", acompañante=" + acompanante
                + ", numCalorias=" + numCalorias
                + ", foto=" + foto
                + "]";
    }

}
