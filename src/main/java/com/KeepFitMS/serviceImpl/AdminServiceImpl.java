package com.KeepFitMS.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.AdminDao;
import com.KeepFitMS.entity.Admin;
import com.KeepFitMS.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Resource
	private AdminDao adminDao;
	
	@Override
	public Admin loginUsername(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.loginUsername(admin);
	}

	@Override
	public Admin loginPassword(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.loginPassword(admin);
	}

	
}
