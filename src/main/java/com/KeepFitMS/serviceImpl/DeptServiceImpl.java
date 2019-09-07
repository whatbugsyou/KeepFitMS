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
			} else {
				return false;
			}
		}
		throw new PersonnelServiceException(PersonnelServiceExceptionEnum.DEPT_ALREADY_EXIST.getCode(),
				PersonnelServiceExceptionEnum.DEPT_ALREADY_EXIST.getMsg());

	}

	@Override
	public boolean modifyDept(Dept dept) throws PersonnelServiceException {
		// 检查部门名是否冲突
		Dept existDept = deptDao.selectDeptByDeptName(dept.getDept_name());
		if (existDept == null) {
			// 无冲突则修改部门
			if (deptDao.updateDept(dept) == 1) {
				return true;
			} else {
				return false;
			}
		}
		// 有冲突则抛出异常
		throw new PersonnelServiceException(PersonnelServiceExceptionEnum.DEPT_ALREADY_EXIST.getCode(),
				PersonnelServiceExceptionEnum.DEPT_ALREADY_EXIST.getMsg());

	}

	@Override
	public boolean deleteDept(int dept_id) throws PersonnelServiceException {
		//检查此部门下是否有员工,是否有职位仍需判断与解决（删除外键）
		Dept existDept = deptDao.selectDeptByDeptId(dept_id);
		if(existDept.getDept_memberNum()==0) {
			//没有员工则可以删除此部门
			if (deptDao.deleteDept(dept_id) == 1) {
				return true;
			} else {
				return false;
			}
		}
		//有则抛出异常
		throw new PersonnelServiceException(PersonnelServiceExceptionEnum.REFUSE_DELETEING_DEPT_COUSE_EMP_NOT_EMPTY.getCode(),
				PersonnelServiceExceptionEnum.REFUSE_DELETEING_DEPT_COUSE_EMP_NOT_EMPTY.getMsg());


	}

	@Override
	public boolean increaseDeptMemberNumByDetpId(int dept_id) {
		if(deptDao.increaseDeptMemberNumByDetpId(dept_id)==1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean decreaseDeptMemberNumByDetpId(int dept_id) {
		if(deptDao.decreaseDeptMemberNumByDetpId(dept_id)==1) {
			return true;
		}
		return false;
	}

}
