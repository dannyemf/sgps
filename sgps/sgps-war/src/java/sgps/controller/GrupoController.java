/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sgps.model.seguridad.Grupo;
import sgps.service.GrupoServiceLocal;

/**
 * Controlador de grupos de usuarios
 * 
 * @author Danny Muñoz
 */

@Named
@ConversationScoped
public class GrupoController extends Controller{   
    
    @EJB
    private GrupoServiceLocal service;
    
    @Inject
    private ContextBean context;
    
    @Inject
    private Conversation conversation;        
    
    /**
     * Grupo en edición
     */
    private Grupo modeloEdicion;          

    public GrupoController() {
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
     * Evento nuevo invocada al presionar el botón nuevo
     * @return 
     */
    public String eventoNuevo(){       
        beginConversation();        
        modeloEdicion = new Grupo();
        return "editar.xhtml?faces-redirect=true";
    }
    
    /**
     * Evento editar invocada al presionar el vínculo editar
     * @param item El grupo a editar
     * @return 
     */
    public String eventoEditar(Grupo item){
        System.out.println("eventoEditar(): ");
        modeloEdicion = item;
        beginConversation();                
        return "editar.xhtml?faces-redirect=true";
    }
    
    /**
     * Evento para cancelar la edición del grupo
     * @return 
     */
    public String eventoCancelar(){
        System.out.println("eventoCancelar(): ");
        endConversation();
        
        return "lista.xhtml?faces-redirect=true";
    }
    
    /**
     * Evento para guardar el grupo en edición
     * @return 
     */
    public String eventoGuardar(){
        System.out.println("eventoGuardar(): ");               
        Grupo us = modeloEdicion.getId() == null ? service.crear(modeloEdicion) : service.actualizar(modeloEdicion);
        endConversation();
        
        return "lista.xhtml?faces-redirect=true";
    }   

    /**
     * @return the modeloEdicion
     */
    public Grupo getModeloEdicion() {
        return modeloEdicion;
    }

    /**
     * @param modeloEdicion the modeloEdicion to set
     */
    public void setModeloEdicion(Grupo modeloEdicion) {
        this.modeloEdicion = modeloEdicion;
    }    
            
}
