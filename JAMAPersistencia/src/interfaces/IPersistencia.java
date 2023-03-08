package interfaces;

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

    public Paciente consultarPorID(int id);

    public ArrayList<Paciente> buscarPacientes();

    // Dietas...
    // Platillos...
}
