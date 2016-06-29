package org.petstore.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.petstore.common.model.Product;
import org.petstore.ejb.service.ProductService;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class AdminController implements Serializable {
	private static final String FOLDER_NAME = "D:\\petstore\\images";
	
	@ManagedProperty("#{productFilter}")
	private ProductFilter filter;
	
	@EJB
	private ProductService productService;

	private List<Product> products;

	private Product product = new Product();
	private Product productForEdit = new Product();

	private UploadedFile file;

	@PostConstruct
	private void init() {
		products = productService.getAll();
	}

	public void addProduct() {
		product.setIsDeleted(false);
		if (file.getSize() == 0)
			product.setImgUrl("product.png");
		productService.add(product);
		products.add(0, product);

		if (file.getSize() != 0) {

			createFolder(FOLDER_NAME);
			try {
				String fileName = saveImage();

				// update inserted product
				product.setImgUrl(fileName);
				productService.update(product);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		closeModal("newProductModal");
	}
	
	public void deleteProduct(Product product) {
		product.setIsDeleted(true);
		productService.update(product);
	}

	public void restoreProduct(Product product) {
		product.setIsDeleted(false);
		productService.update(product);
	}
	
	public void editProduct(){

		if (file.getSize() != 0) {

			createFolder(FOLDER_NAME);
			try {
				String fileName = saveImage();

				// update product
				productForEdit.setImgUrl(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		productService.update(productForEdit);

		closeModal("editProductModal");
	}

	/*public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		UploadedFile file = (UploadedFile) value;
		int fileByte = file.getContents().length;
		if (fileByte > 15360) {
			msgs.add(new FacesMessage("Too big, must be at most 15KB"));
		}
		if (!(file.getContentType().startsWith("image"))) {
			msgs.add(new FacesMessage("Not an Image file"));
		}
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}*/
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Product> getProducts() {
		return filter.filter(products);
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Product getProductForEdit() {
		return productForEdit;
	}
	
	public void setProductForEdit(Product productForEdit) {
		this.productForEdit = productForEdit;
	}
	
	public void setForEdit(){
		
		System.out.println("...");
	}

	public ProductFilter getFilter() {
		return filter;
	}

	public void setFilter(ProductFilter filter) {
		this.filter = filter;
	}
	
	
	

	private void createFolder(String name) {
		File theDir = new File(name);

		if (!theDir.exists()) {
			System.out.println();
			theDir.mkdirs();
		}
	}

	private String saveImage() throws IOException {
		String extension = FilenameUtils.getExtension(file.getFileName());
		String fileName = product.getId() + "." + extension;

		InputStream input = file.getInputstream();
		OutputStream output = new FileOutputStream(new File(FOLDER_NAME, fileName));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}

		return fileName;
	}

	private void closeModal(String modalName) {
		filter.refresh();
		product = new Product();
		RequestContext.getCurrentInstance().execute("$('#"+modalName+"').modal('hide')");
	}
}
