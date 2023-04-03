/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author Usuario
 */
public class Cena extends Platillo{

    private int id;
    
    public Cena() {
    }

    public Cena(int id, String nombre, String ingredientes, String acompanante, int numCalorias, byte[] foto) {
        super(nombre, ingredientes, acompanante, numCalorias, foto);
        this.id = id;
    }
    
    public Cena(String nombre, String ingredientes, String acompanante, int numCalorias, byte[] foto) {
        super(nombre, ingredientes, acompanante, numCalorias, foto);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return "Cena ["
                + "nombre=" + getNombre()
                + ", ingredientes=" + getIngredientes()
                + ", acompañante=" + getAcompanante()
                + ", numCalorias=" + getNumCalorias()
                + ", foto=" + getFoto()
                + "]";
    }
}
