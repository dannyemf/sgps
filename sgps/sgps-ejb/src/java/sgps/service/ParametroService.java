/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import com.mysema.query.jpa.impl.JPAQuery;
import javax.ejb.Stateless;
import sgps.dao.GenericoJPADAO;
import sgps.model.seguridad.Parametro;
import sgps.model.seguridad.QParametro;

/**
 *
 * @author uti
 */
@Stateless
public class ParametroService extends GenericoJPADAO<Parametro> implements ParametroServiceLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Parametro obtenerPorNombre(String nombre){
        
        JPAQuery q = newJpaQuery();
        QParametro p = QParametro.parametro;
        
        return q.from(p).where(p.nombre.eq(nombre)).singleResult(p);
                
    }

}
