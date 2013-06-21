/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.icefaces.ace.component.menuitem.MenuItem;
import org.icefaces.ace.component.submenu.Submenu;
import org.icefaces.ace.model.DefaultMenuModel;
import sgps.model.seguridad.Menu;
import sgps.model.seguridad.Usuario;
import sgps.service.MenuServiceLocal;

/**
 *
 * @author Danny Muñoz
 */

@Named
@SessionScoped
public class MenuBean extends Controller{
    
    @EJB
    private MenuServiceLocal service;
    
    private DefaultMenuModel menuModel = new DefaultMenuModel();
    
    public void init(Usuario usuario){
        
        menuModel = new DefaultMenuModel();
        
        List<Menu> menus =  service.obtenerMenus(usuario);
        
        for (Menu m : menus) {
            
            Submenu mi = new Submenu();
            mi.setLabel(m.getEtiqueta());
            mi.setIcon(m.getIcono());
            
            menuModel.addSubmenu(mi);
            
            this.crearMenu(usuario, mi, m);
        }
        
        //Crear
        MenuItem miIssues = new MenuItem();
        miIssues.setValue("Issues");        

        ELContext el = FacesContext.getCurrentInstance().getELContext();
        ExpressionFactory ef = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
        miIssues.setActionExpression(ef.createMethodExpression(el, "/proyectos/issues/lista.xhtml?faces-redirect=true", null, new Class[0]));
        menuModel.addMenuItem(miIssues);
        
    }
    
    private void crearMenu(Usuario usuario, Submenu mi, Menu menu){
        
        List<Menu> menus =  service.obtenerMenus(usuario, menu);
        
        for (Menu m : menus) {
            
            
            if(m.getControl() != null && m.getControl().trim().equals("") == false){
                MenuItem mii = new MenuItem();
                mii.setValue(m.getEtiqueta());
                mii.setIcon(m.getIcono());
                
                ELContext el = FacesContext.getCurrentInstance().getELContext();
		ExpressionFactory ef = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
                mii.setActionExpression(ef.createMethodExpression(el, m.getControl()+"?faces-redirect=true", null, new Class[0]));
		
                
                
                mi.getChildren().add(mii);                
            }else{
                Submenu mii = new Submenu();
                mii.setLabel(m.getEtiqueta());
                mii.setIcon(m.getIcono());
                mi.getChildren().add(mii);
                crearMenu(usuario, mii, m);
            }
        }
    }

    /**
     * @return the menuModel
     */
    public DefaultMenuModel getMenuModel() {
        return menuModel;
    }

    /**
     * @param menuModel the menuModel to set
     */
    public void setMenuModel(DefaultMenuModel menuModel) {
        this.menuModel = menuModel;
    }
    
}
