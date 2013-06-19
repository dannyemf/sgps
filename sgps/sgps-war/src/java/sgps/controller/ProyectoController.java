/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import sgps.model.proyecto.Proyecto;
import sgps.service.ProyectoServiceLocal;

/**
 * Controlador de proyectos
 * @author Danny Muñoz
 */

@Named
@ConversationScoped
public class ProyectoController extends Controller{
    
    @EJB
    private ProyectoServiceLocal service;        
    
    @Inject
    private ContextBean context;
    
    @Inject
    private Conversation conversation;    
    
    /**
     * Listado de usuarios a mostrar en la vista
     */
    private List<Proyecto> listaDatos = new ArrayList<Proyecto>();        
    
    /**
     * Usuario en edición
     */
    private Proyecto modeloEdicion;
    
    /**
     * Texto para filtrar los datos al presionar el botón buscar
     */
    private String textoBusqueda;

    public ProyectoController() {
    }
    
    @PostConstruct
    public void init(){        
    }
    
    /**
     * Inicia la conversación
     */
    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    /**
     * Termina la conversación
     */
    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }        
    
    /**
     * Evento invocada al presionar el botón buscar
     * @param evento 
     */
    public void eventoBuscar(ActionEvent event){        
        beginConversation();       
        listaDatos = service.buscarPor(textoBusqueda);
        System.out.println("buscar: " + event);
    }
    
    /**
     * Evento invocada al presionar el botón nuevo
     * @return 
     */
    public String eventoNuevo(){
        System.out.println("eventoNuevo(): " + conversation.getId());
        beginConversation();
        modeloEdicion = new Proyecto();
        
        return "editar.xhtml?faces-redirect=true";
    }
    
    /**
     * Evento invocada al presionar el vínculo editar
     * @param item El usuario a editar
     * @return 
     */
    public String eventoEditar(Proyecto item){
        System.out.println("eventoEditar(): ");
        beginConversation();
        
        modeloEdicion = item;
                
        return "editar.xhtml?faces-redirect=true";
    }
    
    /**
     * Evento para cancelar la edición
     * @return 
     */
    public String eventoCancelar(){
        System.out.println("eventoCancelar(): ");
        //endConversation();        
        return "lista.xhtml?faces-redirect=true";
    }
    
    /**
     * Evento para guardar el usuario en edición
     * @return 
     */
    public String eventoGuardar(){
        System.out.println("eventoGuardar(): ");
        
//        for (Iterator<ItemGrupo> it = grupos.iterator(); it.hasNext();) {
//            ItemGrupo ig = it.next();
//            if(ig.isChecked()){
//                modeloEdicion.getGrupos().add(ig.getGrupo());
//            }else{
//                modeloEdicion.getGrupos().remove(ig.getGrupo());
//            }
//        }
        Proyecto us = modeloEdicion.getId() == null ? service.crear(modeloEdicion) : service.actualizar(modeloEdicion);
        
        
        //endConversation();
        return "lista.xhtml?faces-redirect=true";
    }

    /**
     * @return the listaDatos
     */
    public List<Proyecto> getListaDatos() {
        return listaDatos;
    }

    /**
     * @param listaDatos the listaDatos to set
     */
    public void setListaDatos(List<Proyecto> listaDatos) {
        this.listaDatos = listaDatos;
    }    

    /**
     * @return the modeloEdicion
     */
    public Proyecto getModeloEdicion() {
        return modeloEdicion;
    }

    /**
     * @param modeloEdicion the modeloEdicion to set
     */
    public void setModeloEdicion(Proyecto modeloEdicion) {
        this.modeloEdicion = modeloEdicion;
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
