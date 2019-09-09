package com.KeepFitMS.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.UnmemberDao;
import com.KeepFitMS.entity.Unmember;
import com.KeepFitMS.service.UnmemberService;
@Service
public class UnmemberServiceImpl implements UnmemberService {
	@Autowired
	private UnmemberDao ud;
	@Override
	public int addUnmember(Unmember m) {
		// TODO Auto-generated method stub
		return ud.addUnmember(m);
	}

}
