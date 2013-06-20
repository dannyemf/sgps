/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import sgps.model.seguridad.Usuario;
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
    
    @Inject
    private MenuBean menuCnt;
    
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
    public String iniciarSession(){
        
        Usuario us = service.autenticar(usuario, clave);
        
        if(us != null){
            
            context.init(us);
            menuCnt.init(us);
            
            return "sgps.hxtml?faces-redirect=true";
        }else{
            addErrorMessage(null, "Usuario no disponible");
        }
        
        return null;
    }
    
    /**
     * Cierra la sesión actual
     */
    public void cerrarSession(ActionEvent evento){
        
        //HttpSession sesion = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);        
        try {
            ExternalContext fc = FacesContext.getCurrentInstance().getExternalContext();
            
            fc.invalidateSession();
            fc.redirect(fc.getRequestContextPath() + "/login.jsf?faces-redirect=true");
        } catch (Exception e) {
        }
        
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
