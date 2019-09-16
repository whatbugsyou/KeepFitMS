package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.JobDao;
import com.KeepFitMS.entity.Job;
import com.KeepFitMS.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobDao jobDao;
	
	@Override
	public List<Job> selectAllJob() {
		return jobDao.selectAllJob();
	}

	@Override
	public List<Job> selectJobByDeptId(int dept_id) {
		// TODO Auto-generated method stub
		return jobDao.selectJobByDeptId(dept_id);
	}

}
