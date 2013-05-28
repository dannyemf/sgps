package sgps.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * EJB generico que implementa las opciones de altas, bajas, modificaciones y acceso por clave
 * para cualquier tipo de Entidad
 * @author ribadas
 * @param <T>
 */


public class GenericoJPADAO<T> implements GenericoDAOInterface<T> {

    @PersistenceContext(unitName = "sgps-ejbPU")
    protected  EntityManager em;


    @Override
    public T crear(T entidad) {
       em.persist(entidad); // Crea una nueva tupla en la BD con los datos de "entidad"
                            // -> ademas genera su ID
       return entidad;
    }

    @Override
    public T actualizar(T entidad) {
       return em.merge(entidad);   // Actualiza los datos de "entidad" en su correspondiente tupla BD
    }

    @Override
    public void eliminar(T entidad) {
        em.remove(em.merge(entidad));  // Actualiza y elimina
    }

    @Override
    public T buscarPorId(Object id) {
        Class<T> claseEntidad = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        // Identifica la clase real de las entidades gestionada por este objeto (T.class)
        return em.find(claseEntidad, id);
    }
    
    public List<T> buscarTodos(Class<T> clase) {        
        Query q = em.createQuery("SELECT object(p) FROM " + clase.getSimpleName() + " AS p");
        return q.getResultList();
    }
    
    /*public List<Producto> buscarTodos() {
        Query q = em.createQuery("SELECT object(p) FROM Producto AS p");
        return q.getResultList();
    }*/
}
