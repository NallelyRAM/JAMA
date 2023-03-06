/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ldoar
 */
public class ConexionBD implements IConexionBD{
    final String CADENA_CONEXION = "jdbc:mysql://localhost/jama";
    final String USUARIO="root";
    final String CONTRASENIA="12345";
    
    @Override
    public Connection crearConexion() throws SQLException{
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASENIA);
        return conexion;
    }
}
