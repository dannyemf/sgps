/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import sgps.model.seguridad.Permiso;
import sgps.model.seguridad.QGrupo;
import sgps.model.seguridad.QPermiso;
import sgps.model.seguridad.Usuario;
import sgps.service.GrupoServiceLocal;
import sgps.service.PermisoServiceLocal;
import sgps.view.model.ItemGrupo;

/**
 * Controlador de permisos
 * 
 * @author Danny Muñoz
 */

@Named
@ConversationScoped
public class PermisoController extends Controller{
    
    @EJB
    private PermisoServiceLocal service;
    
    @EJB
    private GrupoServiceLocal groupService;
    
    @Inject
    private ContextBean context;
    
    @Inject
    private Conversation conversation;    
    
    /**
     * Listado de usuarios a mostrar en la vista
     */
    private List<Permiso> listaDatos = new ArrayList<Permiso>();
    
    /**
     * Lista de grupos para permitir seleccionar al usuario mediante un check
     */
    private List<ItemGrupo> grupos = new ArrayList<ItemGrupo>();
    
    /**
     * Usuario en edición
     */
    private Permiso modeloEdicion;
    
    /**
     * Texto para filtrar los datos al presionar el botón buscar
     */
    private String textoBusqueda;

    public PermisoController() {
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
     * Validador del nombre del permiso. Verfica que no esté duplicado
     * @param context FacesContext
     * @param component UIComponent
     * @param value Valor
     * @throws ValidatorException 
     */
    public void validateNombre(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        JPAQuery q = service.newJpaQuery();
        QPermiso p = QPermiso.permiso;        
        boolean e = q.from(p).where(p.id.ne(modeloEdicion.getId()).and(p.nombre.eq((String)value))).exists();
        if(e){
            FacesMessage fm = new FacesMessage("Nombre de permiso no disponible");
            fm.setSeverity(FacesMessage.SEVERITY_WARN);            
            throw  new ValidatorException(fm);
        }
    }
    
    /**
     * Evento invocada al presionar el botón buscar
     * @param evento 
     */
    public void eventoBuscar(ActionEvent event){        
        beginConversation();
        listaDatos = service.buscarPor(textoBusqueda);
    }
    
    /**
     * Evento invocada al presionar el botón nuevo
     * @return 
     */
    public String eventoNuevo(){
        System.out.println("eventoNuevo(): " + conversation.getId());
        beginConversation();
        modeloEdicion = new Permiso();
        grupos = groupService.obtenerGrupos(modeloEdicion);
        return "editar.xhtml?faces-redirect=true";
    }
    
    /**
     * Evento invocada al presionar el vínculo editar
     * @param item El usuario a editar
     * @return 
     */
    public String eventoEditar(Permiso item){
        System.out.println("eventoEditar(): ");
        beginConversation();        
        modeloEdicion = item;        
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
        Permiso us = modeloEdicion.getId() == null ? service.crear(modeloEdicion) : service.actualizar(modeloEdicion);
        
        
        //endConversation();
        return "lista.xhtml?faces-redirect=true";
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
    public Permiso getModeloEdicion() {
        return modeloEdicion;
    }

    /**
     * @param modeloEdicion the modeloEdicion to set
     */
    public void setModeloEdicion(Permiso modeloEdicion) {
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
