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
import sgps.model.seguridad.Menu;
import sgps.model.seguridad.QGrupo;
import sgps.model.seguridad.QMenu;
import sgps.model.seguridad.Usuario;

/**
 *
 * @author uti
 */
@Stateless
public class MenuService extends GenericoJPADAO<Menu> implements MenuServiceLocal  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List<Menu> obtenerMenus(Usuario usuario){
        
        
        QMenu me = QMenu.menu;
        QGrupo   gr = QGrupo.grupo;        
        
        JPAQuery q = newJpaQuery();        
        
        List<Menu> menus =  new ArrayList<Menu>();
                
        try {
            menus = q.from(me).leftJoin(me.grupos, gr)
                    .where(gr.in(usuario.getGrupos()).and(me.padre.isNull()))
                    .orderBy(me.orden.asc()).list(me);
        } catch (Exception e) {
            System.out.println(e);
        }                
        
        return menus;
    }
    
    @Override
    public List<Menu> obtenerMenus(Usuario usuario, Menu padre){
        
        
        QMenu me = QMenu.menu;
        QGrupo   gr = QGrupo.grupo;        
        
        JPAQuery q = newJpaQuery();        
        
        List<Menu> menus =  new ArrayList<Menu>();
                
        try {
            menus = q.from(me).leftJoin(me.grupos, gr)
                    .where(gr.in(usuario.getGrupos()).and(me.padre.eq(padre)))
                    .orderBy(me.orden.asc()).list(me);
        } catch (Exception e) {
            System.out.println(e);
        }                
        
        return menus;
    }
    
    @Override
    public List<Menu> buscarPor(String textoBusqueda) {
        
        JPAQuery q = new JPAQuery(em);
        QMenu m = QMenu.menu;        
        
        return q.from(m)
                .where(m.etiqueta.like(getLikeString(textoBusqueda)).or(m.control.like(getLikeString(textoBusqueda))))
                .orderBy(m.orden.asc())
        .list(m);
    }

}
