/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import java.util.List;
import javax.ejb.Local;
import sgps.dao.GenericoDAOInterface;
import sgps.model.seguridad.Grupo;
import sgps.model.seguridad.Menu;
import sgps.model.seguridad.Permiso;
import sgps.model.seguridad.Usuario;
import sgps.view.model.ItemGrupo;

/**
 *
 * @author uti
 */
@Local
public interface GrupoServiceLocal extends GenericoDAOInterface<Grupo>{
    List<ItemGrupo> obtenerGrupos(Usuario usuario);
    List<ItemGrupo> obtenerGrupos(Permiso usuario);
    List<ItemGrupo> obtenerGrupos(Menu menu);
}
