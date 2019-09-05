package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.KeepFitMS.entity.Member;

/*
 * 会员持久层
 * */
@Mapper
public interface MemberDao {
	//获取所有用户
	List<Member> getAllMember();
	//添加用户,录入会员基本信息
	@Insert("insert into member (mid,mname,mtelephone,mdate,msfz,maddress,msex,coach_id,wardrobe_id,consultant_id,needs,icon)"
			+ "values (#{mid},#{mname},#{mtelephone},#{mdate},#{msfz},#{maddress},#{msex},#{coach_id},#{wardrobe_id},#{consultant_id},#{needs},#{icon})")
	int addMember(Member member);
	//通过ID查询用户
	
	Member getMemberByMid(int mid);
}
