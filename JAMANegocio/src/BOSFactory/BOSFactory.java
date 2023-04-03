/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BOSFactory;

import interfaces.IDietasService;
import interfaces.IPacientesService;
import negocio.DietasService;
import negocio.PacientesService;

/**
 * Actúa como la fábrica de instancias.
 *
 * @author Equipo 2 - JAMA
 */
public class BOSFactory {

    public static IPacientesService crearPacientesService() {
        return new PacientesService();
    }

    public static IDietasService crearDietasService() {
        return new DietasService();
    }

    // ...
}
