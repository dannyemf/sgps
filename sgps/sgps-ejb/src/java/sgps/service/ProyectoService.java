/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.List;
import javax.ejb.Stateless;
import sgps.dao.GenericoJPADAO;
import sgps.model.proyecto.Proyecto;
import sgps.model.proyecto.QProyecto;
import sgps.model.seguridad.Permiso;
import sgps.model.seguridad.QPermiso;
import sgps.model.seguridad.QUsuario;
import sgps.model.seguridad.Usuario;

/**
 *
 * @author uti
 */
@Stateless
public class ProyectoService extends GenericoJPADAO<Proyecto> implements ProyectoServiceLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List<Proyecto> buscarPor(String textoBusqueda) {
        
        JPAQuery q = new JPAQuery(em);
        QProyecto p = QProyecto.proyecto;
        
        return q.from(p).where(p.nombre.like(getLikeString(textoBusqueda)).or(p.descripcion.like(getLikeString(textoBusqueda))))
        .orderBy(p.nombre.asc())
        .list(p);
    }
    
    @Override
    public List<Proyecto> obtenerProyectos(Usuario usuario){
        
        JPAQuery q = new JPAQuery(em);
        
        QProyecto p = QProyecto.proyecto;
        QUsuario m = QUsuario.usuario;
        
        return q.from(p).leftJoin(p.miembros, m).where(m.eq(usuario))
                .orderBy(p.nombre.asc())
                .list(p);        
    }

}
