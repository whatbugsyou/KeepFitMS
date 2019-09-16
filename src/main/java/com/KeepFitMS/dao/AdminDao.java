package com.KeepFitMS.dao;

import org.apache.ibatis.annotations.Mapper;

import com.KeepFitMS.entity.Admin;

@Mapper
public interface AdminDao {

	/**
	 * 判断用户名
	 * @param admin
	 * @return
	 */
	Admin loginUsername(Admin admin);
	
	/**
	 * 判断密码
	 * @param admin
	 * @return
	 */
	Admin loginPassword(Admin admin);
}
