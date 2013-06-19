/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import javax.ejb.Local;
import sgps.dao.GenericoDAOInterface;
import sgps.model.proyecto.Proyecto;

/**
 *
 * @author uti
 */
@Local
public interface ProyectoServiceLocal extends GenericoDAOInterface<Proyecto> {
    
}
