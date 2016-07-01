package org.petstore.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.petstore.common.model.Product;
import org.petstore.ejb.service.ProductService;
import org.petstore.web.util.FileManager;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ViewScoped
@ManagedBean
public class AdminController implements Serializable {
	private static final String FOLDER_NAME = "\\\\EPUALVIW1475\\resources\\images";

	@ManagedProperty("#{productFilter}")
	private ProductFilter filter;
	
	private FileManager fileManager;

	@EJB
	private ProductService productService;

	private List<Product> products;

	private Product product = new Product();
	private Product productForEdit = new Product();

	private File currentFile;

	@PostConstruct
	private void init() {
		products = productService.getAll();
		fileManager = new FileManager();
	}
	
	@PreDestroy
	private void destroy(){
		fileManager.destroy();
	}

	public void addProduct() {
		product.setIsDeleted(false);
		if (currentFile == null)
			product.setImgUrl("product.png");
		productService.add(product);
		products.add(0, product);

		if (currentFile != null) {

			try {
				String fileName = renameCurrentImage(product.getId());

				// update inserted product
				product.setImgUrl(fileName);
				productService.update(product);
				
				fileManager.confirmUploadedFile(currentFile);
			} catch (Exception e) {
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

	public void editProduct() {
		
		System.out.println("EDIT PRODUCT currentFile: "+currentFile);

		if (currentFile != null) {
			
			try {
				String fileName = renameCurrentImage(productForEdit.getId());

				// update product
				productForEdit.setImgUrl(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		productService.update(productForEdit);

		closeModal("editProductModal");
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile file = event.getFile();

        try(InputStream input = file.getInputstream()){

            File folder= new File(FOLDER_NAME);

            String filename = FilenameUtils.getBaseName(file.getFileName()); 
            String extension = FilenameUtils.getExtension(file.getFileName());
            
            currentFile = Files.createTempFile(folder.toPath(), filename + "-", "." + extension).toFile();
            Files.copy(input, currentFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            fileManager.addUnconfirmedUploadedFile(currentFile);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", event.getFile().getFileName() + " is uploaded"));

        } catch (IOException e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR I/O", "Code of the error: DC1"));
            e.printStackTrace();
        }
	}

	/*
	 * public void validateFile(FacesContext ctx, UIComponent comp, Object
	 * value) { List<FacesMessage> msgs = new ArrayList<FacesMessage>();
	 * UploadedFile file = (UploadedFile) value; int fileByte =
	 * file.getContents().length; if (fileByte > 15360) { msgs.add(new
	 * FacesMessage("Too big, must be at most 15KB")); } if
	 * (!(file.getContentType().startsWith("image"))) { msgs.add(new
	 * FacesMessage("Not an Image file")); } if (!msgs.isEmpty()) { throw new
	 * ValidatorException(msgs); } }
	 */

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public ProductFilter getFilter() {
		return filter;
	}

	public void setFilter(ProductFilter filter) {
		this.filter = filter;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	private String renameCurrentImage(Integer productId) throws IOException {
		String extension = FilenameUtils.getExtension(currentFile.getName());
		String fileName = productId + "." + extension;
		
		System.out.println("renameCurrentImage fileName: "+fileName);
		
		File newFile = new File(FOLDER_NAME + "\\"+fileName);
		newFile.delete();
		currentFile.renameTo(newFile);
		return fileName;
	}

	private void closeModal(String modalName) {
		filter.refresh();
		product = new Product();
		RequestContext.getCurrentInstance().execute("$('#" + modalName + "').modal('hide')");
	}
}
