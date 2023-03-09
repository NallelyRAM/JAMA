package interfaces;

import dominio.Paciente;
import java.util.ArrayList;

/**
 * Interfaz que representa a los pacientes.
 * @author Equipo 2 - JAMA.
 */
public interface IPacientesDAO {

    /**
     * Agrega un nuevo paciente
     *
     * @param paciente Paciente a agregar
     * @return true si lo agregó correctamente, false en caso contrario.
     */
    public boolean agregar(Paciente paciente);

    /**
     * Actualiza el paciente especificando el id del paciente a actualizar y
     * dando los nuevos datos del paciente.
     *
     * @param id EL id del paciente a actualizar.
     * @param paciente Los nuevos datos a sobreescribir del paciente.
     * @return True si lo actualizó correctamente, false en caso contrario.
     */
    public boolean actualizar(int id, Paciente paciente);

    /**
     * Elimina el paciente con el id dado como parametro.
     *
     * @param id El id del paciente a eliminar.
     * @return True si lo eliminó correctamente, false en caso contrario.
     */
    public boolean eliminar(int id);

    /**
     * Consulta por ID el paciente en la BD.
     *
     * 
     * @param id
     * @return El paciente si lo encontró, null en caso contrario.
     */
    public Paciente consultarPorID(int id);

    /**
     * Devuelve la lista de todos los pacientes.
     *
     * @return La lista de todos los pacientes.
     */
    public ArrayList<Paciente> consultarTodos();

    public Paciente consultarPorNombre(String nombre);
}
