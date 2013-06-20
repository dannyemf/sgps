/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.util;

/**
 *
 * @author uti
 */
public class StringUtil {
    
    public static boolean isNullOrEmpty(Object objeto){
        
        if(objeto == null){
            return true;
        }else{
            String s = objeto.toString();
            return (s.trim().length() == 0);
        }
    }
}
