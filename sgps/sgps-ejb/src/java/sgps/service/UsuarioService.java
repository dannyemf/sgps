/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.List;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import sgps.dao.GenericoJPADAO;
import sgps.model.seguridad.QUsuario;
import sgps.model.seguridad.Usuario;

/**
 *
 * @author remoto
 */
@Stateless
@Startup
public class UsuarioService extends GenericoJPADAO<Usuario> implements UsuarioServiceLocal {

    public UsuarioService() {
        
    }
    
    
    @Override
    public Usuario autenticar(String login, String clave){
        JPAQuery q = new JPAQuery(em);
        QUsuario us = QUsuario.usuario;
        
        return q.from(us).where(us.login.eq(login).and(us.clave.eq(clave))).uniqueResult(us);
    }

    
    @Override
    public List<Usuario> buscarPor(String textoBusqueda) {
        
        JPAQuery q = new JPAQuery(em);
        QUsuario us = QUsuario.usuario;
        
        return q.from(us).where(
                us.login.like(getLikeString(textoBusqueda))
                .or(us.descripcion.like(getLikeString(textoBusqueda))))
        .orderBy(us.descripcion.asc())
        .list(us);
    }
    
    
    

}
