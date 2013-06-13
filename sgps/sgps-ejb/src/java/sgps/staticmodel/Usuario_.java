/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.staticmodel;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sgps.model.seguridad.Grupo;
import sgps.model.seguridad.Usuario;

/**
 *
 * @author remoto
 */
@StaticMetamodel(Usuario.class)
public class Usuario_{
    
    public static volatile SingularAttribute<Usuario, Long> id;
    public static volatile SingularAttribute<Usuario, String> login;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile SingularAttribute<Usuario, String> descripcion;
    public static volatile SetAttribute<Usuario, Grupo> grupos;
    
}
