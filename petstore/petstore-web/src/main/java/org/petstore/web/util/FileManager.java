package org.petstore.web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;


public class FileManager{
	private List<File> unconfirmedUploadedFiles;
	
	public FileManager() {
		unconfirmedUploadedFiles = new ArrayList<>();
	}

    public void addUnconfirmedUploadedFile(File unconfirmedUploadedFile) {
        unconfirmedUploadedFiles.add(unconfirmedUploadedFile);
    }

    public void confirmUploadedFile(File confirmedUploadedFile) {
        unconfirmedUploadedFiles.remove(confirmedUploadedFile);
    }
    
    @PreDestroy
	public void destroy() {
		for (File unconfirmedUploadedFile : unconfirmedUploadedFiles) {
			unconfirmedUploadedFile.delete();
		}
	}  
}
