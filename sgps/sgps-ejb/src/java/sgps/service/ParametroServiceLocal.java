/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import javax.ejb.Local;
import sgps.dao.GenericoDAOInterface;
import sgps.model.seguridad.Parametro;

/**
 *
 * @author uti
 */
@Local
public interface ParametroServiceLocal extends GenericoDAOInterface<Parametro>{
    
    Parametro obtenerPorNombre(String nombre);
            
}
