/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.model.proyecto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import sgps.model.seguridad.Usuario;

/**
 *
 * @author uti
 */
@Entity
@Table(schema = "proyecto")
public class Issue implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyecto proyecto;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioCreacion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioAsignado;
    
    @Column(nullable = false, length = 100)
    private String titulo;
    
    @Column(nullable = false, length = 255)
    private String descripcion;        
    
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    
    @Enumerated(EnumType.STRING)
    private EstadoIssue estado;

    public Issue() {
        estado = EstadoIssue.Abierto;
        fechaCreacion = new Date();
    }        

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgps.model.core.Issue[ id=" + id + " ]";
    }

    /**
     * @return the proyecto
     */
    public Proyecto getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    /**
     * @return the usuarioCreacion
     */
    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    /**
     * @param usuarioCreacion the usuarioCreacion to set
     */
    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    /**
     * @return the usuarioAsignado
     */
    public Usuario getUsuarioAsignado() {
        return usuarioAsignado;
    }

    /**
     * @param usuarioAsignado the usuarioAsignado to set
     */
    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaCierre
     */
    public Date getFechaCierre() {
        return fechaCierre;
    }

    /**
     * @param fechaCierre the fechaCierre to set
     */
    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    /**
     * @return the estado
     */
    public EstadoIssue getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoIssue estado) {
        this.estado = estado;
    }
    
}
