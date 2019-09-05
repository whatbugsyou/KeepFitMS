package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeepFitMS.dao.DeptDao;
import com.KeepFitMS.entity.Dept;
import com.KeepFitMS.exception.PersonnelServiceException;
import com.KeepFitMS.exception.PersonnelServiceExceptionEnum;
import com.KeepFitMS.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao deptDao;

	@Override
	public List<Dept> selectAllDept() {
		return deptDao.selectAllDept();

	}

	@Override
	public boolean addDept(Dept dept) throws PersonnelServiceException {
		// 检查部门是否已经存在
		Dept existDept = deptDao.selectDeptByDeptName(dept.getDept_name());
		if (existDept == null) {
			// 添加部门
			if (deptDao.insertDept(dept) == 1) {
				return true;
			}
		}
		throw new PersonnelServiceException(PersonnelServiceExceptionEnum.DEPT_ALREADY_EXIST.getCode(),
				PersonnelServiceExceptionEnum.DEPT_ALREADY_EXIST.getMsg());

	}

}
