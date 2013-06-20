/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller.conmparator;

import java.util.Comparator;
import sgps.model.proyecto.Fase;

/**
 *
 * @author uti
 */
public class FaseOrdenComparator implements Comparator<Fase>{

    @Override
    public int compare(Fase o1, Fase o2) {
        return new Integer(o1.getOrden()).compareTo(new Integer(o2.getOrden()));
    }
    
}
