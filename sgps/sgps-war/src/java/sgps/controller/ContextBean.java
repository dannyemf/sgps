/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import sgps.model.seguridad.Permiso;
import sgps.model.seguridad.Usuario;
import sgps.service.PermisoServiceLocal;

/**
 * Clase para manejar el contexto de usuario, como usuario autenticado, ip, formatos, etc.
 * 
 * @author Danny Muñoz
 */

@Named
@SessionScoped
public class ContextBean implements Serializable{
    
    @EJB PermisoServiceLocal servicePermiso;
            
    /**
     * Usuario autenticado
     */
    private Usuario usuario;
    private List<Permiso> permisos = new ArrayList<Permiso>();

    public ContextBean() {
    }
    
    public void init(Usuario usuario){
        this.usuario = usuario;
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
        
    public boolean checkPermiso(String permiso){
        if(this.permisos.contains(permiso)){
            return true;
        }        
        return false;
    }
    
    public boolean checkDisabled(String permiso){
        if(this.permisos.contains(permiso)){
            return false;
        }        
        return true;
    }
    
    public void checkPagePermiso(String permiso){
        FacesContext fc = FacesContext.getCurrentInstance();
        try {            
            fc.getExternalContext().redirect(fc.getExternalContext().getRequestContextPath() + "/nopermiso.jsf");
        } catch (Exception e) {
        }
    }

    /**
     * @return the permisos
     */
    public List<Permiso> getPermisos() {
        return permisos;
    }

    /**
     * @param permisos the permisos to set
     */
    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }
           
}
