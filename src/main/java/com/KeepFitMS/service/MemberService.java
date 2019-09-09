package com.KeepFitMS.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.KeepFitMS.entity.Card;
import com.KeepFitMS.entity.Member;

public interface MemberService {
		//获取所有用户
		List<Member> getAllMember();
		//添加用户,录入会员基本信息
		int addMember(Member member,Card card);
		//根据ID查询会员
		Member getMemberByMid(int mid);
	
		//筛选会员
		List<Member> shaixuanMember(Member m);
		//通过ID删除会员，会员卡

		int deleteMemberByMid(int mid);
		
		//通过ID修改会员的手机号或地址
		int updateMemberByMid(Member m);
	
}
