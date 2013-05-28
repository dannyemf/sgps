package sgps.dao;

import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author desarrollador
 */

public interface GenericoDAOInterface<T> {
    T crear(T entidad);
    T actualizar(T entidad);
    void eliminar(T entidad);
    T buscarPorId(Object id);
    List<T> buscarTodos(Class<T> clase);
}
