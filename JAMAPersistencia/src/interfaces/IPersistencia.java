package interfaces;

import dominio.Dieta;
import dominio.Paciente;
import java.util.ArrayList;

/**
 * Persistencia que permite a la fachada interactuar con el backend.
 *
 * @author Equipo 2 - JAMA
 */
public interface IPersistencia {

    // Paciente...
    public boolean agregarPaciente(Paciente paciente);

    public boolean actualizarPaciente(int id, Paciente paciente);

    public boolean eliminarPaciente(int id);

    public Paciente consultarPorIDPaciente(int id);

    public ArrayList<Paciente> buscarPacientes();
    
    public Paciente consultarPorNombrePaciente(String nombre);
    
    // Dietas...
    public boolean agregarDieta(Dieta dieta);

    public boolean actualizarDieta(int id, Dieta dieta);

    public boolean eliminarDieta(int id);

    public Dieta consultarPorIDDieta(int id);

    public ArrayList<Dieta> buscarDietas();
    
    public Dieta consultarPorNombreDieta(String nombre);
    
    
    // Platillos...
}
