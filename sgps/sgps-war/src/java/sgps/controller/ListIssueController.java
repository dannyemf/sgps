/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import sgps.model.proyecto.EstadoIssue;
import sgps.model.proyecto.Issue;
import sgps.model.proyecto.QIssue;
import sgps.service.IssueServiceLocal;
import sgps.service.ProyectoServiceLocal;

/**
 *
 * @author uti
 */

@Named
@SessionScoped
public class ListIssueController extends Controller{
    
    @EJB
    private IssueServiceLocal service;
    
    @EJB
    private ProyectoServiceLocal serviceProyecto;
    
    @Inject
    private ContextBean context;
    
    private Issue modeloEdicion;

    public ListIssueController() {
    }
    
    public List<Issue> getIssuesAbiertos(){
        
        if(context.getProyecto() != null){
            JPAQuery q = serviceProyecto.newJpaQuery();        
            QIssue is = QIssue.issue;        
            List<Issue>  lista = q.from(is).where(is.proyecto.eq(context.getProyecto()).and(is.usuarioCreacion.eq(context.getUsuario())).and(is.estado.eq(EstadoIssue.Abierto))).orderBy(is.fechaCreacion.asc()).list(is);
            return lista;                
        }
        
        return new ArrayList<Issue>();
    }
    
    public List<Issue> getIssuesCerrados(){
        
        if(context.getProyecto() != null){
            JPAQuery q = serviceProyecto.newJpaQuery();        
            QIssue is = QIssue.issue;        
            List<Issue>  lista = q.from(is).where(is.proyecto.eq(context.getProyecto()).and(is.usuarioCreacion.eq(context.getUsuario())).and(is.estado.eq(EstadoIssue.Cerrado))).orderBy(is.fechaCreacion.asc()).list(is);
            return lista;                
        }
        
        return new ArrayList<Issue>();
    }
    
    public List<Issue> getIssuesAsignados(){
        
        if(context.getProyecto() != null){
            JPAQuery q = serviceProyecto.newJpaQuery();        
            QIssue is = QIssue.issue;        
            List<Issue>  lista = q.from(is).where(is.proyecto.eq(context.getProyecto()).and(is.usuarioAsignado.eq(context.getUsuario())).and(is.estado.eq(EstadoIssue.Cerrado))).orderBy(is.fechaCreacion.asc()).list(is);
            return lista;                
        }
        
        return new ArrayList<Issue>();
    }
    
    public void accionNuevo(ActionEvent evento){
        if(context.getProyecto() != null){
            modeloEdicion = new Issue();
            modeloEdicion.setProyecto(context.getProyecto());
            modeloEdicion.setUsuarioCreacion(context.getUsuario());
        }
    }
    
    public void accionGuardar(ActionEvent evento){
        
        Issue is = modeloEdicion.getId() == null ? service.crear(modeloEdicion) : service.actualizar(modeloEdicion);
        modeloEdicion = null;
        
    }
    
    public void accionCancelar(ActionEvent evento){
        modeloEdicion = null;
    }

    /**
     * @return the modeloEdicion
     */
    public Issue getModeloEdicion() {
        return modeloEdicion;
    }

    /**
     * @param modeloEdicion the modeloEdicion to set
     */
    public void setModeloEdicion(Issue modeloEdicion) {
        this.modeloEdicion = modeloEdicion;
    }
    
    
        
    
}
