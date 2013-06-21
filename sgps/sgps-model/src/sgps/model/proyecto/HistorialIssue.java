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
public class HistorialIssue implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Issue issue;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(nullable = false, length = 255)
    private String comentario;
    
    @Enumerated(EnumType.STRING)
    private TipoHistorialIssue tipo;

    public HistorialIssue() {
        fecha = new Date();
        tipo = TipoHistorialIssue.Abierto;
        comentario = "";
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
        if (!(object instanceof HistorialIssue)) {
            return false;
        }
        HistorialIssue other = (HistorialIssue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgps.model.proyecto.HistorialIssue[ id=" + id + " ]";
    }

    /**
     * @return the issue
     */
    public Issue getIssue() {
        return issue;
    }

    /**
     * @param issue the issue to set
     */
    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the tipo
     */
    public TipoHistorialIssue getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoHistorialIssue tipo) {
        this.tipo = tipo;
    }
    
}
