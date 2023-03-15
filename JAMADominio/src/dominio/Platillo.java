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
    public String nombre;
    public String ingredientes;
    public String acompanante;
    public float numCalorias;
    public byte foto;

    public Platillo() {
    }
    
    //CONSTRUCTOR CON TODAS LAS VARIABLES
    public Platillo(String nombre, String ingredientes, String acompanante, float numCalorias, byte foto) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.acompanante = acompanante;
        this.numCalorias = numCalorias;
        this.foto = foto;
    }
    
    //GET Y SET DE FOTO
    public byte getFoto() {    
        return foto;
    }
    public void setFoto(byte foto) {
        this.foto = foto;
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

    public float getNumCalorias() {
        return numCalorias;
    }

    public void setNumCalorias(float numCalorias) {
        this.numCalorias = numCalorias;
    }
    
    
    
}
