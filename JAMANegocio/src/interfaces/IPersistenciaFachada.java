/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dominio.Paciente;
import java.util.ArrayList;

/**
 *  
 * @author Equipo 2 - JAMA
 */
public interface IPersistenciaFachada {
    
    
    // Pacientes  .........
    public boolean registrarPaciente(Paciente paciente);
    
    public boolean eliminarPaciente(int id);
    
    public boolean actualizarPaciente(int id, Paciente paciente);

    public ArrayList<Paciente> buscarPacientes();

    public Paciente buscarPacientePorID(int id);
    
    // ...
    
}
