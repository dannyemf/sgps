/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import javax.ejb.Stateless;
import sgps.dao.GenericoJPADAO;
import sgps.model.Usuario;

/**
 *
 * @author remoto
 */
@Stateless
public class UsuarioService extends GenericoJPADAO<Usuario> implements UsuarioServiceLocal {

    public UsuarioService() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    

}
