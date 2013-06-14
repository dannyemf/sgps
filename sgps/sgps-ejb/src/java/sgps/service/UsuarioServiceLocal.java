/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import javax.ejb.Local;
import sgps.dao.GenericoDAOInterface;
import sgps.model.seguridad.Usuario;

/**
 *
 * @author remoto
 */
@Local
public interface UsuarioServiceLocal extends GenericoDAOInterface<Usuario>{
    
    Usuario autenticar(String login, String clave);
    
}
