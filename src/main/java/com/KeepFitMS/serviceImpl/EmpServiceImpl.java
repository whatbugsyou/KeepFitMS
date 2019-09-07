package com.KeepFitMS.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KeepFitMS.dao.EmpDao;
import com.KeepFitMS.entity.Emp;
import com.KeepFitMS.exception.PersonnelServiceException;
import com.KeepFitMS.exception.PersonnelServiceExceptionEnum;
import com.KeepFitMS.service.DeptService;
import com.KeepFitMS.service.EmpService;


@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao empDao;
	@Autowired
	private DeptService deptService;
	
	@Override
	public List<Emp> selectAllEmp() {
		// TODO Auto-generated method stub
		return empDao.selectAllEmp();
	}

	@Override
	@Transactional //使用事务
	public boolean addEmp(Emp emp) throws PersonnelServiceException {
		// 检查员工是否重复，通过员工名字和手机号
		Emp existEmp = empDao.selectEmpByEmpName(emp.getEmp_name());
		if (existEmp != null && existEmp.getEmp_name().equals(emp.getEmp_name())
				&& existEmp.getEmp_phone().equals(emp.getEmp_phone())) {
			// 重复则抛出异常
			throw new PersonnelServiceException(PersonnelServiceExceptionEnum.EMP_ALREADY_EXIST.getCode(),
					PersonnelServiceExceptionEnum.EMP_ALREADY_EXIST.getMsg());
		} else {
			// 不重复则添加员工
			if (empDao.insertEmp(emp) == 1) {//事务目标------1
				//添加员工成功，修改其部门人数+1 事务目标------2
				deptService.increaseDeptMemberNumByDetpId(emp.getDept().getDept_id());
				//填充数据回前台对象 直接emp=empDao.selectEmpByEmpId(emp.getEmp_id())是不行的。因为emp参数值是值传递。
				Emp newEmp = empDao.selectEmpByEmpId(emp.getEmp_id());
				emp.setJob(newEmp.getJob());
				emp.setDept(newEmp.getDept());
			}
		}
		return false;

	}

	@Override
	@Transactional //使用事务
	public boolean deleteEmp(int emp_id) {
		//查询原部门id
		int dept_id = empDao.selectDeptIdByEmpId(emp_id);
		if (empDao.deleteEmp(emp_id) == 1) { //事务目标---1
			//删除成功，修改其部门人数-1
			deptService.decreaseDeptMemberNumByDetpId(dept_id); //事务目标----2
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional //使用事务
	public boolean modifyEmp(Emp emp) {
		//查出未修改前的员工
		Emp existEmp = empDao.selectEmpByEmpId(emp.getEmp_id());
		if (empDao.updateEmp(emp) == 1) { //事务目标----1
			//如果修改了部门信息
			int preDeptID=existEmp.getDept().getDept_id();
			int nowDeptID=emp.getDept().getDept_id();
			if(preDeptID!=nowDeptID) {
				deptService.decreaseDeptMemberNumByDetpId(preDeptID); //事务目标----2
				deptService.increaseDeptMemberNumByDetpId(nowDeptID); //事务目标----3
			}
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<Emp> selectEmpByDeptId(int dept_id) {
		List<Emp> empsInSameDept = empDao.selectEmpByDeptId(dept_id);
		return empsInSameDept;
	}
}
