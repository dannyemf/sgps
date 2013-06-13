/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller;

import java.io.Serializable;

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
        
    }
    
    /**
     * Agrega un mensaje de error al componente indicado
     * @param clientId El id del componente
     * @param message El mensaje a presentar
     */
    public void addErrorMessage(String clientId, String message){
        
    }
    
}
