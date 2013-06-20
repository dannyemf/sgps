package sgps.dao;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author desarrollador
 */

public interface GenericoDAOInterface<T> {
    String getLikeString(Object stringOrObject);
    EntityManager getEntityManager();     
    CriteriaBuilder getCriteriaBuilder();
    T crear(T entidad);
    T actualizar(T entidad);
    void detach(T entidad);
    void eliminar(T entidad);
    T buscarPorId(Object id);
    List<T> buscarTodos(Class<T> clase);
    List<T> buscarPor(String textoBusqueda);
    JPAQuery newJpaQuery();
}
