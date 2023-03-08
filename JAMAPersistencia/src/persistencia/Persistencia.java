/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import factory.DAOSFactory;
import dominio.Paciente;
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
    
    private static Persistencia persistencia;
    
    public Persistencia() throws SQLException{
        pacientesDAO = DAOSFactory.crearPacientesDAO();
    }
    
    public static Persistencia getInstance() throws SQLException{
        if(persistencia == null) return new Persistencia();
        return persistencia;
    }
    
    @Override
    public boolean agregarPaciente(Paciente paciente) {
        return pacientesDAO.agregar(paciente);
    }

    @Override
    public boolean actualizarPaciente(int id, Paciente paciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarPaciente(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paciente consultarPorID(int id) {
        return pacientesDAO.consultarPorID(id);
    }

    @Override
    public ArrayList<Paciente> buscarPacientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
