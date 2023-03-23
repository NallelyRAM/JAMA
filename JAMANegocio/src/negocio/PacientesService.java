package negocio;

import Persistencia.Persistencia;
import dominio.Paciente;
import interfaces.IPacientesService;
import interfaces.IPersistencia;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Esta clase implementa los métodos de IPacientesService y aplica todas las
 * reglas de negocio.
 *
 * @author Equipo 2 - JAMA
 */
public class PacientesService implements IPacientesService {

    IPersistencia persistencia;

    public PacientesService() throws SQLException {
        this.persistencia = Persistencia.getInstance();
    }

    @Override
    public boolean registrarPaciente(Paciente paciente) {
        if (validar(paciente.getIdPersona())) {
            persistencia.agregarPaciente(paciente);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Paciente> buscarPacientes() {
        return persistencia.buscarPacientes();
    }

    @Override
    public Paciente buscarPacientePorID(int id) {
        return persistencia.consultarPorIDPaciente(id);
    }

    

    @Override
    public boolean eliminarPaciente(int id) {
        if(!validar(id)){
            return persistencia.eliminarPaciente(id);
        }
        return false;
    }

    @Override
    public boolean actualizarPaciente(int id,Paciente paciente) {
        if(!validar(id)){
            return persistencia.actualizarPaciente(id, paciente);
        }
        return false;
    }
    
    
    @Override
    public boolean validar(int id) {
        Paciente paciente = persistencia.consultarPorIDPaciente(id);
        return paciente == null;
    }

    @Override
    public Paciente buscarPacientePorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
