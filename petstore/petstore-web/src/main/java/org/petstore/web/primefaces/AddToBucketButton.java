package org.petstore.web.primefaces;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.petstore.ejb.service.OrderService;

@ManagedBean
public class AddToBucketButton {
	
    public void buttonAction(ActionEvent actionEvent) {
        addMessage("You succerssfully this order to the bucket!");
    }
    
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}