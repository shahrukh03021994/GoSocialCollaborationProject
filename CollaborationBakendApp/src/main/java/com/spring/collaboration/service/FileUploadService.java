package com.spring.collaboration.service;

import com.spring.collaboration.domain.FileUpload;

public interface FileUploadService {

	public void save(FileUpload fileUpload, String username);
	public FileUpload getFile(String username);
}
