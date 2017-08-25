package com.spring.collaboration.dao;

import java.util.List;

import com.spring.collaboration.domain.Job;

public interface JobDao {

    public boolean addJob(Job job);
	
	public Job getJob(String name);
	
	public List<Job> listJobs();
	
	public boolean deleteJob(int job_id);
	
	public boolean invalidateJob(int job_id);
}
