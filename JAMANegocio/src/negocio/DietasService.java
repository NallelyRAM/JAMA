package negocio;

import Persistencia.Persistencia;
import dominio.Dieta;
import interfaces.IDietasService;
import interfaces.IPersistencia;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Esta clase implementa los métodos de IDietasService y aplica todas las reglas
 * de negocio.
 *
 * @author Equipo 2 - JAMA
 */
public class DietasService implements IDietasService {

    IPersistencia persistencia;

    public DietasService() throws SQLException {
        this.persistencia = Persistencia.getInstance();
    }

    @Override
    public boolean registrarDieta(Dieta dieta) {
        return persistencia.agregarDieta(dieta);

    }

    @Override
    public ArrayList<Dieta> buscarDietas() {
        return persistencia.buscarDietas();
    }

    @Override
    public Dieta buscarDietaPorId(int id) {
        return persistencia.consultarPorIDDieta(id);
    }

    @Override
    public boolean eliminarDieta(int id) {
        if (!validar(id)) {
            return persistencia.eliminarDieta(id);
        }
        return false;
    }

    @Override
    public boolean actualizarDieta(int id, Dieta dieta) {
        if (!validar(dieta.getIdDieta())) {
            return persistencia.actualizarDieta(id, dieta);
        }
        return false;
    }

    @Override
    public boolean validar(int id) {
        Dieta dieta = persistencia.consultarPorIDDieta(id);
        return dieta == null;
    }

    @Override
    public Dieta buscarDietaPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
