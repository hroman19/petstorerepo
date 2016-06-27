package com.petstore.web.primefaces;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.petstore.ejb.service.ProductService;
import org.primefaces.event.SlideEndEvent;

@ManagedBean(eager=true)
@ApplicationScoped
public class SliderView {
	@EJB
	ProductService productService;
	
	private int minNumber ;
	private int maxNumber ;
	private int sliderDefaultMinNumber;
	private int sliderDefaultMaxNumber;
	
	@PostConstruct
	public void view() {
		setMinNumber((int) productService.getProductWithMinPrice());
		setMaxNumber((int)Math.ceil( productService.getProductWithMaxPrice()));
		setSliderDefaultMinNumber((int) productService.getProductWithMinPrice());
		setSliderDefaultMaxNumber((int)Math.ceil( productService.getProductWithMaxPrice()));
	}
	
	
	



	public void onSlideEnd(SlideEndEvent event) {
		FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void setSliderValues(int number1, int number2 ){
		setMinNumber(number1);
		setMaxNumber(number2);
	}






	public int getMinNumber() {
		return minNumber;
	}






	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}






	public int getMaxNumber() {
		return maxNumber;
	}






	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}






	public int getSliderDefaultMinNumber() {
		return sliderDefaultMinNumber;
	}






	public void setSliderDefaultMinNumber(int sliderDefaultMinNumber) {
		this.sliderDefaultMinNumber = sliderDefaultMinNumber;
	}






	public int getSliderDefaultMaxNumber() {
		return sliderDefaultMaxNumber;
	}






	public void setSliderDefaultMaxNumber(int sliderDefaultMaxNumber) {
		this.sliderDefaultMaxNumber = sliderDefaultMaxNumber;
	}



	
	
}
