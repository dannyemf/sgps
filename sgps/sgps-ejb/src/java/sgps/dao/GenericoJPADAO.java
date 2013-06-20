package sgps.dao;

import com.mysema.query.jpa.impl.JPAQuery;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;


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
    public EntityManager getEntityManager(){
        return em;
    }
    
    public CriteriaBuilder getCriteriaBuilder(){
        return em.getCriteriaBuilder();
    }
    
    public String getLikeString(Object stringOrObject){
        if(stringOrObject == null){
            return "%";
        }else{
            return "%" + stringOrObject + "%";
        }
    }


    @Override
    public T crear(T entidad) {
        /*try {
            T m = em.merge(entidad);
        } catch (Exception e) {
            em.persist(entidad);
        }*/
        // Crea una nueva tupla en la BD con los datos de "entidad"
        // -> ademas genera su ID
        em.persist(entidad);
       return entidad;
    }

    @Override
    public T actualizar(T entidad) {        
       return em.merge(entidad);   // Actualiza los datos de "entidad" en su correspondiente tupla BD
    }
    
    
    public void detach(T entidad) {        
       em.detach(entidad);
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
    
    public JPAQuery newJpaQuery(){
        return new JPAQuery(em);
    }
    
    public List<T> buscarPor(String textoBusqueda){
        /*
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = b.createQuery(Usuario.class);
        return em.createQuery(criteria).getResultList();*/
        
        /*QUsuario q = QUsuario.usuario;        
        JPAQuery j = new JPAQuery(em);
        return j.from(q).list(q);*/
        
        
        /*
        CriteriaQuery<Usuario> criteria = b.createQuery(Usuario.class);
        Root<Usuario> usRoot = criteria.from( Usuario.class );
        
        criteria
        .select(usRoot)
        .where(
                b.or(
                    b.like(usRoot.get(Usuario_.login), textoBusqueda),
                    b.like(usRoot.get(Usuario_.descripcion), textoBusqueda)                    
                )
        ).orderBy(
                b.asc(usRoot.get(Usuario_.login))
        );
        
        List<Usuario> lista = em.createQuery(criteria).getResultList();
        
        return lista;*/
        
        return new ArrayList<T>();
    }
    
    /*public List<Producto> buscarTodos() {
        Query q = em.createQuery("SELECT object(p) FROM Producto AS p");
        return q.getResultList();
    }*/
}
