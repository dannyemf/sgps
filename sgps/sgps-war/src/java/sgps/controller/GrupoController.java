/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.ArrayList;
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
import sgps.model.seguridad.Grupo;
import sgps.model.seguridad.QGrupo;
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
     * Listado de grupos a mostrar en la vista
     */
    private List<Grupo> listaDatos = new ArrayList<Grupo>();
    
    /**
     * Grupo en edición
     */
    private Grupo modeloEdicion;    
    
    /**
     * Texto de búsqueda
     */
    private String textoBusqueda;

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
     * Validador del nombre del grupo. Verfica que no esté duplicado
     * @param context FacesContext
     * @param component UIComponent
     * @param value Valor
     * @throws ValidatorException 
     */
    public void validateNombre(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        JPAQuery q = service.newJpaQuery();
        QGrupo g = QGrupo.grupo;
        
        boolean e = q.from(g).where(g.id.ne(modeloEdicion.getId()).and(g.nombre.eq((String)value))).exists();
        if(e){
            FacesMessage fm = new FacesMessage("Nombre de grupo no disponible");
            fm.setSeverity(FacesMessage.SEVERITY_WARN);            
            throw  new ValidatorException(fm);
        }
    }    
    
    /**
     * Evento buscar invocada al presionar el botón buscar
     * @param evento 
     */
    public void eventoBuscar(ActionEvent evento){        
        beginConversation();
        listaDatos = service.buscarPor(textoBusqueda);
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
        //endConversation();        
        return "lista.xhtml?faces-redirect=true";
    }
    
    /**
     * Evento para guardar el grupo en edición
     * @return 
     */
    public String eventoGuardar(){
        System.out.println("eventoGuardar(): ");               
        Grupo us = modeloEdicion.getId() == null ? service.crear(modeloEdicion) : service.actualizar(modeloEdicion);
        //endConversation();
        return "lista.xhtml?faces-redirect=true";
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
