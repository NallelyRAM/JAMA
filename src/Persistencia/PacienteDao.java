/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import dominio.Paciente;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ldoar
 */
public class PacienteDao {
    private IConexionBD conexion;

    public PacienteDao(IConexionBD conexion) {
        this.conexion = conexion;
    }
    
    public boolean agregar(Paciente paciente) {
         try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            
                    
            String codigoSQL = String.format("INSERT INTO pacientes(nombrePaciente, apellidosPaciente, edadPaciente, sexoPaciente) VALUES ('%s','%s','%s',%s);",
            paciente.getNombre(),
            paciente.getApellidos(),
            paciente.getEdad(),
            paciente.getSexo(),
            paciente.getFechaNacimiento(),
            paciente.getTelefono(),
            paciente.getEmail(),
            paciente.getPeso(),
            paciente.getEstatura(),
            paciente.getTalla());
            
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            if (registrosAfectados == 1) {
                System.out.println("Se a registrado exitosamente al paciente");
                return true;
            }
            else {System.out.println("No se pudo registar al paciente");
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

//    @Override
//    public boolean actualizar(Paciente paciente) {
//      try {
//            Connection conexion = this.conexion.crearConexion();
//            Statement comandoSQL = conexion.createStatement();
//            String codigoSQL = String.format("UPDATE especies SET nombre_vulgar='%s',"
//                    + "nombre_cientifico='%s', familia='%s', peligro_extincion=%s  "
//                    + " WHERE id_especie =%d;",
//                      especie.getNombreVulgar(),
//                      especie.getNombreCientifico(),
//                      especie.getFamilia(),
//                      especie.getPeligro_Extincion(),
//                      especie.getID());
//            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
//            conexion.close();
//            if (registrosAfectados == 1) {
//                System.out.println("La especie se actualizo exitosamente");
//                return true;
//            }
//            else {System.out.println("No se pudo actualizar la especie");
//                return true;
//            }
//            
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            return false;
//        }
//    }
}
