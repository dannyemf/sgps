/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import sgps.dao.GenericoJPADAO;
import sgps.model.seguridad.Grupo;
import sgps.model.seguridad.Menu;
import sgps.model.seguridad.Permiso;
import sgps.model.seguridad.QGrupo;
import sgps.model.seguridad.Usuario;
import sgps.view.model.ItemGrupo;


/**
 *
 * @author uti
 */
@Stateless
public class GrupoService extends GenericoJPADAO<Grupo> implements GrupoServiceLocal  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    
    @Override
    public List<Grupo> buscarPor(String textoBusqueda){
        
        JPAQuery q = newJpaQuery();
        QGrupo g = QGrupo.grupo;
        
        return
                
        q.from(g)
        .where(
                g.nombre.like(getLikeString(textoBusqueda))
                .or(g.descripcion.like(getLikeString(textoBusqueda)))
        )
        .orderBy(g.nombre.asc())
        .list(g);
        
    }
    
    
    
    @Override
    public List<ItemGrupo> obtenerGrupos(Usuario usuario){
        
        
        List<ItemGrupo> lst = new ArrayList<ItemGrupo>();
        
        List<Grupo> grupos = buscarTodos(Grupo.class);
        
        for (Iterator<Grupo> it = grupos.iterator(); it.hasNext();) {
            Grupo g = it.next();
            if(usuario.getGrupos().contains(g)){
                lst.add(new ItemGrupo(true, g));
            }else{
                lst.add(new ItemGrupo(false, g));
            }
        }
        
        return lst;
    }
    
    @Override
    public List<ItemGrupo> obtenerGrupos(Permiso permiso){
        
        
        List<ItemGrupo> lst = new ArrayList<ItemGrupo>();
        
        List<Grupo> grupos = buscarTodos(Grupo.class);
        
        for (Iterator<Grupo> it = grupos.iterator(); it.hasNext();) {
            Grupo g = it.next();
            if(permiso.getGrupos().contains(g)){
                lst.add(new ItemGrupo(true, g));
            }else{
                lst.add(new ItemGrupo(false, g));
            }
        }
        
        return lst;
    }
    
    @Override
    public List<ItemGrupo> obtenerGrupos(Menu menu){
        
        
        List<ItemGrupo> lst = new ArrayList<ItemGrupo>();
        
        List<Grupo> grupos = buscarTodos(Grupo.class);
        
        for (Iterator<Grupo> it = grupos.iterator(); it.hasNext();) {
            Grupo g = it.next();
            if(menu.getGrupos().contains(g)){
                lst.add(new ItemGrupo(true, g));
            }else{
                lst.add(new ItemGrupo(false, g));
            }
        }
        
        return lst;
    }
}
