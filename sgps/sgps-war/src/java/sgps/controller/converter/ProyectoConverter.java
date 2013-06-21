/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sgps.model.proyecto.Proyecto;
import sgps.service.ProyectoServiceLocal;

/**
 *
 * @author uti
 */

@FacesConverter(value = "proyectoConverter")
public class ProyectoConverter implements Converter{
    
    ProyectoServiceLocal proyectoService = lookupProyectoServiceLocal();   
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Proyecto m = null;
        
        try {
            Long id = Long.parseLong(value);
            m = proyectoService.buscarPorId(id);
        } catch (Exception e) {}
        
        return m;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Proyecto m = (Proyecto)value;
            return m.getId().toString();
        } catch (Exception e) {
            return "";
        }
    }

    private ProyectoServiceLocal lookupProyectoServiceLocal() {
        try {
            Context c = new InitialContext();
            return (ProyectoServiceLocal) c.lookup("java:global/sgps/sgps-ejb/ProyectoService!sgps.service.ProyectoServiceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
    
}
