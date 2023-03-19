package negocio;

import BOSFactory.BOSFactory;
import dominio.Dieta;
import dominio.Paciente;
import interfaces.IDietasService;
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
    IDietasService dietasBO;
    // ...
   
    private static PersistenciaFachada persistencia;
    

    public PersistenciaFachada() throws SQLException {
        pacientesBO = BOSFactory.crearPacientesService();
        dietasBO = BOSFactory.crearDietasService();
        // ...
    }

    public static IPersistenciaFachada getInstance() throws SQLException {
        if (persistencia == null) {
            return new PersistenciaFachada();
        }
        return persistencia;
    }

    // Pacientes ...
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
    
    // Dietas ...
    @Override
    public boolean registrarDieta(Dieta dieta) {
        return dietasBO.registrarDieta(dieta);
    }

    @Override
    public boolean eliminarDieta(int id) {
        return dietasBO.eliminarDieta(id);
    }

    @Override
    public boolean actualizarDieta(int id, Dieta dieta) {
        return dietasBO.actualizarDieta(id, dieta);
    }

    @Override
    public ArrayList<Dieta> buscarDietas() {
        return dietasBO.buscarDietas();
    }

    @Override
    public Dieta buscarDietaPorID(int id) {
        return dietasBO.buscarDietaPorId(id);
    }
    
    // Abajo se continúa con las consultas, platillos, etc...
    
}
