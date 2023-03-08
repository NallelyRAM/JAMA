/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import Persistencia.ConexionBD;
import Persistencia.PacientesDAO;
import interfaces.IConexionBD;
import interfaces.IPacientesDAO;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class DAOSFactory {
    
    private static final IConexionBD CONEXIONBD = ConexionBD.getInstance();
    
    public static IPacientesDAO crearPacientesDAO() throws SQLException{
        return new PacientesDAO(CONEXIONBD);
    }
    
    // ...
}
