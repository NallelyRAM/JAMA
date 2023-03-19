package interfaces;

import dominio.Dieta;
import java.util.ArrayList;

/**
 * Dietas service que aplica todas las reglas de negocio.
 *
 * @author Equipo 2 - JAMA
 */
public interface IDietasService {

    public boolean registrarDieta(Dieta dieta);

    public boolean eliminarDieta(int id);

    public boolean actualizarDieta(int id, Dieta dieta);

    public ArrayList<Dieta> buscarDietas();

    public Dieta buscarDietaPorNombre(String dieta);

    public boolean validar(int id);

    public Dieta buscarDietaPorId(int id);
}
