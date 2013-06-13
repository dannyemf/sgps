/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import sgps.model.seguridad.Usuario;

/**
 * Clase para manejar el contexto de usuario, como usuario autenticado, ip, formatos, etc.
 * 
 * @author Danny Muñoz
 */

@Named
@SessionScoped
public class ContextBean implements Serializable{
    
    /**
     * Usuario autenticado
     */
    private Usuario usuario;

    public ContextBean() {
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
           
}
