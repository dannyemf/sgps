/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.service;

import javax.ejb.Stateless;
import sgps.dao.GenericoJPADAO;
import sgps.model.proyecto.Issue;

/**
 *
 * @author uti
 */
@Stateless
public class IssueService  extends GenericoJPADAO<Issue> implements IssueServiceLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
