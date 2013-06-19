/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sgps.model.seguridad.Menu;
import sgps.service.MenuServiceLocal;

/**
 *
 * @author uti
 */

@FacesConverter(value = "menuConverter")
public class MenuConverter implements Converter{
    MenuServiceLocal menuService = lookupMenuServiceLocal();

    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Menu m = null;
        
        try {
            Long id = Long.parseLong(value);
            m = menuService.buscarPorId(id);
        } catch (Exception e) {}
        
        return m;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Menu m = (Menu)value;
            return m.getId().toString();
        } catch (Exception e) {
            return "";
        }
    }

    private MenuServiceLocal lookupMenuServiceLocal() {
        try {
            Context c = new InitialContext();
            return (MenuServiceLocal) c.lookup("java:global/sgps/sgps-ejb/MenuService!sgps.service.MenuServiceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
