package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.KeepFitMS.entity.Goods;
import com.KeepFitMS.entity.Pctype;
import com.KeepFitMS.entity.Ptype;

@Mapper
public interface GoodsDao {
	
	
	/**
	 * 查询所有商品信息集合以及父类型子类型
	 * @param
	 * @return List<Goods>
	 */
	@Select("select * from goods")
	@Results(//这里设计的是，商品对父类型以及子类型是多对一关系
		id = "allGoodsTypeCtype",value ={
		@Result(property = "ptype" ,column = "ptype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPtypeById")),
		@Result(property = "pctype",column = "pctype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPctypeByPctypeid"))
	})
	List<Goods> selectAllGoods();
	

	/**
	 * 根据提供的父类型id查父类型属性
	 * @param ptype_id
	 * @return Ptype
	 */
	@Select("select * from ptype where ptype_id = #{ptype_id}")
	Ptype selectPtypeById(Integer ptype_id);
	

	/**
	 * 根据提供的子类型id查子类型属性
	 * @param pctype_id
	 * @return Pctype
	 */
	@Select("select * from pctype where pctype_id = #{pctype_id}")
	Pctype selectPctypeByPctypeid(Integer pctype_id);
	
	/**
	 * 查所有的父类型
	 * @param
	 * @return List<Ptype>
	 */
	@Select("select * from ptype")
	List<Ptype> selectAllPtype();

	/**
	 * 根据父类型id查子类所有属性
	 * @param ptype_id
	 * @return List<Pctype>
	 */
	@Select("select * from pctype where ptype_id =#{ptype_id} ")
	List<Pctype> selectPctypeByPtypeId(Integer ptype_id);
	
	/**
	 * 根据父类型id查询所有商品信息集合以及父类型子类型
	 * @param provid
	 * @return List<Goods>
	 */
	@Select("select * from goods where ptype_id = #{ptype_id} ")
	@Results(//这里设计的是，商品对父类型以及子类型是多对一关系
		id = "allGoodsTypeCtypeByPtypeId",value ={
		@Result(property = "ptype" ,column = "ptype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPtypeById")),
		@Result(property = "pctype",column = "pctype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPctypeByPctypeid"))
	})
	List<Goods> selectAllGoodsByPtypeId(Integer ptype_id);

	/**
	 * 根据父类型id和子类型id查询所有商品信息集合以及父类型子类型
	 * @param ptype_id
	 * @param pctype_id
	 * @return List<Goods>
	 */
	@Select("select * from goods where ptype_id = #{ptype_id} and pctype_id=#{pctype_id} ")
	@Results(//这里设计的是，商品对父类型以及子类型是多对一关系
		id = "allGoodsTypeCtypeByPtypeIdAndPctypeId",value ={
		@Result(property = "ptype" ,column = "ptype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPtypeById")),
		@Result(property = "pctype",column = "pctype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPctypeByPctypeid"))
	})
	List<Goods> selectAllGoodsByPtypeIdAndPctype(Integer ptype_id, Integer pctype_id);

	/**
	 * 
	 * @param goods_name
	 * @return List<Goods>
	 */
	@Select("select * from goods where goods_name = #{goods_name} ")
	@Results(//这里设计的是，商品对父类型以及子类型是多对一关系
		id = "allGoodsTypeCtypeByName",value ={
		@Result(property = "ptype" ,column = "ptype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPtypeById")),
		@Result(property = "pctype",column = "pctype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPctypeByPctypeid"))
	})
	List<Goods> selectAllGoodsByName(String goods_name);

}
