/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import sgps.model.Usuario;
import sgps.service.UsuarioServiceLocal;

/**
 *
 * @author remoto
 */

@Named
public class UsuarioController {
    
    @EJB
    private UsuarioServiceLocal usuarioService;
    
    public List<Usuario> getUsuarios(){
        return usuarioService.buscarTodos(Usuario.class);        
    }
    
}
