package com.KeepFitMS.service;

import java.util.List;

import com.KeepFitMS.entity.Member;

public interface MemberService {
		//获取所有用户
		List<Member> getAllMember();
		//添加用户,录入会员基本信息
		int addMember(Member member);
}
