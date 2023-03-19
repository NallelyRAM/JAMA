/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dominio.Dieta;
import java.util.ArrayList;

/**
 * Interfaz que representa a las dietas.
 *
 * @author Equipo 2 - JAMA.
 */
public interface IDietasDAO {

    /**
     * Agrega una nueva dieta al catalogo de dietas.
     *
     * @param dieta Dieta a agregar al catalogo de dietas.
     * @return true si lo agregó correctamente, false en caso contrario.
     */
    public boolean agregar(Dieta dieta);

    /**
     * Actualiza la dieta especificando el id de la dieta a actualizar y dando
     * los nuevos datos de la dieta.
     *
     * @param id EL id de la dieta a actualizar.
     * @param dieta Los nuevos datos a sobreescribir de la dieta.
     * @return True si lo actualizó correctamente, false en caso contrario.
     */
    public boolean actualizar(int id, Dieta dieta);

    /**
     * Elimina la dieta con el id dado como parametro.
     *
     * @param id El id de la dieta a eliminar.
     * @return True si lo eliminó correctamente, false en caso contrario.
     */
    public boolean eliminar(int id);

    /**
     * Consulta por ID la dieta en la BD.
     *
     *
     * @param id El id de la dieta a consultar
     * @return La dieta si lo encontró, null en caso contrario.
     */
    public Dieta consultarPorID(int id);

    /**
     * Devuelve la lista de todas las dietas.
     *
     * @return La lista de todas las dietas.
     */
    public ArrayList<Dieta> consultarTodos();

    /**
     * Consulta por nombre la dieta dada como parametro
     *
     * @param nombre El nombre de la dieta a buscar dada como parametro
     * @return La dieta encontrada, null en caso contrario.
     */
    public Dieta consultarPorNombre(String nombre);
}
