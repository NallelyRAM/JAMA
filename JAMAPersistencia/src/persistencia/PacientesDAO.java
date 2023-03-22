package Persistencia;

import dominio.Paciente;
import interfaces.IConexionBD;
import interfaces.IPacientesDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase implementa los métodos de IPacientesDAO.
 *
 * @author Equipo 2 - JAMA.
 */
public class PacientesDAO implements IPacientesDAO {

    private final IConexionBD conexion;
    private final Connection baseDatos;

    public PacientesDAO(IConexionBD conexion) throws SQLException {
        this.conexion = conexion;
        this.baseDatos = this.conexion.obtenerConexion();
    }

    @Override
    public boolean agregar(Paciente paciente) {

        try {
            // Deshabilitar el modo de autocommit para permitir la transacción
            baseDatos.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Insertar un registro en la tabla persona
        String sqlPersona = "INSERT INTO persona (nombre, edad, sexo, fechaNacimiento, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmtPersona = baseDatos.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPersona.setString(1, paciente.getNombre() + " " + paciente.getApellidos());
            pstmtPersona.setInt(2, paciente.getEdad());
            pstmtPersona.setString(3, paciente.getSexo());
            pstmtPersona.setDate(4, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            pstmtPersona.setString(5, paciente.getTelefono());
            pstmtPersona.setString(6, paciente.getEmail());
            pstmtPersona.executeUpdate();

            int idDieta = paciente.getDieta() != null ? paciente.getDieta().getIdDieta() : 0;

            // Obtener el ID del registro de persona recién creado
            try (ResultSet rs = pstmtPersona.getGeneratedKeys()) {
                if (rs.next()) {
                    int idPersona = rs.getInt(1);
                    // Insertar un registro en la tabla paciente con una referencia al registro de persona recién creado
                    String sqlPaciente = "INSERT INTO paciente (motivoConsulta, idDieta, idPersona) VALUES (?, ?, ?)";
                    try (PreparedStatement pstmtPaciente = baseDatos.prepareStatement(sqlPaciente)) {
                        pstmtPaciente.setString(1, paciente.getMotivoConsulta());
                        pstmtPaciente.setObject(2, idDieta != 0 ? idDieta : null);
                        pstmtPaciente.setInt(3, idPersona);
                        pstmtPaciente.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            // Confirmar la transacción
            baseDatos.commit();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public boolean actualizar(int id, Paciente paciente) {
        try {
            // Deshabilitar el modo de autocommit para permitir la transacción
            baseDatos.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Actualizar un registro en la tabla persona
        try {
            String consultaPaciente = "UPDATE Paciente SET motivoConsulta = ?, idDieta = ? WHERE idPaciente = ?";
            PreparedStatement sentenciaPaciente = baseDatos.prepareStatement(consultaPaciente);
            sentenciaPaciente.setString(1, paciente.getMotivoConsulta());
            sentenciaPaciente.setInt(2, paciente.getDieta().getIdDieta());
            sentenciaPaciente.setInt(3, id);

            sentenciaPaciente.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Confirmar la transacción
            baseDatos.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paciente consultarPorID(int id) {
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
            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paciente;
    }

    @Override
    public ArrayList<Paciente> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paciente consultarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
