package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.KeepFitMS.entity.Goods;
import com.KeepFitMS.entity.Pctype;
import com.KeepFitMS.entity.Ptype;
/**
 * 商品模块的持久层接口
 * @author suyin
 *
 */
@Mapper
public interface GoodsDao {
	/**
	 * 使用内部类完成动态sql查询商品集合，关联查询父类型属性和子类型属性
	 * @param goods_name
	 * @param ptype_id
	 * @param pctype_id
	 * @param curr
	 * @param limit
	 * @return List<Goods>
	 */
	 @SelectProvider(type = GoodsDaoProvider.class, method = "findGoodsList")  
	@Results(//这里设计的是，商品对父类型以及子类型是多对一关系
		id = "allGoodsTypeCtype",value ={
		@Result(property = "ptype" ,column = "ptype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPtypeById")),
		@Result(property = "pctype",column = "pctype_id",
				one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPctypeByPctypeid"))
	})
	List<Goods> selectGoods(String goods_name, Integer ptype_id, Integer pctype_id, Integer curr, Integer limit);
	 

	 /**
	  * 查询条数
	  * @return
	  */
	 @SelectProvider(type = GoodsDaoProvider.class, method = "findGoodsListCount")  
	Integer selectAllCount(String goods_name, Integer ptype_id, Integer pctype_id); 

	 
	 
	 /**
	  * 内部类
	  * @author suyin
	  *
	  */
	 class GoodsDaoProvider {  
	        public String findGoodsList(String goods_name, Integer ptype_id, Integer pctype_id, Integer curr, Integer limit) {  
	            String sql = "SELECT goods_id,goods_img,goods_name,goods_status,goods_price,goods_num,ptype_id,pctype_id FROM goods";  
	            if(goods_name!=null&&goods_name!=""){  
	                sql += " where goods_name = #{goods_name}";  
	            }  
	            if(ptype_id!=null&&ptype_id!=0&&pctype_id==null&&pctype_id==0) {
	            	 sql += " where ptype_id = #{ptype_id}";  
	            }
	            if(ptype_id!=null&&ptype_id!=0&&pctype_id!=null&&pctype_id!=0) {
	            	sql += " where ptype_id = #{ptype_id} and pctype_id = #{pctype_id}";  
	            }
	            sql+=" limit #{curr},#{limit}";
	            return sql;  
	        }  
	        
	        public String findGoodsListCount(String goods_name, Integer ptype_id, Integer pctype_id) {  
	            String sql = "SELECT count(*) FROM goods";  
	            if(goods_name!=null&&goods_name!=""){  
	                sql += " where goods_name = #{goods_name}";  
	            }  
	            if(ptype_id!=null&&ptype_id!=0&&pctype_id==null&&pctype_id==0) {
	            	 sql += " where ptype_id = #{ptype_id}";  
	            }
	            if(ptype_id!=null&&ptype_id!=0&&pctype_id!=null&&pctype_id!=0) {
	            	sql += " where ptype_id = #{ptype_id} and pctype_id = #{pctype_id}";  
	            }
	            return sql;  
	        }  
	 }
	/**
	 * 根据提供的父类型id查父类型属性
	 * @param ptype_id
	 * @return Ptype
	 */
	@Select("select ptype_id,ptype_name from ptype where ptype_id = #{ptype_id}")
	Ptype selectPtypeById(Integer ptype_id);
	

	/**
	 * 根据提供的子类型id查子类型属性
	 * @param pctype_id
	 * @return Pctype
	 */
	@Select("select pctype_id,pctype_name from pctype where pctype_id = #{pctype_id}")
	Pctype selectPctypeByPctypeid(Integer pctype_id);
	
	/**
	 * 查所有的父类型
	 * @param
	 * @return List<Ptype>
	 */
	@Select("select ptype_id,ptype_name from ptype")
	List<Ptype> selectAllPtype();

	/**
	 * 根据父类型id查子类所有属性
	 * @param ptype_id
	 * @return List<Pctype>
	 */
	@Select("select pctype_id,pctype_name from pctype where ptype_id =#{ptype_id} ")
	List<Pctype> selectPctypeByPtypeId(Integer ptype_id);

	/**
	 * 修改商品状态
	 * @param goods_id
	 * @param status
	 * @return
	 */
	@Update("update goods set goods_status = #{goods_status} where goods_id=#{goods_id}")
	void updateGoodsStatus(Integer goods_id, Boolean goods_status);
	

	
}
