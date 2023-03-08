/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import interfaces.IConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ldoar
 */
public class ConexionBD implements IConexionBD {

    private static final String CADENA_CONEXION = "jdbc:mysql://localhost/jama";
    private static final String USUARIO = "root";
    private static final String CONTRASENIA = "12345";
    private static Connection conexion;
    private static ConexionBD conexionBD;

    @Override
    public Connection crearConexion() throws SQLException {
        return DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASENIA);
    }

    @Override
    public Connection obtenerConexion() throws SQLException {
        if (conexion != null) {
            return conexion;
        }
        return crearConexion();
    }

    public static ConexionBD getInstance() {
        if (conexionBD == null) {
            return new ConexionBD();
        }
        return conexionBD;
    }
}
