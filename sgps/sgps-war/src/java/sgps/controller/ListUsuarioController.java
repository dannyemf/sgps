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
import sgps.model.seguridad.Usuario;
import sgps.service.GrupoServiceLocal;
import sgps.service.UsuarioServiceLocal;

/**
 * Controlador de usuarios
 * @author Danny Muñoz
 */

@ManagedBean
@ViewScoped
public class ListUsuarioController extends Controller{
    
    @EJB
    private UsuarioServiceLocal service;
    
    @EJB
    private GrupoServiceLocal groupService;
    
    @Inject
    private ContextBean context;       
    
    /**
     * Listado de usuarios a mostrar en la vista
     */
    private List<Usuario> listaDatos = new ArrayList<Usuario>();        
    
    /**
     * Texto para filtrar los datos al presionar el botón buscar
     */
    private String textoBusqueda;

    public ListUsuarioController() {
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
        System.out.println("buscar: " + event);
    }        

    /**
     * @return the listaDatos
     */
    public List<Usuario> getListaDatos() {
        return listaDatos;
    }

    /**
     * @param listaDatos the listaDatos to set
     */
    public void setListaDatos(List<Usuario> listaDatos) {
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
