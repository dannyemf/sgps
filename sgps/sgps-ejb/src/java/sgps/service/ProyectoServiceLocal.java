/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import java.util.List;
import javax.ejb.Local;
import sgps.dao.GenericoDAOInterface;
import sgps.model.proyecto.Proyecto;
import sgps.model.seguridad.Usuario;

/**
 *
 * @author uti
 */
@Local
public interface ProyectoServiceLocal extends GenericoDAOInterface<Proyecto> {
    
    List<Proyecto> obtenerProyectos(Usuario usuario);
    
}
