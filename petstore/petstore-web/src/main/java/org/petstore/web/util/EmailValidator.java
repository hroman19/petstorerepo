package org.petstore.web.util;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.petstore.ejb.service.UserService;

public class EmailValidator implements Validator {

	@EJB
	private UserService userService;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		String email = value.toString();

		if (!email.matches(EMAIL_PATTERN)) {
			FacesMessage msg = new FacesMessage("Error", "Email is not valid.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		if(!isFreeEmail(email)){
			FacesMessage msg = new FacesMessage("Error", "Email is used.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
	
	private boolean isFreeEmail(String email){
		userService.getByEmail(email);
		return true;
	}

}
