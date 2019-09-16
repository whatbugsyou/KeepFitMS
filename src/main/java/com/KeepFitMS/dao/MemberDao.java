package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.KeepFitMS.entity.M_record;
import com.KeepFitMS.entity.Member;

/*
 * 会员持久层
 * */
@Mapper
public interface MemberDao {
	//获取所有会员
	List<Member> getAllMember();
	//添加用户,录入会员基本信息
	@Insert("insert into member (mid,mname,mtelephone,mdate,msfz,maddress,msex,coach_id,needs,icon)"
			+ "values (#{mid},#{mname},#{mtelephone},#{mdate},#{msfz},#{maddress},#{msex},#{coach_id},#{needs},#{icon})")
	int addMember(Member member);
	//通过ID查询会员
	Member getMemberByMid(int mid);
	//通过ID删除会员
	@Delete("delete from member where mid = #{mid}")
	int deleteMemberByMid(int mid);

	
	//通过条件筛选会员
	List<Member> shaixuanMember(Member m);
	
	//通过ID修改会员的手机号或地址
	int updateMemberByMid(Member m);
}
