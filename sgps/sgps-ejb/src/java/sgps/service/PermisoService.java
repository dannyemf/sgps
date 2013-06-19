/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import sgps.dao.GenericoJPADAO;
import sgps.model.seguridad.Permiso;
import sgps.model.seguridad.QGrupo;
import sgps.model.seguridad.QPermiso;
import sgps.model.seguridad.Usuario;


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
    
    public List<Permiso> obtenerPermisos(Usuario usuario){
        
        
        QPermiso pe = QPermiso.permiso;
        QGrupo   gu = QGrupo.grupo;
        //QUsuario   us = QUsuario.usuario;
        
        JPAQuery q = newJpaQuery();        
        
        //Grupo.permisos 
        
        List<Permiso> permisos =  new ArrayList<Permiso>();
                
        try {
            permisos = q.from(pe).leftJoin(pe.grupos, gu).where(gu.in(usuario.getGrupos())).list(pe);
        } catch (Exception e) {
            System.out.println(e);
        }                
        
        return permisos;
    }

}
