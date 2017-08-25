package com.spring.collaboration.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.JobDao;
import com.spring.collaboration.domain.Job;
import com.spring.collaboration.service.JobService;

@Service
public class JobServiceImpl implements JobService{

	@Autowired
	JobDao jobDao;
	public boolean addJob(Job job) {
		// TODO Auto-generated method stub
		return jobDao.addJob(job);
	}

	public Job getJob(String name) {
		// TODO Auto-generated method stub
		return jobDao.getJob(name);
	}

	public List<Job> listJobs() {
		// TODO Auto-generated method stub
		return jobDao.listJobs();
	}

	public boolean deleteJob(int job_id) {
		// TODO Auto-generated method stub
		return jobDao.deleteJob(job_id);
	}

	public boolean invalidateJob(int job_id) {
		// TODO Auto-generated method stub
		return jobDao.invalidateJob(job_id);
	}

}
