/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgps.controller.validator;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Scope;


/**
 *
 * @author uti
 */
@FacesValidator(value = "jpaUniqueValidator")
@FacesComponent(value = "jpaUniqueValidator")
public class JpaUniqueValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (entity == null && property == null) {
            return;
        }

        if (entity == null) {
            throw new IllegalStateException("Missing 'entity' attribute");
        }

        if (property == null) {
            throw new IllegalStateException("Missing 'property' attribute");
        }

        /*if (!jpaUniqueSupport.isUnique(entity, property, value)) {
            FacesMessage fm = new FacesMessage(resourcesUtil.getProperty(entity.getClass().getSimpleName().toLowerCase() + "_" + property + "_already_exists"));
            fm.setSeverity(SEVERITY_ERROR);
            throw new ValidatorException(fm);
        }*/
    }

    private Object entity;
    private String property;

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}

