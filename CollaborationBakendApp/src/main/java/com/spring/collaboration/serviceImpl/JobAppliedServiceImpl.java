package com.spring.collaboration.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.JobAppliedDao;
import com.spring.collaboration.domain.JobApplied;
import com.spring.collaboration.service.JobAppliedService;


@Service
public class JobAppliedServiceImpl implements JobAppliedService{

	@Autowired
	JobAppliedDao jobAppliedDao;
	
	public boolean applyNew(JobApplied jobApplied) {
		// TODO Auto-generated method stub
		return jobAppliedDao.applyNew(jobApplied);
	}

	public List<JobApplied> listByUser(String username) {
		// TODO Auto-generated method stub
		return jobAppliedDao.listByUser(username);
	}

	public List<JobApplied> listByCompany() {
		// TODO Auto-generated method stub
		return jobAppliedDao.listByCompany();
	}

}
