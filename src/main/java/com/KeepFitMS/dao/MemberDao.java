package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.KeepFitMS.entity.Member;

/*
 * 会员持久层
 * */
@Mapper
public interface MemberDao {
	//获取所有用户
	List<Member> getAllMember();
	//添加用户,录入会员基本信息
	@Insert("insert into member (mname,mtelephone,mdate,msfz,maddress,msex,coach_id,wardrobe_id,consultant_id,needs,icon)"
			+ "values (#{mname},#{mtelephone},#{mdate},#{msfz},#{maddress},#{msex},#{coach_id},#{wardrobe_id},#{consultant_id},#{needs},#{icon})")
	int addMember(Member member);
	
}
