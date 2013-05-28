/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import sgps.service.UsuarioServiceLocal;

/**
 *
 * @author remoto
 */

@Named
public class LoginController implements Serializable{
    
    @EJB
    private UsuarioServiceLocal usuarioService;    
    
    public void doLogin(){
        
    }
    
    public void doLogOut(){
        
    }
        
    
}
