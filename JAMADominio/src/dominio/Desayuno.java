/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;


/**
 *
 * @author Usuario
 */
public class Desayuno extends Platillo {

    private String colacion;
    private int id;

    public Desayuno(String nombre, String ingredientes, String acompanante, int numCalorias, byte[] foto, String colacion) {
        super(nombre, ingredientes, acompanante, numCalorias, foto);
        this.colacion = colacion;
    }
    
    public Desayuno(int id, String nombre, String ingredientes, String acompanante, int numCalorias, byte[] foto, String colacion) {
        super(nombre, ingredientes, acompanante, numCalorias, foto);
        this.colacion = colacion;
        this.id = id;
    }

    public Desayuno() {
    }

    public Desayuno(String colacion) {
        this.colacion = colacion;
    }

    public String getColacion() {
        return colacion;
    }

    public void setColacion(String colacion) {
        this.colacion = colacion;
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
        return "Desayuno ["
                + "nombre=" + getNombre()
                + ", ingredientes=" + getIngredientes()
                + ", acompañante=" + getAcompanante()
                + ", numCalorias=" + getNumCalorias()
                + ", foto=" + getFoto()
                + ", colacion=" + colacion
                + "]";
    }

}
