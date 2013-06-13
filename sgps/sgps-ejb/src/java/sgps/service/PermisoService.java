/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.List;
import javax.ejb.Stateless;
import sgps.dao.GenericoJPADAO;
import sgps.model.seguridad.Permiso;
import sgps.model.seguridad.QPermiso;

/**
 *
 * @author uti
 */
@Stateless
public class PermisoService extends GenericoJPADAO<Permiso> implements PermisoServiceLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List<Permiso> buscarPor(String textoBusqueda) {
        
        JPAQuery q = new JPAQuery(em);
        QPermiso p = QPermiso.permiso;
        
        return q.from(p).where(p.nombre.like(getLikeString(textoBusqueda)))
        .orderBy(p.nombre.asc())
        .list(p);
    }

}
