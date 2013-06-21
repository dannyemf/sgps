/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import sgps.model.proyecto.Proyecto;
import sgps.model.seguridad.Permiso;
import sgps.model.seguridad.Usuario;
import sgps.service.PermisoServiceLocal;
import sgps.service.ProyectoServiceLocal;

/**
 * Clase para manejar el contexto de usuario, como usuario autenticado, ip, formatos, etc.
 * 
 * @author Danny Muñoz
 */

@Named
@SessionScoped
public class ContextBean implements Serializable{
    
    @EJB PermisoServiceLocal servicePermiso;
    @EJB ProyectoServiceLocal serviceProyecto;
            
    /**
     * Usuario autenticado
     */
    private Usuario usuario;
    private List<Permiso> permisos = new ArrayList<Permiso>();
    private Proyecto proyecto;
    private List<Proyecto> misProyectos = new ArrayList<Proyecto>();

    public ContextBean() {
    }
    
    public void init(Usuario usuario){
        this.usuario = usuario;        
        this.permisos = servicePermiso.obtenerPermisos(usuario);
        this.misProyectos = serviceProyecto.obtenerProyectos(usuario);
        if(!this.misProyectos.isEmpty()){
            this.proyecto = this.misProyectos.get(0);
        }
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
        if(hasPermiso(permiso)){
            return true;
        }        
        return false;
    }
    
    public boolean checkDisabled(String permiso){
        if(hasPermiso(permiso)){
            return false;
        }        
        return true;
    }
    
    public boolean hasPermiso(String permiso){
        
        for (Iterator<Permiso> it = permisos.iterator(); it.hasNext();) {
            Permiso p = it.next();
            if(p.getNombre().equals(permiso)){
                return true;
            }
        }
        
        return false;
    }
    
    public void checkPagePermiso(String permiso){                                    
                  
        if(!hasPermiso(permiso)){
            System.out.println("No contiene el permiso: " + permiso);
            FacesContext fc = FacesContext.getCurrentInstance();
            try {            
                fc.getExternalContext().redirect(fc.getExternalContext().getRequestContextPath() + "/nopermiso.jsf");
            } catch (Exception e) {
            }
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

    /**
     * @return the proyecto
     */
    public Proyecto getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    /**
     * @return the misProyectos
     */
    public List<Proyecto> getMisProyectos() {
        return misProyectos;
    }

    /**
     * @param misProyectos the misProyectos to set
     */
    public void setMisProyectos(List<Proyecto> misProyectos) {
        this.misProyectos = misProyectos;
    }
           
}
