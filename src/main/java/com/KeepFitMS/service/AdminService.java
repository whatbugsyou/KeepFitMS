package com.KeepFitMS.service;

import com.KeepFitMS.entity.Admin;

public interface AdminService {

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
