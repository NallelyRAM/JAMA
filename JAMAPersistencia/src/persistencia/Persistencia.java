/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import dominio.Dieta;
import factory.DAOSFactory;
import dominio.Paciente;
import interfaces.IDietasDAO;
import interfaces.IPacientesDAO;
import interfaces.IPersistencia;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *  Clase Persistencia que se encarga de llamar a los métodos correspondientes.
 * @author Equipo 2 - JAMA
 */
public class Persistencia implements IPersistencia{
    
    IPacientesDAO pacientesDAO;
    IDietasDAO dietasDAO;
    
    
    private static Persistencia persistencia;
    
    public Persistencia() throws SQLException{
        pacientesDAO = DAOSFactory.crearPacientesDAO();
        dietasDAO = DAOSFactory.crearDietasDAO();
    }
    
    public static Persistencia getInstance() throws SQLException{
        if(persistencia == null) return new Persistencia();
        return persistencia;
    }
    
    // Pacientes ...
    @Override
    public boolean agregarPaciente(Paciente paciente) {
        return pacientesDAO.agregar(paciente);
    }

    @Override
    public boolean actualizarPaciente(int id, Paciente paciente) {
        return pacientesDAO.actualizar(id, paciente);
    }

    @Override
    public boolean eliminarPaciente(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paciente consultarPorIDPaciente(int id) {
        return pacientesDAO.consultarPorID(id);
    }

    @Override
    public ArrayList<Paciente> buscarPacientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paciente consultarPorNombrePaciente(String nombre) {
        return pacientesDAO.consultarPorNombre(nombre);
    }
    
    
    // Dietas ...
    @Override
    public boolean agregarDieta(Dieta dieta) {
        return dietasDAO.agregar(dieta);
    }

    @Override
    public boolean actualizarDieta(int id, Dieta dieta) {
        return dietasDAO.actualizar(id, dieta);
    }

    @Override
    public boolean eliminarDieta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dieta consultarPorIDDieta(int id) {
        return dietasDAO.consultarPorID(id);
    }

    @Override
    public ArrayList<Dieta> buscarDietas() {
        return dietasDAO.consultarTodos();
    }

    @Override
    public Dieta consultarPorNombreDieta(String nombre) {
        return dietasDAO.consultarPorNombre(nombre);
    }
    
}
