package com.spring.collaboration.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.FileUploadDao;
import com.spring.collaboration.domain.FileUpload;
import com.spring.collaboration.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{

	@Autowired
	FileUploadDao fileUploadDao;

	public void save(FileUpload fileUpload, String username) {
		fileUploadDao.save(fileUpload, username);
		
	}

	public FileUpload getFile(String username) {
		// TODO Auto-generated method stub
		return fileUploadDao.getFile(username);
	}
	
}
