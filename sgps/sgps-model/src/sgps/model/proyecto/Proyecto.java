/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.model.proyecto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
public class Proyecto implements Serializable {
    
    private static long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 255)
    private String descripcion;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaFin;
    
    @ManyToOne(fetch = FetchType.EAGER)    
    private Usuario jefe;
    
    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "proyecto_usuario", schema = "proyecto", joinColumns = { @JoinColumn(name = "PROYECTO_ID") }, inverseJoinColumns = { @JoinColumn(name = "USUARIO_ID") })
    private Set<Usuario> miembros = new HashSet<>();

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
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgps.model.core.Proyecto[ id=" + id + " ]";
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the jefe
     */
    public Usuario getJefe() {
        return jefe;
    }

    /**
     * @param jefe the jefe to set
     */
    public void setJefe(Usuario jefe) {
        this.jefe = jefe;
    }

    /**
     * @return the miembros
     */
    public Set<Usuario> getMiembros() {
        return miembros;
    }

    /**
     * @param miembros the miembros to set
     */
    public void setMiembros(Set<Usuario> miembros) {
        this.miembros = miembros;
    }
    
}
