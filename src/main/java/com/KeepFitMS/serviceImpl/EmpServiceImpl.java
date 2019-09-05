package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.EmpDao;
import com.KeepFitMS.entity.Emp;
import com.KeepFitMS.service.EmpService;
@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpDao empdao;
	@Override
	public List<Emp> selectAllEmp() {
		// TODO Auto-generated method stub
		return empdao.selectAllEmp();
	}
}
