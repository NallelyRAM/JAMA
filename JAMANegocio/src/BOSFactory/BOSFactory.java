/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BOSFactory;

import interfaces.IPacientesService;
import java.sql.SQLException;
import negocio.PacientesService;

/**
 *  Actúa como la fábrica de instancias.
 * @author Equipo 2 - JAMA
 */
public class BOSFactory {
    
    public static IPacientesService crearPacientesService() throws SQLException {
        return new PacientesService();
    }
    
    // ...
    
}
