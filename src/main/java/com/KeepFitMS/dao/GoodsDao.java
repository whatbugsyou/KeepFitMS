package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

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
	//调用内部类动态sql查询查询
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
	  * 使用内部类完成动态sql 查询条数
	  * @return Integer
	  */
	 @SelectProvider(type = GoodsDaoProvider.class, method = "findGoodsListCount")  
	Integer selectAllCount(String goods_name, Integer ptype_id, Integer pctype_id); 

	 /**
	  * 使用内部类完成动态sql 修改商品
	  * @param goods
	  * @return boolean
	  */
		@UpdateProvider(type= GoodsDaoProvider.class,method="changeGoodsList")
		boolean updateGoods(Goods goods);
		
		/**
		 * 使用内部类完成动态sql 增加商品
		 * @param goods
		 * @return boolean
		 */
		@InsertProvider(type= GoodsDaoProvider.class,method="addGoodsList")
		boolean addGoods(Goods goods);
	 /**
	  * 内部类
	  * @author suyin
	  *
	  */
	 class GoodsDaoProvider {  
	        public String findGoodsList(String goods_name, Integer ptype_id, Integer pctype_id, Integer curr, Integer limit) {  
	            String sql = "SELECT goods_id,goods_img,goods_name,goods_status,goods_desc,goods_price,goods_num,ptype_id,pctype_id FROM goods";  
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
	        
	        public String changeGoodsList(Goods goods) {
	        	return new SQL() {
					{
		                UPDATE("goods");
		                if(goods.getGoods_name()!=null&&goods.getGoods_name()!=""){
		                    SET("goods_name = #{goods_name}");
		                }
		                if(goods.getGoods_img()!=null&&goods.getGoods_img()!=""){
		                    SET("goods_img = #{goods_img}");
		                }
		                if(goods.getGoods_num()!=null&&goods.getGoods_num()!=0) {
		                	 SET("goods_num = #{goods_num}");
			        	}
		            	if(goods.getGoods_num()==0) {
		            		 SET("goods_num = 0");
		            		 SET("goods_status = false");
			        	}
		            	if(goods.getGoods_price()!=null) {
		            		 SET("goods_price = #{goods_price}");
			        	}
		            	if(goods.getGoods_desc()!=null&&goods.getGoods_desc()!="") {
		            		 SET("goods_desc = #{goods_desc}");
			        	}
		            	if(goods.getPtype().getPtype_id()!=null&&goods.getPtype().getPtype_id()!=0) {
		            		SET("ptype_id = #{ptype_id}");
			        	}
			        	if(goods.getPctype().getPctype_id()!=null&&goods.getPctype().getPctype_id()!=0) {
			        		SET(" pctype_id = #{pctype_id}");
			        	}
		                WHERE(" goods_id = #{goods_id}");
					}
				}.toString();
	        }
	        
	        public String addGoodsList(Goods goods) {
	        	return new SQL(){
	                {
	                    INSERT_INTO("T_PERSON_INFO");
	                    if(goods.getGoods_name()!=null&&goods.getGoods_name()!=""){
	                    	VALUES("goods_name "," #{goods_name}");
		                }
		                if(goods.getGoods_img()!=null&&goods.getGoods_img()!=""){
		                	VALUES("goods_img "," #{goods_img}");
		                }
		                if(goods.getGoods_num()!=null&&goods.getGoods_num()!=0 ){
		                	VALUES("goods_num "," #{goods_num}");
			        	}
		            	if(goods.getGoods_num()<=0) {
		            		VALUES("goods_num "," 0");
		            		VALUES(" goods_status "," false");
			        	}      	
		            	if(goods.getGoods_price()!=null) {
		            		VALUES("goods_price "," #{goods_price}");
			        	}
		            	if(goods.getGoods_price()<=0) {
		            		VALUES("goods_price"," 0");
			        	}
		            	if(goods.getGoods_desc()!=null&&goods.getGoods_desc()!="") {
		            		VALUES("goods_desc "," #{goods_desc}");
			        	}
		            	if(goods.getPtype().getPtype_id()!=null&&goods.getPtype().getPtype_id()!=0) {
		            		VALUES("ptype_id "," #{ptype_id}");
			        	}
			        	if(goods.getPctype().getPctype_id()!=null&&goods.getPctype().getPctype_id()!=0) {
			        		VALUES("pctype_id "," #{pctype_id}");
			        	}
	                }
	            }.toString();
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


	@Select("select * from goods where goods_name = #{goods_name}")
	boolean selecGoodsByName(String goods_name);

	@Update("update goods set goods_num = goods_num+#{goods_num} where goods_id=#{goods_id}")
	boolean updateGodosNum(Goods goods);





	
	
	
}
