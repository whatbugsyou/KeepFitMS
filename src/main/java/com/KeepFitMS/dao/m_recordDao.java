package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.KeepFitMS.entity.M_record;

/*
 * 会员来访记录持久层
 * */
@Mapper
public interface m_recordDao {
	//获取所有记录
	List<M_record> getAllM_records();
	//分页查询记录
	@Select("SELECT *FROM m_record ORDER BY edate DESC limit #{firstParm},#{lastParam}")
	List<M_record> getM_recordsByTimeLimit(@Param("firstParm")int firstParm,@Param("lastParam")int lastParam);
	//用户每次到店记录
	@Insert("insert into m_record (mid,sdate,edate,remarks) values(#{mid},#{sdate},#{edate},#{remarks})")
	int addRecord(M_record mr);
}
