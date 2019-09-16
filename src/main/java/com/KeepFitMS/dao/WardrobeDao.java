package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KeepFitMS.entity.Wardrobe;
import com.KeepFitMS.entity.Wardroberecord;
/**
 * @衣柜管理接口
 * @author Lwg
 *
 */
@Mapper
public interface WardrobeDao {
	/**
	 * @apiNote 查询所有衣柜
	 * @param 无
	 * @return 衣柜集合
	 */
	@Select("select * from wardrobe order by wardrobe_name asc")
	List<Wardrobe> selectAllW();
	
	/**
	 * @apiNote 查询所有衣柜出租记录
	 * @param 无
	 * @return 衣柜出租记录集合
	 */
	@Select("select * from wardroberecords group by wr_status desc,wr_regtime desc")
	List<Wardroberecord> selectAllWR();
	
	/**
	 * @apiNote 根据衣柜出租记录的id查询衣柜出租记录
	 * @param Integer wr_id
	 * @return 衣柜出租记录
	 */
	@Select("select * from wardroberecords where wr_id = #{wr_id}")
	Wardroberecord selectWRById(Integer wr_id);
	
	/**
	 * @apiNote 查询衣柜是否已经被租
	 * @param wardrobe_name
	 * @return 返回status的值
	 */
	@Select("select wardrobe_status from wardrobe where wardrobe_name=#{wardrobe_name}")
	int selectWStatusByName(String wardrobe_name);
	
	/**
	 * @apiNote 根据衣柜名称修改衣柜的状态
	 * @param Integer wstatus, String wname
	 * @return void
	 */
	@Update("update wardrobe set wardrobe_status=#{wardrobe_status} where wardrobe_name=#{wardrobe_name}")
	void updateWByWname(@Param("wardrobe_status") Integer wstatus,@Param("wardrobe_name") String wname);
	
	/**
	 * @apiNote 修改衣柜出租记录
	 * @param Wardroberecord wardroberecord
	 * @return void
	 */
	@Update("update wardroberecords set cid=#{cid},mname=#{mname},wardrobe_name=#{wardrobe_name},wr_regtime=#{wr_regtime},wr_status=#{wr_status},"
			+ "wr_starttime=#{wr_starttime},wr_endtime=#{wr_endtime},wr_deposit=#{wr_deposit},wr_desc=#{wr_desc} where wr_id=#{wr_id}")
	void updateWR(Wardroberecord wardroberecord);
	
	/**
	 * @apiNote 插入衣柜出租记录
	 * @param Wardroberecord wardroberecord
	 * @return void
	 */
	@Insert("insert into wardroberecords(cid,mname,wardrobe_name,wr_regtime,wr_status,wr_starttime,wr_endtime,wr_deposit,wr_desc) "
			+ "values(#{cid},#{mname},#{wardrobe_name},#{wr_regtime},#{wr_status},#{wr_starttime},#{wr_endtime},#{wr_deposit},#{wr_desc})")
	void insertWR(Wardroberecord wardroberecord);

}
