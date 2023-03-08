package negocio;

import BOSFactory.BOSFactory;
import dominio.Paciente;
import interfaces.IPacientesService;
import interfaces.IPersistenciaFachada;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Equipo 2 - JAMA
 */
public class PersistenciaFachada implements IPersistenciaFachada{

    IPacientesService pacientesBO;
    // ...
   
    private static PersistenciaFachada persistencia;

    public PersistenciaFachada() throws SQLException {
        pacientesBO = BOSFactory.crearPacientesService();
        // ...
    }

    public static PersistenciaFachada getInstance() throws SQLException {
        if (persistencia == null) {
            return new PersistenciaFachada();
        }
        return persistencia;
    }

    @Override
    public boolean registrarPaciente(Paciente paciente) {
        return pacientesBO.registrarPaciente(paciente);
    }

    @Override
    public boolean eliminarPaciente(int id) {
        return pacientesBO.eliminarPaciente(id);
    }

    @Override
    public boolean actualizarPaciente(int id, Paciente paciente) {
        return pacientesBO.actualizarPaciente(id, paciente);
    }
    
    @Override
    public ArrayList<Paciente> buscarPacientes() {
        return pacientesBO.buscarPacientes();
    }

    @Override
    public Paciente buscarPacientePorID(int id) {
        return pacientesBO.buscarPacientePorID(id);
    }
    
    // Abajo se continúa con la dieta, consultas, platillos, etc...

    
    
}
