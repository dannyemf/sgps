/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import com.mysema.query.jpa.impl.JPAQuery;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import sgps.model.proyecto.ArchivoAdjunto;
import sgps.model.proyecto.EstadoIssue;
import sgps.model.proyecto.Issue;
import sgps.model.proyecto.QIssue;
import sgps.model.proyecto.TipoArchivoAdjunto;
import sgps.model.seguridad.Parametro;
import sgps.model.seguridad.QParametro;
import sgps.service.IssueServiceLocal;
import sgps.service.ParametroServiceLocal;
import sgps.service.ProyectoServiceLocal;

/**
 *
 * @author uti
 */

@Named
@SessionScoped
public class BandejaIssueController extends Controller{
    
    @EJB
    private IssueServiceLocal service;
    
    @EJB
    private ProyectoServiceLocal serviceProyecto;
    
    @EJB
    private ParametroServiceLocal serviceParametro;
    
    @Inject
    private ContextBean context;
    
    private Issue modeloEdicion;
    
    private List<ArchivoAdjunto> archivosAdjuntosIssue = new ArrayList<ArchivoAdjunto>();

    public BandejaIssueController() {
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
            List<Issue>  lista = q.from(is).where(is.proyecto.eq(context.getProyecto()).and(is.usuarioAsignado.eq(context.getUsuario())).and(is.estado.eq(EstadoIssue.Cerrado))).orderBy(is.fechaCreacion.asc()).list(is);
            return lista;                
        }
        
        return new ArrayList<Issue>();
    }
    
    public List<Issue> getIssuesAsignados(){
        
        if(context.getProyecto() != null){
            JPAQuery q = serviceProyecto.newJpaQuery();        
            QIssue is = QIssue.issue;        
            List<Issue>  lista = q.from(is).where(is.proyecto.eq(context.getProyecto()).and(is.usuarioAsignado.eq(context.getUsuario())).and(is.estado.eq(EstadoIssue.Abierto))).orderBy(is.fechaCreacion.asc()).list(is);
            return lista;                
        }
        
        return new ArrayList<Issue>();
    }
    
    public void accionNuevo(ActionEvent evento){
        if(context.getProyecto() != null){
            modeloEdicion = new Issue();
            modeloEdicion.setProyecto(context.getProyecto());
            modeloEdicion.setUsuarioCreacion(context.getUsuario());
            
            archivosAdjuntosIssue = new ArrayList<ArchivoAdjunto>();
        }
    }
    
    public void accionGuardar(ActionEvent evento){
        
        Issue is = modeloEdicion.getId() == null ? service.crear(modeloEdicion) : service.actualizar(modeloEdicion);
        
        Parametro p_dir = serviceParametro.obtenerPorNombre("directorio_archivos");
        
        for (Iterator<ArchivoAdjunto> it = archivosAdjuntosIssue.iterator(); it.hasNext();) {
            ArchivoAdjunto a = it.next();
            a.setReferencia(modeloEdicion.getId());
            File orignal = new File(a.getPath());
            File move = new File(p_dir.getValor() + "/" + a.getNombre());            
            boolean b = orignal.renameTo(move);
            
            
        }
        
        modeloEdicion = null;
        
    }
    
    public void accionCancelar(ActionEvent evento){
        modeloEdicion = null;
    }
    
     public void fileIssueListener(FileEntryEvent e)
    {
        FileEntry fe = (FileEntry)e.getComponent();
        FileEntryResults results = fe.getResults();        
        
        for (FileEntryResults.FileInfo i : results.getFiles()) 
        {            
            if (i.isSaved()) {
                ArchivoAdjunto a = new ArchivoAdjunto();
                archivosAdjuntosIssue.add(a);
                
                a.setNombre(i.getFileName());
                a.setTamaño(i.getSize());
                a.setMimeType(i.getContentType());
                a.setTipo(TipoArchivoAdjunto.Issue);
                a.setPath(i.getFile().getAbsolutePath());
                
                System.out.println(i.getFile().getAbsolutePath());
            } else {
                /*fileData.add("File was not saved because: " +
                    i.getStatus().getFacesMessage(
                        FacesContext.getCurrentInstance(),
                        fe, i).getSummary());*/
            }
        }

        /*if (parent != null) {
            long dirSize = 0;
            int fileCount = 0;
            for (File file : parent.listFiles()) {
                fileCount++;
                dirSize += file.length();
            }
            fileData.add("Total Files In Upload Directory: " + fileCount);
            fileData.add("Total Size of Files In Directory: " + dirSize + " bytes");
        }*/
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

    /**
     * @return the archivosAdjuntosIssue
     */
    public List<ArchivoAdjunto> getArchivosAdjuntosIssue() {
        return archivosAdjuntosIssue;
    }

    /**
     * @param archivosAdjuntosIssue the archivosAdjuntosIssue to set
     */
    public void setArchivosAdjuntosIssue(List<ArchivoAdjunto> archivosAdjuntosIssue) {
        this.archivosAdjuntosIssue = archivosAdjuntosIssue;
    }
    
    
        
    
}
