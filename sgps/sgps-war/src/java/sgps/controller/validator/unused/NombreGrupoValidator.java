/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller.validator.unused;

import java.io.Serializable;
import java.lang.reflect.Method;
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
import javax.persistence.Query;
import sgps.service.GrupoServiceLocal;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author uti
 */

@FacesValidator(value="uniqueValidatorx")
public class NombreGrupoValidator implements Serializable, Validator{  
    
    GrupoServiceLocal service = lookupGrupoServiceLocal();
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        Object model = component.getAttributes().get("model");
        Object property = component.getAttributes().get("property");        
        Object id = getId(model);
        
        Query q = service.getEntityManager().createQuery("select count(*) from " + model.getClass().getSimpleName() + " m where m.id != " + id + " and m."+property + "='"+value+"'");
        Long l = (Long)q.getSingleResult();
        System.out.println("validate: "+ model + "-" + (model.getClass()) + "."+property+"-" + service);
        
        if(l > 0){            
            FacesMessage fm = new FacesMessage("Ya existen un registro con el mismo(a) " + property);
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);        
            throw  new ValidatorException(fm);
        }
        
    }
    
    public Object getId(Object model){
        try {
            Method m = model.getClass().getMethod("getId");
            return m.invoke(model);
        } catch (Exception e) {
            throw new NotImplementedException();
        }
    }

    private GrupoServiceLocal lookupGrupoServiceLocal() {
        try {
            Context c = new InitialContext();
            return (GrupoServiceLocal) c.lookup("java:global/sgps/sgps-ejb/GrupoService!sgps.service.GrupoServiceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
