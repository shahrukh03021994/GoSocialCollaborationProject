package com.spring.collaboration.service;

import java.util.List;

import com.spring.collaboration.domain.JobApplied;

public interface JobAppliedService {


public boolean applyNew(JobApplied jobApplied);
	
	public List<JobApplied> listByUser(String username);
	
	public List<JobApplied> listByCompany();
}
