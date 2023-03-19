/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author Usuario
 */
public class Desayuno extends Platillo{
    public String colacion;

    public Desayuno(String nombre, String ingredientes, String acompanante, int numCalorias, byte[] foto, String colacion) {
        super(nombre, ingredientes, acompanante, numCalorias, foto);
        this.colacion = colacion;
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
    
    
    
}
