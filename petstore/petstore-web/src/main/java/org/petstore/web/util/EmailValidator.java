package org.petstore.web.util;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.petstore.ejb.service.UserService;

@ManagedBean
@RequestScoped
public class EmailValidator implements Validator {

	@EJB
	private UserService userService;

	private boolean valid;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		String email = value.toString();

		if (!email.matches(EMAIL_PATTERN)) {
			FacesMessage msg = new FacesMessage("Error", "Email is not valid.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			valid = false;
			FacesContext.getCurrentInstance().validationFailed();
			throw new ValidatorException(msg);
		}

		if (!isFreeEmail(email)) {
			FacesMessage msg = new FacesMessage("Error", "Email is used.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			valid = false;
			FacesContext.getCurrentInstance().validationFailed();
			throw new ValidatorException(msg);
		}
		valid = true;
	}

	private boolean isFreeEmail(String email) {
		return userService.getByEmail(email) == null;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
