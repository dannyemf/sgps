/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller.converter;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sgps.model.seguridad.QUsuario;
import sgps.model.seguridad.Usuario;
import sgps.service.UsuarioServiceLocal;

/**
 *
 * @author uti
 */

@FacesConverter(value = "usuarioLoginConverter")
public class UsuarioLoginConverter implements Converter{
    UsuarioServiceLocal usuarioService = lookupUsuarioServiceLocal();
    

    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario m = null;
        
        try {            
            JPAQuery q = usuarioService.newJpaQuery();
            QUsuario us = QUsuario.usuario;            
            m = q.from(us).where(us.login.eq(value)).singleResult(us);
        } catch (Exception e) {}
        
        return m;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Usuario m = (Usuario)value;
            System.out.println("getAsString() : " + m.getLogin());
            return m.getLogin();
        } catch (Exception e) {
            System.out.println(e);
            return value != null ? value.toString() : "";
        }
    }

    private UsuarioServiceLocal lookupUsuarioServiceLocal() {
        try {
            Context c = new InitialContext();
            return (UsuarioServiceLocal) c.lookup("java:global/sgps/sgps-ejb/UsuarioService!sgps.service.UsuarioServiceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
}
