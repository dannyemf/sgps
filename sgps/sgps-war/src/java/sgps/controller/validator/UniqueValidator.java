/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller.validator;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.PartialStateHolder;
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
 * Validador de entidades con propiedades únicas. 
 * Por ejemplo en usuario el login, en un producto el código.
 * 
 * La validacion se hace importando el tag: xmlns:s="http://sgps/jsf-custom-components/"
 * 
 * 
 * @author Danny Muñoz
 */

@FacesValidator(value = "uniqueValidator")
public class UniqueValidator  implements Validator, PartialStateHolder{  
    
    /*public static final String VALIDATOR_ID = "javax.faces.Length";*/
    
    /**
     * La entidad a validar
     */
    private Object entity;
    
    /**
     * La propiedad a verificar
     */
    private String property;
    
    /**
     * El mensaje de validación personalizado a presentar
     */
    private String message;
    
    private boolean transientValue = false;
    private boolean initialState;
    
    private GrupoServiceLocal service = lookupGrupoServiceLocal();
    
    public UniqueValidator() {
        super();
    }       
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        
        Object id = getId();
        
        Query q = service.getEntityManager().createQuery("select count(*) from " + entity.getClass().getSimpleName() + " m where m.id != " + id + " and m."+property + "='"+value+"'");
        Long l = (Long)q.getSingleResult();
        System.out.println("validate: "+ entity + "-" + (entity.getClass()) + "."+property+"-" + service);
        
        if(l > 0){            
            FacesMessage fm = new FacesMessage(message != null ? message : "Ya existen un registro con el mismo(a) " + property);
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);        
            throw  new ValidatorException(fm);
        }        
    }
    
    public Object getId(){
        try {
            Method m = entity.getClass().getMethod("getId");
            return m.invoke(entity);
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
    
    /**
     * @return the entity
     */
    public Object getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(Object entity) {
        this.entity = entity;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    // ----------------------------------------------------- StateHolder Methods


    public Object saveState(FacesContext context) {
        if (context == null) {
            throw new NullPointerException();
        }
        if (!initialStateMarked()) {
            Object values[] = new Object[3];
            values[0] = entity;
            values[1] = property;
            values[2] = message;
            
            return (values);
        }
        return null;
    }


    public void restoreState(FacesContext context, Object state) {
        if (context == null) {
            throw new NullPointerException();
        }
        if (state != null) {
            Object values[] = (Object[]) state;
            entity = (Object) values[0];
            property = (String) values[1];
            message = (String) values[2];
        }

    }    


    @Override
    public boolean isTransient() {
        return (this.transientValue);
    }

    @Override
    public void setTransient(boolean transientValue) {
        this.transientValue = transientValue;
    }   

    @Override
    public void markInitialState() {
        initialState = true;
    }

    @Override
    public boolean initialStateMarked() {
        return initialState;
    }

    @Override
    public void clearInitialState() {
        initialState = false;
    }    
    
}
