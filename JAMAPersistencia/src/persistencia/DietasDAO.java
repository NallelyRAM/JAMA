/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Cena;
import dominio.Comida;
import dominio.Desayuno;
import dominio.Dieta;
import dominio.Platillo;
import interfaces.IConexionBD;
import interfaces.IDietasDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author USER
 */
public class DietasDAO implements IDietasDAO {

    private final IConexionBD conexion;
    private Connection baseDatos;

    public DietasDAO(IConexionBD conexion)  {
        this.conexion = conexion;
        try {
            this.baseDatos = this.conexion.obtenerConexion();
        } catch (SQLException ex) {
            System.out.println("Error "+ex);
        }
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
            System.out.println(ex);
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
            System.out.println(ex);
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
                    try (PreparedStatement pstmtComida = baseDatos.prepareStatement(sqlComida, Statement.RETURN_GENERATED_KEYS)) {
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
            System.out.println(ex);
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
                    try (PreparedStatement pstmtCena = baseDatos.prepareStatement(sqlComida, Statement.RETURN_GENERATED_KEYS)) {
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
            System.out.println(ex);
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

        int idDesayuno = 0;
        int idComida = 0;
        int idCena = 0;

        int idPlatilloDesayuno = 0;
        int idPlatilloComida = 0;
        int idPlatilloCena = 0;

        try {
            // Deshabilitar el modo de autocommit para permitir la transacción
            baseDatos.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String mySqlDesayuno = "SELECT idPlatillo FROM desayuno WHERE idDesayuno = ?";
        String mySqlComida = "SELECT idPlatillo FROM comida WHERE idComida = ?";
        String mySqlCena = "SELECT idPlatillo FROM cena WHERE idCena = ?";
        try (PreparedStatement pstmtDesayuno = baseDatos.prepareStatement(mySqlDesayuno); PreparedStatement pstmtComida = baseDatos.prepareStatement(mySqlComida); PreparedStatement pstmtCena = baseDatos.prepareStatement(mySqlCena)) {
            pstmtDesayuno.setInt(1, dieta.getDesayuno().getId());
            pstmtComida.setInt(1, dieta.getComida().getId());
            pstmtCena.setInt(1, dieta.getCena().getId());
            try (ResultSet rsDesayuno = pstmtDesayuno.executeQuery(); ResultSet rsComida = pstmtComida.executeQuery(); ResultSet rsCena = pstmtCena.executeQuery()) {
                if (rsDesayuno.next()) {
                    idPlatilloDesayuno = rsDesayuno.getInt("idPlatillo");
                }
                if (rsComida.next()) {
                    idPlatilloComida = rsComida.getInt("idPlatillo");
                }
                if (rsCena.next()) {
                    idPlatilloCena = rsCena.getInt("idPlatillo");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en: " + ex);
        }

        // Actualizar un registro en la tabla platillo
        String sqlPlatilloDesayuno = "UPDATE platillo SET nombre=?, ingredientes=?, acompañante=?, numCalorias=?, foto=?  WHERE idPlatillo=?";
        try (PreparedStatement pstmtPlatillo = baseDatos.prepareStatement(sqlPlatilloDesayuno, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPlatillo.setString(1, dieta.getDesayuno().getNombre());
            pstmtPlatillo.setString(2, dieta.getDesayuno().getIngredientes());
            pstmtPlatillo.setString(3, dieta.getDesayuno().getAcompanante());
            pstmtPlatillo.setInt(4, dieta.getDesayuno().getNumCalorias());
            pstmtPlatillo.setBytes(5, dieta.getDesayuno().getFoto());

            pstmtPlatillo.setInt(6, idPlatilloDesayuno);
            pstmtPlatillo.executeUpdate();

            idDesayuno = dieta.getDesayuno().getId();

            String sqlDesayuno = "UPDATE desayuno SET colacion=?, idPlatillo=? WHERE idDesayuno=?";
            try (PreparedStatement pstmtDesayuno = baseDatos.prepareStatement(sqlDesayuno)) {
                pstmtDesayuno.setString(1, dieta.getDesayuno().getColacion());
                pstmtDesayuno.setInt(2, idPlatilloDesayuno);

                pstmtDesayuno.setInt(3, idDesayuno);
                pstmtDesayuno.executeUpdate();


            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            // Confirmar la transacción
            baseDatos.commit();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String sqlPlatilloComida = "UPDATE platillo SET nombre=?, ingredientes=?, acompañante=?, numCalorias=?, foto=?  WHERE idPlatillo=?";
        try (PreparedStatement pstmtPlatillo = baseDatos.prepareStatement(sqlPlatilloComida, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPlatillo.setString(1, dieta.getComida().getNombre());
            pstmtPlatillo.setString(2, dieta.getComida().getIngredientes());
            pstmtPlatillo.setString(3, dieta.getComida().getAcompanante());
            pstmtPlatillo.setInt(4, dieta.getComida().getNumCalorias());
            pstmtPlatillo.setBytes(5, dieta.getComida().getFoto());

            pstmtPlatillo.setInt(6, idPlatilloComida);
            pstmtPlatillo.executeUpdate();

            idComida = dieta.getComida().getId();
            String sqlComida = "UPDATE comida SET colacion=?, idPlatillo=? WHERE idComida=?";
            try (PreparedStatement pstmtComida = baseDatos.prepareStatement(sqlComida, Statement.RETURN_GENERATED_KEYS)) {
                pstmtComida.setString(1, dieta.getComida().getColacion());
                pstmtComida.setInt(2, idPlatilloComida);
                
                pstmtComida.setInt(3, idComida);
                pstmtComida.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            // Confirmar la transacción
            baseDatos.commit();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String sqlPlatilloCena = "UPDATE platillo SET nombre=?, ingredientes=?, acompañante=?, numCalorias=?, foto=? WHERE idPlatillo=?";
        try (PreparedStatement pstmtPlatillo = baseDatos.prepareStatement(sqlPlatilloCena, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPlatillo.setString(1, dieta.getCena().getNombre());
            pstmtPlatillo.setString(2, dieta.getCena().getIngredientes());
            pstmtPlatillo.setString(3, dieta.getCena().getAcompanante());
            pstmtPlatillo.setInt(4, dieta.getCena().getNumCalorias());
            pstmtPlatillo.setBytes(5, dieta.getCena().getFoto());

            pstmtPlatillo.setInt(6, idPlatilloCena);
            pstmtPlatillo.executeUpdate();

            
            idCena = dieta.getCena().getId();
            
            String sqlComida = "UPDATE cena SET idPlatillo=? WHERE idCena=?";
            try (PreparedStatement pstmtCena = baseDatos.prepareStatement(sqlComida, Statement.RETURN_GENERATED_KEYS)) {
                pstmtCena.setInt(1, idPlatilloCena);
                
                pstmtCena.setInt(2, idCena);
                pstmtCena.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {
            // Confirmar la transacción
            baseDatos.commit();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String sqlPersona = "UPDATE dieta SET nombre=?, fechaInicio=?, fechaFinal=?, diaSemana=?, idDesayuno=?, idComida=?, idCena=? WHERE idDieta=?";
        try (PreparedStatement pstmtPersona = baseDatos.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
            pstmtPersona.setString(1, dieta.getNombreDieta());
            pstmtPersona.setDate(2, new java.sql.Date(dieta.getFechaInicio().getTime()));
            pstmtPersona.setDate(3, new java.sql.Date(dieta.getFechaFinal().getTime()));
            pstmtPersona.setInt(4, dieta.getDiaSemana());
            pstmtPersona.setInt(5, idDesayuno);
            pstmtPersona.setInt(6, idComida);
            pstmtPersona.setInt(7, idCena);
            
            pstmtPersona.setInt(8, id);
            pstmtPersona.executeUpdate();
            baseDatos.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dieta consultarPorID(int id) {

        String query = "SELECT * FROM dieta WHERE idDieta = ?";

        try {
            PreparedStatement statement = baseDatos.prepareStatement(query);
            statement.setInt(1, id);
            // Ejecutar la consulta y obtener los resultados
            ResultSet resultado = statement.executeQuery();
            // Procesar los resultados
            while (resultado.next()) {
                // Obtener los valores de cada columna
                String nombreDieta = resultado.getString("nombre");
                Date fechaInicio = resultado.getDate("fechaInicio");
                Date fechaFinal = resultado.getDate("fechaFinal");
                int diaSemana = resultado.getInt("diaSemana");

                int idDesayuno = resultado.getInt("idDesayuno");
                int idComida = resultado.getInt("idComida");
                int idCena = resultado.getInt("idCena");

                String queryDesayuno = "SELECT * FROM desayuno WHERE idDesayuno = ?";

                PreparedStatement statementDesayuno = baseDatos.prepareStatement(queryDesayuno);
                statementDesayuno.setInt(1, idDesayuno);
                ResultSet resultadoDesayuno = statementDesayuno.executeQuery();
                // Procesar los resultados
                Desayuno desayuno = new Desayuno();
                int idPlatillo = 0;
                String colacion = "";
                while (resultadoDesayuno.next()) {
                    idPlatillo = resultadoDesayuno.getInt("idPlatillo");
                    colacion = resultadoDesayuno.getString("colacion");

                }

                queryDesayuno = "SELECT * FROM platillo WHERE idPlatillo = ?";

                PreparedStatement statementPlatillo = baseDatos.prepareStatement(queryDesayuno);
                statementPlatillo.setInt(1, idPlatillo);
                ResultSet resultadoPlatillos = statementPlatillo.executeQuery();
                // Procesar los resultados
                Platillo platillo = new Platillo();
                while (resultadoPlatillos.next()) {
                    String nombre = resultadoPlatillos.getString("nombre");
                    String ingredientes = resultadoPlatillos.getString("ingredientes");
                    String acompañante = resultadoPlatillos.getString("acompañante");
                    int numCalorias = resultadoPlatillos.getInt("numCalorias");
                    byte[] foto = resultadoPlatillos.getBytes("foto");
                    platillo = new Platillo(idPlatillo, nombre, ingredientes, acompañante, numCalorias, foto);
                }

                desayuno = new Desayuno(idDesayuno, platillo.getNombre(), platillo.getIngredientes(), platillo.getAcompanante(), platillo.getNumCalorias(),
                        platillo.getFoto(), colacion);

                String queryComida = "SELECT * FROM comida WHERE idComida = ?";

                PreparedStatement statementComida = baseDatos.prepareStatement(queryComida);
                statementComida.setInt(1, idComida);
                ResultSet resultadoComida = statementComida.executeQuery();
                // Procesar los resultados
                Comida comida = new Comida();
                idPlatillo = 0;
                colacion = "";
                while (resultadoComida.next()) {
                    idPlatillo = resultadoComida.getInt("idPlatillo");
                    colacion = resultadoComida.getString("colacion");

                }

                queryComida = "SELECT * FROM platillo WHERE idPlatillo = ?";

                PreparedStatement statementPlatilloComida = baseDatos.prepareStatement(queryComida);
                statementPlatilloComida.setInt(1, idPlatillo);
                ResultSet resultadoPlatillosComidas = statementPlatilloComida.executeQuery();
                // Procesar los resultados
                platillo = new Platillo();
                while (resultadoPlatillosComidas.next()) {
                    String nombre = resultadoPlatillosComidas.getString("nombre");
                    String ingredientes = resultadoPlatillosComidas.getString("ingredientes");
                    String acompañante = resultadoPlatillosComidas.getString("acompañante");
                    int numCalorias = resultadoPlatillosComidas.getInt("numCalorias");
                    byte[] foto = resultadoPlatillosComidas.getBytes("foto");
                    platillo = new Platillo(idPlatillo, nombre, ingredientes, acompañante, numCalorias, foto);
                }

                comida = new Comida(idComida, platillo.getNombre(), platillo.getIngredientes(), platillo.getAcompanante(), platillo.getNumCalorias(),
                        platillo.getFoto(), colacion);

                String queryCena = "SELECT * FROM cena WHERE idCena = ?";

                PreparedStatement statementCena = baseDatos.prepareStatement(queryCena);
                statementCena.setInt(1, idCena);
                ResultSet resultadoCena = statementCena.executeQuery();
                // Procesar los resultados
                Cena cena = new Cena();
                idPlatillo = 0;
                colacion = "";
                while (resultadoCena.next()) {
                    idPlatillo = resultadoCena.getInt("idPlatillo");
                }

                queryDesayuno = "SELECT * FROM platillo WHERE idPlatillo = ?";

                PreparedStatement statementPlatilloCena = baseDatos.prepareStatement(queryDesayuno);
                statementPlatilloCena.setInt(1, idPlatillo);
                ResultSet resultadoPlatillosCena = statementPlatilloCena.executeQuery();
                // Procesar los resultados
                platillo = new Platillo();
                while (resultadoPlatillosCena.next()) {
                    String nombre = resultadoPlatillosCena.getString("nombre");
                    String ingredientes = resultadoPlatillosCena.getString("ingredientes");
                    String acompañante = resultadoPlatillosCena.getString("acompañante");
                    int numCalorias = resultadoPlatillosCena.getInt("numCalorias");
                    byte[] foto = resultadoPlatillosCena.getBytes("foto");
                    platillo = new Platillo(idPlatillo, nombre, ingredientes, acompañante, numCalorias, foto);
                }

                cena = new Cena(idCena, platillo.getNombre(), platillo.getIngredientes(), platillo.getAcompanante(), platillo.getNumCalorias(),
                        platillo.getFoto());

                return new Dieta(nombreDieta, fechaInicio, fechaFinal, diaSemana, desayuno, comida, cena);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;

    }

    @Override
    public ArrayList<Dieta> consultarTodos() {

        ArrayList<Dieta> dietas = new ArrayList<>();

        String query = "SELECT * FROM dieta";

        try {
            PreparedStatement statement = baseDatos.prepareStatement(query);

            // Ejecutar la consulta y obtener los resultados
            ResultSet resultado = statement.executeQuery();
            // Procesar los resultados
            while (resultado.next()) {
                // Obtener los valores de cada columna
                int idDieta = resultado.getInt("idDieta");
                String nombreDieta = resultado.getString("nombre");
                Date fechaInicio = resultado.getDate("fechaInicio");
                Date fechaFinal = resultado.getDate("fechaFinal");
                int diaSemana = resultado.getInt("diaSemana");

                int idDesayuno = resultado.getInt("idDesayuno");
                int idComida = resultado.getInt("idComida");
                int idCena = resultado.getInt("idCena");

                String queryDesayuno = "SELECT * FROM desayuno WHERE idDesayuno = ?";

                PreparedStatement statementDesayuno = baseDatos.prepareStatement(queryDesayuno);
                statementDesayuno.setInt(1, idDesayuno);
                ResultSet resultadoDesayuno = statementDesayuno.executeQuery();
                // Procesar los resultados
                Desayuno desayuno = new Desayuno();
                int idPlatillo = 0;
                String colacion = "";
                while (resultadoDesayuno.next()) {
                    idPlatillo = resultadoDesayuno.getInt("idPlatillo");
                    colacion = resultadoDesayuno.getString("colacion");

                }

                queryDesayuno = "SELECT * FROM platillo WHERE idPlatillo = ?";

                PreparedStatement statementPlatillo = baseDatos.prepareStatement(queryDesayuno);
                statementPlatillo.setInt(1, idPlatillo);
                ResultSet resultadoPlatillos = statementPlatillo.executeQuery();
                // Procesar los resultados
                Platillo platillo = new Platillo();
                while (resultadoPlatillos.next()) {
                    String nombre = resultadoPlatillos.getString("nombre");
                    String ingredientes = resultadoPlatillos.getString("ingredientes");
                    String acompañante = resultadoPlatillos.getString("acompañante");
                    int numCalorias = resultadoPlatillos.getInt("numCalorias");
                    byte[] foto = resultadoPlatillos.getBytes("foto");
                    platillo = new Platillo(idPlatillo, nombre, ingredientes, acompañante, numCalorias, foto);
                }

                desayuno = new Desayuno(idDesayuno, platillo.getNombre(), platillo.getIngredientes(), platillo.getAcompanante(), platillo.getNumCalorias(),
                        platillo.getFoto(), colacion);

                String queryComida = "SELECT * FROM comida WHERE idComida = ?";

                PreparedStatement statementComida = baseDatos.prepareStatement(queryComida);
                statementComida.setInt(1, idComida);
                ResultSet resultadoComida = statementComida.executeQuery();
                // Procesar los resultados
                Comida comida = new Comida();
                idPlatillo = 0;
                colacion = "";
                while (resultadoComida.next()) {
                    idPlatillo = resultadoComida.getInt("idPlatillo");
                    colacion = resultadoComida.getString("colacion");
                }

                queryComida = "SELECT * FROM platillo WHERE idPlatillo = ?";

                PreparedStatement statementPlatilloComida = baseDatos.prepareStatement(queryComida);
                statementPlatilloComida.setInt(1, idPlatillo);
                ResultSet resultadoPlatillosComidas = statementPlatilloComida.executeQuery();
                // Procesar los resultados
                platillo = new Platillo();
                while (resultadoPlatillosComidas.next()) {
                    String nombre = resultadoPlatillosComidas.getString("nombre");
                    String ingredientes = resultadoPlatillosComidas.getString("ingredientes");
                    String acompañante = resultadoPlatillosComidas.getString("acompañante");
                    int numCalorias = resultadoPlatillosComidas.getInt("numCalorias");
                    byte[] foto = resultadoPlatillosComidas.getBytes("foto");
                    platillo = new Platillo(idPlatillo, nombre, ingredientes, acompañante, numCalorias, foto);
                }

                comida = new Comida(idComida, platillo.getNombre(), platillo.getIngredientes(), platillo.getAcompanante(), platillo.getNumCalorias(),
                        platillo.getFoto(), colacion);

                String queryCena = "SELECT * FROM cena WHERE idCena = ?";

                PreparedStatement statementCena = baseDatos.prepareStatement(queryCena);
                statementCena.setInt(1, idCena);
                ResultSet resultadoCena = statementCena.executeQuery();
                // Procesar los resultados
                Cena cena = new Cena();
                idPlatillo = 0;
                colacion = "";
                while (resultadoCena.next()) {
                    idPlatillo = resultadoCena.getInt("idPlatillo");
                }

                queryDesayuno = "SELECT * FROM platillo WHERE idPlatillo = ?";

                PreparedStatement statementPlatilloCena = baseDatos.prepareStatement(queryDesayuno);
                statementPlatilloCena.setInt(1, idPlatillo);
                ResultSet resultadoPlatillosCena = statementPlatilloCena.executeQuery();
                // Procesar los resultados
                platillo = new Platillo();
                while (resultadoPlatillosCena.next()) {
                    String nombre = resultadoPlatillosCena.getString("nombre");
                    String ingredientes = resultadoPlatillosCena.getString("ingredientes");
                    String acompañante = resultadoPlatillosCena.getString("acompañante");
                    int numCalorias = resultadoPlatillosCena.getInt("numCalorias");
                    byte[] foto = resultadoPlatillosCena.getBytes("foto");
                    platillo = new Platillo(idPlatillo, nombre, ingredientes, acompañante, numCalorias, foto);
                }

                cena = new Cena(idCena, platillo.getNombre(), platillo.getIngredientes(), platillo.getAcompanante(), platillo.getNumCalorias(),
                        platillo.getFoto());

                dietas.add(new Dieta(idDieta, nombreDieta, fechaInicio, fechaFinal, diaSemana, desayuno, comida, cena));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return dietas;
    }

    @Override
    public Dieta consultarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
