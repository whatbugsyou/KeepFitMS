package com.KeepFitMS.service;

import java.util.List;

import com.KeepFitMS.entity.Dept;
import com.KeepFitMS.exception.PersonnelServiceException;

public interface DeptService {
	public List<Dept> selectAllDept();

	public boolean addDept(Dept dept) throws PersonnelServiceException;
}
