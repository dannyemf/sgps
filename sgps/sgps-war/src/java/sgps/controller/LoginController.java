/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sgps.service.UsuarioServiceLocal;

/**
 * Controlador de inicio y cierre de sesión
 * 
 * @author Danny Muñoz
 */

@Named
@ConversationScoped
public class LoginController extends Controller{
    
    @EJB
    private UsuarioServiceLocal service;   
    
    @Inject
    private ContextBean context;
    
    /**
     * Nombre de usuario
     */
    private String usuario;
    
    /**
     * Clave del usuario
     */
    private String clave;
    
    /**
     * Inicia la sesión con los datos indicados
     */
    public void iniciarSession(){
        
    }
    
    /**
     * Cierra la sesión actual
     */
    public void cerrarSession(){
        
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
        
    
}
