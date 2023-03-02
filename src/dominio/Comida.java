/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author Usuario
 */
public class Comida extends Platillo{
    public String colacion;

    public Comida() {
    }

    public Comida(String colacion) {
        this.colacion = colacion;
    }

    public String getColacion() {
        return colacion;
    }

    public void setColacion(String colacion) {
        this.colacion = colacion;
    }
    
    
}
