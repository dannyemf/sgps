/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.model.seguridad;

import java.io.Serializable;
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

/**
 *
 * @author remoto
 */
@Entity
@Table(schema = "seguridad")
public class Menu implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, length = 45)
    private String etiqueta;
    
    @Column(length = 255)
    private String control;
    
    @Column(length = 45)
    private String icono;
    
    @Column(nullable = false)
    private double orden;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Menu padre;

    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "menu_grupo", schema = "seguridad", joinColumns = { @JoinColumn(name = "MENU_ID") }, inverseJoinColumns = { @JoinColumn(name = "GRUPO_ID") })
    private Set<Grupo> grupos = new HashSet<Grupo>();
    
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgps.model.Menu[ id=" + id + " ]";
    }

    /**
     * @return the etiqueta
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta the etiqueta to set
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * @return the control
     */
    public String getControl() {
        return control;
    }

    /**
     * @param control the control to set
     */
    public void setControl(String control) {
        this.control = control;
    }

    /**
     * @return the icono
     */
    public String getIcono() {
        return icono;
    }

    /**
     * @param icono the icono to set
     */
    public void setIcono(String icono) {
        this.icono = icono;
    }

    /**
     * @return the grupos
     */
    public Set<Grupo> getGrupos() {
        return grupos;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }

    /**
     * @return the padre
     */
    public Menu getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Menu padre) {
        this.padre = padre;
    }

    /**
     * @return the orden
     */
    public double getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(double orden) {
        this.orden = orden;
    }
    
}
