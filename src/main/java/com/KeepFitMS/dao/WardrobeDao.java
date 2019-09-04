package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KeepFitMS.entity.Wardrobe;
import com.KeepFitMS.entity.Wardroberecord;

@Mapper
public interface WardrobeDao {
	
	@Select("select * from wardrobe")
	List<Wardrobe> selectAllW();
	
	@Select("select * from wardroberocords")
	List<Wardroberecord> selectAllWR();
	
	@Update("update wardrobe set wardrobe_name=#{wardrobe_name},wardrobe_status=#{wardrobe_status} where wardrobe_id=#{wardrobe_id}")
	int updateW(Wardrobe wardrobe);
	
	@Update("update wardroberocords set cid=#{cid},mname=#{mname},wardrobe_name=#{wardrobe_name},wr_regtime=#{wr_regtime},wr_status=#{wr_status}"
			+ "wr_starttime=#{wr_starttime},wr_endtime=#{wr_endtime},wr_deposit=#{wr_deposit},wr_desc=#{wr_desc} where wr_id=#{wr_id}")
	int updateWR(Wardroberecord wardroberecord);
	
	@Insert("insert into wardroberocords(cid,mname,wardrobe_name,wr_regtime,wr_status,wr_starttime,wr_endtime,wr_deposit,wr_desc) "
			+ "values(#{cid},#{mname},#{wardrobe_name},#{wr_regtime},#{wr_status},#{wr_starttime},#{wr_endtime},#{wr_deposit},#{wr_desc})")
	int insertWR(Wardroberecord wardroberecord);
	
	
	
	

}
