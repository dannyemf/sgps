/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller.validator;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sgps.model.seguridad.QUsuario;
import sgps.service.UsuarioServiceLocal;
import sgps.util.StringUtil;


/**
 * Validador de entidades con propiedades únicas. 
 * Por ejemplo en usuario el login, en un producto el código.
 * 
 * La validacion se hace importando el tag: xmlns:s="http://sgps/jsf-custom-components/"
 * 
 * 
 * @author Danny Muñoz
 */

@FacesValidator(value = "usarioLoginValidator")
public class UsuarioLoginValidator  implements Validator{  
    UsuarioServiceLocal usuarioService = lookupUsuarioServiceLocal();
    
    /*public static final String VALIDATOR_ID = "javax.faces.Length";*/           
    
    
    
    public UsuarioLoginValidator() {
        super();
    }       
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        JPAQuery q = usuarioService.newJpaQuery();
        QUsuario us = QUsuario.usuario;
        
        if(!StringUtil.isNullOrEmpty(value)){
            boolean e = q.from(us).where(us.login.eq(value.toString())).exists();
        
            if(!e){
                String m = "No existe el usuario con login: " + value.toString();
                throw  new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, m, m));
            }
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
