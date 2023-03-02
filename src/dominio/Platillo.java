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
    
    //FALTA CONSTRUCTOR CON TODAS LAS VARIABLES

    //NO HAY GET Y SET DE FOTO 
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
