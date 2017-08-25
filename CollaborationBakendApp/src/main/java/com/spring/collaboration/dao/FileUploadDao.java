package com.spring.collaboration.dao;

import com.spring.collaboration.domain.FileUpload;

public interface FileUploadDao {
	public void save(FileUpload fileUpload, String username);
	public FileUpload getFile(String username);
}
