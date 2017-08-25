package com.spring.collaboration.dao;

import java.util.List;

import com.spring.collaboration.domain.JobApplied;

public interface JobAppliedDao {

public boolean applyNew(JobApplied jobApplied);
	
	public List<JobApplied> listByUser(String username);
	
	public List<JobApplied> listByCompany();
}
