package com.KeepFitMS.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.KeepFitMS.entity.Unmember;

/*
 * 游客dao层
 * */
@Mapper
public interface UnmemberDao {
	//添加游客
	@Insert("insert into unmember (umname,umtelephone) values(#{umname},#{umtelephone})")
	public int addUnmember(Unmember m);
}
