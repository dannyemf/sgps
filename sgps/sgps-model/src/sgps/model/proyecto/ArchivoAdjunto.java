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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author uti
 */
@Entity
@Table(schema = "proyecto")
public class ArchivoAdjunto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private Long referencia;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoArchivoAdjunto tipo;
    
    @Column(nullable = false, length = 255)
    private String nombre;
    
    @Column(nullable = false)
    private Long tamaño; //bytes
    
    @Column(nullable = false, length = 45)
    private String mimeType;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Transient
    private String path;

    public ArchivoAdjunto() {
        tipo = TipoArchivoAdjunto.Issue;
        fecha = new Date();
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
        if (!(object instanceof ArchivoAdjunto)) {
            return false;
        }
        ArchivoAdjunto other = (ArchivoAdjunto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sgps.model.proyecto.ArchivoAdjunto[ id=" + id + " ]";
    }

    /**
     * @return the referencia
     */
    public Long getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(Long referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the tipo
     */
    public TipoArchivoAdjunto getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoArchivoAdjunto tipo) {
        this.tipo = tipo;
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
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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
     * @return the tamaño
     */
    public Long getTamaño() {
        return tamaño;
    }

    /**
     * @param tamaño the tamaño to set
     */
    public void setTamaño(Long tamaño) {
        this.tamaño = tamaño;
    }
    
    public String getTamañoVisual(){
        
        if(tamaño < 1024){
            return tamaño + " bytes";
        }else{
            if(tamaño >= 1024 && tamaño < 1024000){
                return Math.rint((tamaño / 1024.0) * 100) / 100 + " KB";
            }else{
                return Math.rint((tamaño / 1024000.0) * 100) / 100 + " MB";
            }
        }                
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    public boolean isImage(){
        return this.mimeType.contains("image");
    }
    
    
}
