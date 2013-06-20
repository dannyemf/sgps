/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import sgps.model.proyecto.Fase;
import sgps.model.proyecto.Proyecto;
import sgps.model.seguridad.Usuario;
import sgps.service.ProyectoServiceLocal;

 import org.icefaces.ace.event.TextChangeEvent;
import sgps.controller.conmparator.FaseOrdenComparator;
import sgps.model.seguridad.QUsuario;
import sgps.service.UsuarioServiceLocal;

/**
 * Controlador de proyectos
 * @author Danny Muñoz
 */

@Named
@ConversationScoped
public class ProyectoController extends Controller{
    
    @EJB
    private ProyectoServiceLocal service;
    
    @EJB
    private UsuarioServiceLocal serviceUsuario;
    
    @Inject
    private ContextBean context;
    
    @Inject
    private Conversation conversation;            
    
    private List<Usuario> listaUsuarios = new ArrayList<Usuario>();    
    
    /**
     * Usuario en edición
     */
    private Proyecto modeloEdicion;
    
    private Fase faseEdicion;
    
    private String miembroAgregar;
    
    private String jefeActual;
    

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
    
    public void textChangeEventHandler(TextChangeEvent event) {
        listaUsuarios.clear();
        String filter = event.getNewValue() != null ? (String) event.getNewValue() : "";
        System.out.println("Filtro jefe: " + filter);
        
        JPAQuery q = service.newJpaQuery();
        QUsuario us = QUsuario.usuario;
        
        listaUsuarios = q.from(us).where(us.login.containsIgnoreCase(filter).or(us.descripcion.containsIgnoreCase(filter))).orderBy(us.login.asc()).limit(10).list(us);
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
        
        Usuario jefe = serviceUsuario.obtenerPorLogin(jefeActual);        
        modeloEdicion.setJefe(jefe);                
        
        Proyecto us = modeloEdicion.getId() == null ? service.crear(modeloEdicion) : service.actualizar(modeloEdicion);
        
        endConversation();
        
        return "lista.xhtml?faces-redirect=true";
    }    
    
    public void eventoNuevaFase(ActionEvent evento){
        faseEdicion = new Fase();        
        runScript("dlgEditFase.show();");
        System.out.println("Evento: " + conversation.getId() + " - " + faseEdicion);
    }
    
    public void eventoEditarFase(ActionEvent evento, Fase fase){
        faseEdicion = fase;
        runScript("dlgEditFase.show();");
    }
    
    public void eventoRemoverFase(ActionEvent evento, Fase fase){
        modeloEdicion.getFases().remove(fase);
    }
    
    public void eventoGuardarFase(ActionEvent evento){
        
        if(faseEdicion.getId() == null){
            modeloEdicion.addFase(faseEdicion);
        }
        
        faseEdicion = null;
        runScript("dlgEditFase.hide();");
    }
    
    public void eventoCancelarFase(ActionEvent evento){
        faseEdicion = null;
        runScript("dlgEditFase.hide();");
    }
    
    public void eventoAgregarMiembro(ActionEvent evento){
        Usuario miembro = serviceUsuario.obtenerPorLogin(miembroAgregar);
        if(miembro != null){
            this.modeloEdicion.getMiembros().add(miembro);
        }        
    }
    
    public void eventoRemoverMiembro(ActionEvent evento, Usuario miembro){
        this.modeloEdicion.getMiembros().remove(miembro);
    }
    
    public List<Fase> getFasesModelEdicion() {
        List<Fase> fases = new ArrayList<Fase>(modeloEdicion.getFases());
        Collections.sort(fases, new FaseOrdenComparator());
        return fases;
    }
    
    public List<Usuario> getMiembrosModelEdicion() {
        return new ArrayList<Usuario>(modeloEdicion.getMiembros());
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
     * @return the listaUsuarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }        

    /**
     * @return the faseEdicion
     */
    public Fase getFaseEdicion() {
        return faseEdicion;
    }

    /**
     * @param faseEdicion the faseEdicion to set
     */
    public void setFaseEdicion(Fase faseEdicion) {
        this.faseEdicion = faseEdicion;
    }

    /**
     * @return the miembroAgregar
     */
    public String getMiembroAgregar() {
        return miembroAgregar;
    }

    /**
     * @param miembroAgregar the miembroAgregar to set
     */
    public void setMiembroAgregar(String miembroAgregar) {
        this.miembroAgregar = miembroAgregar;
    }

    /**
     * @return the jefeActual
     */
    public String getJefeActual() {
        return jefeActual;
    }

    /**
     * @param jefeActual the jefeActual to set
     */
    public void setJefeActual(String jefeActual) {
        this.jefeActual = jefeActual;
    }

    
    
}
