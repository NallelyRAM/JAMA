package interfaces;

import dominio.Paciente;
import java.util.ArrayList;

/**
 *  Pacientes service que aplica todas las reglas de negocio.
 * @author Equipo 2 - JAMA
 */
public interface IPacientesService {

    public boolean registrarPaciente(Paciente paciente);

    public boolean eliminarPaciente(int id);
    
    public boolean actualizarPaciente(int id,Paciente paciente);
    
    public ArrayList<Paciente> buscarPacientes();

    public Paciente buscarPacientePorID(int id);

    public boolean validar(int id);
}
