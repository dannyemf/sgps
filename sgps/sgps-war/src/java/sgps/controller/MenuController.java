/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sgps.model.seguridad.Menu;
import sgps.service.GrupoServiceLocal;
import sgps.service.MenuServiceLocal;
import sgps.view.model.ItemGrupo;

/**
 * Controlador de permisos
 * 
 * @author Danny Muñoz
 */

@Named
@ConversationScoped
public class MenuController extends Controller{
    
    @EJB
    private MenuServiceLocal service;
    
    @EJB
    private GrupoServiceLocal groupService;
    
    @Inject
    private ContextBean context;
    
    @Inject
    private Conversation conversation;            
    
    private List<Menu> listaPadres = new ArrayList<Menu>();
    
    
    /**
     * Lista de grupos para permitir seleccionar al usuario mediante un check
     */
    private List<ItemGrupo> grupos = new ArrayList<ItemGrupo>();
    
    /**
     * Usuario en edición
     */
    private Menu modeloEdicion;
        

    public MenuController() {
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
     * Evento invocada al presionar el botón nuevo
     * @return 
     */
    public String eventoNuevo(){
        System.out.println("eventoNuevo(): " + conversation.getId());
        beginConversation();
        modeloEdicion = new Menu();
        
        listaPadres = service.buscarTodos(Menu.class);
        
        grupos = groupService.obtenerGrupos(modeloEdicion);
        return "editar.xhtml?faces-redirect=true";
    }
    
    /**
     * Evento invocada al presionar el vínculo editar
     * @param item El usuario a editar
     * @return 
     */
    public String eventoEditar(Menu item){
        System.out.println("eventoEditar(): ");
        beginConversation();        
        modeloEdicion = item;
        
        listaPadres = service.buscarTodos(Menu.class);
        listaPadres.remove(item);
        
        grupos = groupService.obtenerGrupos(modeloEdicion);
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
        
        for (Iterator<ItemGrupo> it = grupos.iterator(); it.hasNext();) {
            ItemGrupo ig = it.next();
            if(ig.isChecked()){
                modeloEdicion.getGrupos().add(ig.getGrupo());
            }else{
                modeloEdicion.getGrupos().remove(ig.getGrupo());
            }
        }
        Menu us = modeloEdicion.getId() == null ? service.crear(modeloEdicion) : service.actualizar(modeloEdicion);
        
        
        //endConversation();
        return "lista.xhtml?faces-redirect=true";
    }   

    /**
     * @return the grupos
     */
    public List<ItemGrupo> getGrupos() {
        return grupos;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupos(List<ItemGrupo> grupos) {
        this.grupos = grupos;
    }

    /**
     * @return the modeloEdicion
     */
    public Menu getModeloEdicion() {
        return modeloEdicion;
    }

    /**
     * @param modeloEdicion the modeloEdicion to set
     */
    public void setModeloEdicion(Menu modeloEdicion) {
        this.modeloEdicion = modeloEdicion;
    }   

    /**
     * @return the listaPadres
     */
    public List<Menu> getListaPadres() {
        return listaPadres;
    }

    /**
     * @param listaPadres the listaPadres to set
     */
    public void setListaPadres(List<Menu> listaPadres) {
        this.listaPadres = listaPadres;
    }
           
}
