/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.icefaces.util.JavaScriptRunner;

/**
 * Clase base con métodos de utilidad del que debe heredar todo controlador
 * 
 * @author Danny Muñoz
 */
public class Controller implements Serializable{
    
    /**
     * Agrega un mensaje de infomración al componente indicado
     * @param clientId El id del componente
     * @param message El mensaje a presentar
     */
    public void addInfoMessage(String clientId, String message){
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
    
    /**
     * Agrega un mensaje de error al componente indicado
     * @param clientId El id del componente
     * @param message El mensaje a presentar
     */
    public void addErrorMessage(String clientId, String message){
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
    
    public void runScript(String js){
        JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), js); 
    }
    
}
