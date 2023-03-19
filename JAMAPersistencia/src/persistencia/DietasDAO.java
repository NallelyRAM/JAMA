/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Dieta;
import dominio.Paciente;
import interfaces.IConexionBD;
import interfaces.IDietasDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DietasDAO implements IDietasDAO {

    private final IConexionBD conexion;
    private final Connection baseDatos;

    public DietasDAO(IConexionBD conexion) throws SQLException {
        this.conexion = conexion;
        this.baseDatos = this.conexion.obtenerConexion();
    }

    @Override
    public boolean agregar(Dieta dieta) {

        int idDesayuno = 0;
        int idComida = 0;
        int idCena = 0;

        try {
            // Deshabilitar el modo de autocommit para permitir la transacción
            baseDatos.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Insertar un registro en la tabla platillo
        String sqlPlatilloDesayuno = "INSERT INTO platillo (nombre, ingredientes, acompañante, numCalorias, foto) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmtPlatillo = baseDatos.prepareStatement(sqlPlatilloDesayuno, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPlatillo.setString(1, dieta.getDesayuno().getNombre());
            pstmtPlatillo.setString(2, dieta.getDesayuno().getIngredientes());
            pstmtPlatillo.setString(3, dieta.getDesayuno().getAcompanante());
            pstmtPlatillo.setInt(4, dieta.getDesayuno().getNumCalorias());
            pstmtPlatillo.setBytes(5, dieta.getDesayuno().getFoto());
            pstmtPlatillo.executeUpdate();

            // Obtener el ID del registro de platillo recién creado
            try (ResultSet rs = pstmtPlatillo.getGeneratedKeys()) {
                if (rs.next()) {
                    idDesayuno = rs.getInt(1);
                    // Insertar un registro en la tabla desayuno con una referencia al registro de platillo recién creado
                    String sqlDesayuno = "INSERT INTO desayuno (colacion, idPlatillo) VALUES (?, ?)";
                    try (PreparedStatement pstmtDesayuno = baseDatos.prepareStatement(sqlDesayuno, Statement.RETURN_GENERATED_KEYS)) {
                        pstmtDesayuno.setString(1, dieta.getDesayuno().getColacion());
                        pstmtDesayuno.setInt(2, idDesayuno);
                        pstmtDesayuno.executeUpdate();
                        try (ResultSet rsDesayuno = pstmtDesayuno.getGeneratedKeys()) {
                            if (rsDesayuno.next()) {
                                idDesayuno = rsDesayuno.getInt(1);
                            }
                        }

                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            // Confirmar la transacción
            baseDatos.commit();

        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Insertar un registro en la tabla platillo
        String sqlPlatilloComida = "INSERT INTO platillo (nombre, ingredientes, acompañante, numCalorias, foto) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmtPlatillo = baseDatos.prepareStatement(sqlPlatilloComida, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPlatillo.setString(1, dieta.getComida().getNombre());
            pstmtPlatillo.setString(2, dieta.getComida().getIngredientes());
            pstmtPlatillo.setString(3, dieta.getComida().getAcompanante());
            pstmtPlatillo.setInt(4, dieta.getComida().getNumCalorias());
            pstmtPlatillo.setBytes(5, dieta.getComida().getFoto());
            pstmtPlatillo.executeUpdate();

            // Obtener el ID del registro de platillo recién creado
            try (ResultSet rs = pstmtPlatillo.getGeneratedKeys()) {
                if (rs.next()) {
                    idComida = rs.getInt(1);
                    // Insertar un registro en la tabla desayuno con una referencia al registro de platillo recién creado
                    String sqlComida = "INSERT INTO comida (colacion, idPlatillo) VALUES (?, ?)";
                    try (PreparedStatement pstmtComida = baseDatos.prepareStatement(sqlComida,Statement.RETURN_GENERATED_KEYS)) {
                        pstmtComida.setString(1, dieta.getComida().getColacion());
                        pstmtComida.setInt(2, idComida);
                        pstmtComida.executeUpdate();
                        
                        try (ResultSet rsComida = pstmtComida.getGeneratedKeys()) {
                            if (rsComida.next()) {
                                idComida = rsComida.getInt(1);
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            // Confirmar la transacción
            baseDatos.commit();

        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Insertar un registro en la tabla platillo
        String sqlPlatilloCena = "INSERT INTO platillo (nombre, ingredientes, acompañante, numCalorias, foto) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmtPlatillo = baseDatos.prepareStatement(sqlPlatilloCena, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPlatillo.setString(1, dieta.getCena().getNombre());
            pstmtPlatillo.setString(2, dieta.getCena().getIngredientes());
            pstmtPlatillo.setString(3, dieta.getCena().getAcompanante());
            pstmtPlatillo.setInt(4, dieta.getCena().getNumCalorias());
            pstmtPlatillo.setBytes(5, dieta.getCena().getFoto());
            pstmtPlatillo.executeUpdate();

            // Obtener el ID del registro de platillo recién creado
            try (ResultSet rs = pstmtPlatillo.getGeneratedKeys()) {
                if (rs.next()) {
                    idCena = rs.getInt(1);
                    // Insertar un registro en la tabla desayuno con una referencia al registro de platillo recién creado
                    String sqlComida = "INSERT INTO cena (idPlatillo) VALUES (?)";
                    try (PreparedStatement pstmtCena = baseDatos.prepareStatement(sqlComida,Statement.RETURN_GENERATED_KEYS)) {
                        pstmtCena.setInt(1, idCena);
                        pstmtCena.executeUpdate();
                        try (ResultSet rsCena = pstmtCena.getGeneratedKeys()) {
                            if (rsCena.next()) {
                                idCena = rsCena.getInt(1);
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            // Confirmar la transacción
            baseDatos.commit();

        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sqlPersona = "INSERT INTO dieta (nombre,fechaInicio, fechaFinal, diaSemana, idDesayuno, idComida, idCena) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmtPersona = baseDatos.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPersona.setString(1, dieta.getNombreDieta());
            pstmtPersona.setDate(2, new java.sql.Date(dieta.getFechaInicio().getTime()));
            pstmtPersona.setDate(3, new java.sql.Date(dieta.getFechaFinal().getTime()));
            pstmtPersona.setInt(4, dieta.getDiaSemana());
            System.out.println(idDesayuno);
            pstmtPersona.setInt(5, idDesayuno);
            pstmtPersona.setInt(6, idComida);
            pstmtPersona.setInt(7, idCena);
            pstmtPersona.executeUpdate();
            baseDatos.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    @Override
    public boolean actualizar(int id, Dieta dieta) {
        try {
            // Deshabilitar el modo de autocommit para permitir la transacción
            baseDatos.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Actualizar un registro en la tabla persona
        try {
            String consultaPaciente = "UPDATE Paciente SET motivoConsulta = ? WHERE idPaciente = ?";
            PreparedStatement sentenciaPaciente = baseDatos.prepareStatement(consultaPaciente);
            sentenciaPaciente.setString(1, paciente.getMotivoConsulta());
            sentenciaPaciente.setInt(2, id);

            sentenciaPaciente.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String consultaPersona = "UPDATE Persona SET nombre = ?, edad = ?, sexo = ?, fechaNacimiento = ?, email = ?, telefono = ? WHERE idPersona = ?";
            PreparedStatement sentenciaPersona = baseDatos.prepareStatement(consultaPersona);
            sentenciaPersona.setString(1, paciente.getNombre());
            sentenciaPersona.setInt(2, paciente.getEdad());
            sentenciaPersona.setString(3, paciente.getSexo());
            sentenciaPersona.setDate(4, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            sentenciaPersona.setString(5, paciente.getEmail());
            sentenciaPersona.setString(6, paciente.getTelefono());
            sentenciaPersona.setInt(7, id);
            sentenciaPersona.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Confirmar la transacción
            baseDatos.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dieta consultarPorID(int id) {
        Paciente paciente = null;
        String sql = "SELECT p.idPersona, p.nombre, p.sexo, p.fechaNacimiento, p.telefono, p.email, "
                + "pa.idPaciente, pa.motivoConsulta "
                + "FROM Persona p "
                + "JOIN Paciente pa ON p.idPersona = pa.idPersona "
                + "WHERE pa.idPaciente = ?;";
        try {
            PreparedStatement pstmt = baseDatos.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                paciente = new Paciente();
                paciente.setIdPersona(rs.getInt("idPersona"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEmail(rs.getString("email"));
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setMotivoConsulta(rs.getString("motivoConsulta"));
//                paciente.setPeso(rs.getFloat("peso"));
//                paciente.setEstatura(rs.getFloat("estatura"));
//                paciente.setTalla(rs.getFloat("talla"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paciente;
    }

    @Override
    public ArrayList<Dieta> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dieta consultarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
