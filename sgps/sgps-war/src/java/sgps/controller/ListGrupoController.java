/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import sgps.model.seguridad.Grupo;
import sgps.service.GrupoServiceLocal;

/**
 *
 * @author uti
 */

@ManagedBean
@ViewScoped
public class ListGrupoController implements Serializable{
    
    @EJB
    private GrupoServiceLocal service;
    
    @Inject
    private ContextBean context;
    
    private String textoBusqueda;
    private List<Grupo> listaDatos = new ArrayList<Grupo>();
    
    /**
     * Evento buscar invocada al presionar el botón buscar
     * @param evento 
     */
    public void eventoBuscar(ActionEvent evento){
        listaDatos = service.buscarPor(getTextoBusqueda());
    }

    /**
     * @return the listaDatos
     */
    public List<Grupo> getListaDatos() {
        return listaDatos;
    }

    /**
     * @param listaDatos the listaDatos to set
     */
    public void setListaDatos(List<Grupo> listaDatos) {
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
