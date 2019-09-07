package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KeepFitMS.entity.Wardrobe;
import com.KeepFitMS.entity.Wardroberecord;

@Mapper
public interface WardrobeDao {
	
	@Select("select * from wardrobe order by wardrobe_name asc")
	List<Wardrobe> selectAllW();
	
	@Select("select * from wardroberecords group by wr_status desc,wr_regtime desc")
	List<Wardroberecord> selectAllWR();
	
	@Select("select * from wardroberecords where wr_id = #{wr_id}")
	Wardroberecord selectWRById(Integer wr_id);
	
	@Update("update wardrobe set wardrobe_status=#{wardrobe_status} where wardrobe_name=#{wardrobe_name}")
	int updateWByWname(@Param("wardrobe_status") Integer wstatus,@Param("wardrobe_name") String wname);
	
	@Update("update wardroberecords set cid=#{cid},mname=#{mname},wardrobe_name=#{wardrobe_name},wr_regtime=#{wr_regtime},wr_status=#{wr_status},"
			+ "wr_starttime=#{wr_starttime},wr_endtime=#{wr_endtime},wr_deposit=#{wr_deposit},wr_desc=#{wr_desc} where wr_id=#{wr_id}")
	int updateWR(Wardroberecord wardroberecord);
	
	@Insert("insert into wardroberecords(cid,mname,wardrobe_name,wr_regtime,wr_status,wr_starttime,wr_endtime,wr_deposit,wr_desc) "
			+ "values(#{cid},#{mname},#{wardrobe_name},#{wr_regtime},#{wr_status},#{wr_starttime},#{wr_endtime},#{wr_deposit},#{wr_desc})")
	int insertWR(Wardroberecord wardroberecord);
	
	
	
	

}
