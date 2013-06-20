/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import sgps.model.seguridad.Permiso;
import sgps.service.GrupoServiceLocal;
import sgps.service.PermisoServiceLocal;

/**
 * Controlador de permisos
 * 
 * @author Danny Muñoz
 */

@ManagedBean
@ViewScoped
public class ListPermisoController extends Controller{
    
    @EJB
    private PermisoServiceLocal service;
    
    @EJB
    private GrupoServiceLocal groupService;
    
    @Inject
    private ContextBean context;        
    
    /**
     * Listado de usuarios a mostrar en la vista
     */
    private List<Permiso> listaDatos = new ArrayList<Permiso>();
        
    
    /**
     * Texto para filtrar los datos al presionar el botón buscar
     */
    private String textoBusqueda;

    public ListPermisoController() {
    }
    
    @PostConstruct
    public void init(){        
    }
    
    
    
    /**
     * Evento invocada al presionar el botón buscar
     * @param evento 
     */
    public void eventoBuscar(ActionEvent event){                
        listaDatos = service.buscarPor(textoBusqueda);
    }        

    /**
     * @return the listaDatos
     */
    public List<Permiso> getListaDatos() {
        return listaDatos;
    }

    /**
     * @param listaDatos the listaDatos to set
     */
    public void setListaDatos(List<Permiso> listaDatos) {
        this.listaDatos = listaDatos;
    }    

    /**
     * @return the textoBusqueda
     */
    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    /**
     * @param textoBusqueda the textoBusqueda to set
     */
    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }
           
}
