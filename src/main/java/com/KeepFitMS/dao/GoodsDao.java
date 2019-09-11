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
import com.KeepFitMS.entity.Records;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
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
	  * 使用内部类完成动态sql 查询商品条数
	  * @param goods_name
	  * @param ptype_id
	  * @param pctype_id
	  * @return Integer
	  */
	 @SelectProvider(type = GoodsDaoProvider.class, method = "findGoodsListCount")  
	Integer selectAllCount(String goods_name, Integer ptype_id, Integer pctype_id); 
	 
	 /**
		 * 查询出售记录
		 * @param startRecords_time
		 * @param endRecords_time
		 * @param curr
		 * @param limit
		 * @return List<Records>
		 */
		 @SelectProvider(type = GoodsDaoProvider.class, method = "findRecordsList")  
		 @Results(//这里设计的是，商品对父类型以及子类型是多对一关系
					id = "allRecordsGoods",value ={
					@Result(property = "goods" ,column = "goods_id",
							one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectGoodsById"))
			})
		List<Records> selectRecords(String startRecords_time, String endRecords_time, Integer curr, Integer limit);
		 
		 /**
		  * 查询出售记录条数
		  * @param startRecords_time
		  * @param endRecords_time
		  * @return Integer
		  */
		 @SelectProvider(type = GoodsDaoProvider.class, method = "findRecordsListCount")  
		 Integer selectRecordsCount(String startRecords_time, String endRecords_time);
		/**
		 * 根据商品名称查	
		 * @param goods_name
		 * @return
		 */
		@Select("select * from goods where goods_name = #{goods_name}")
		List<Goods> selecGoodsByName(String goods_name);

		/**
		 * 根据商品id称查	
		 * @param goods_name
		 * @return
		 */
		@Select("select * from goods where goods_id = #{goods_id}")
		@Results(//这里设计的是，商品对父类型以及子类型是多对一关系
				id = "allGoodsTypeCtypeById",value ={
				@Result(property = "ptype" ,column = "ptype_id",
						one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPtypeById")),
				@Result(property = "pctype",column = "pctype_id",
						one = @One(select = "com.KeepFitMS.dao.GoodsDao.selectPctypeByPctypeid"))
			})
		Goods selectGoodsById(Integer goods_id);
		

	
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
	 * 根据父类型id查商品id
	 * @param ptype_id
	 * @return
	 */
	 @Select("select goods_id from goods where ptype_id = #{ptype_id}")
	 List<Integer> selectGoodsByPtypeid(Integer ptype_id);

	 /**
	  * 根据商品id查总条数
	  * @param goods_id
	  * @return
	  */
	 @Select("select count(*) from records where goods_id = #{goods_id}")
	 List<Integer> selectRecordsCountBygoodsid(Integer goods_id);
	 
	 

	 /**
	  * 根据时间查记录条数
	  * @param records_time
	  * @return
	  */
	 @Select("select count(*) from records where records_time like  '%${records_time}%' ")
	 List<Integer> selectCountByTime(String records_time);
	 
	 /**
	  * 根据时间查总金额
	  * @param records_time
	  * @return
	  */
	 @Select("select sum(records_money) from records where  records_time like  '%${records_time}%' ")
	 List<Integer> selectMoneyByTime(String records_time);
	 
	 /**
	  * 根据商品id查金额总数
	  * @param goods_id
	  * @return
	  */
	 @Select("select records_money from records where goods_id = #{goods_id}")
	 List<Integer> selectRecordsMoneyBygoodsid(Integer goods_id);
	 
	/**
	 * 修改商品状态
	 * @param goods_id
	 * @param status
	 * @return
	 */
	@Update("update goods set goods_status = #{goods_status} where goods_id=#{goods_id}")
	int updateGoodsStatus(Integer goods_id, Boolean goods_status);
	 /**
	  * 使用内部类完成动态sql 修改商品
	  * @param goods
	  * @return boolean
	  */
		@UpdateProvider(type= GoodsDaoProvider.class,method="changeGoodsList")
		int updateGoods(Goods goods);

	/**
	 * 根据id修改数量
	 * @param goods
	 * @return
	 */
	@Update("update goods set goods_num = goods_num+#{goods_num} where goods_id=#{goods_id}")
	int updateGodosNum(Goods goods);


	
	/**
	 * 使用内部类完成动态sql 增加商品
	 * @param goods
	 * @return boolean
	 */
	@InsertProvider(type= GoodsDaoProvider.class,method="addGoodsList")
	int insertGoods(Goods goods);
	
	/**
	 * 使用内部类完成动态sql 增加出售记录
	 * @param uuid_8
	 * @param start_time
	 * @param end_time
	 * @param records_money
	 * @param goods_id
	 * @param cid
	 */
	@InsertProvider(type= GoodsDaoProvider.class,method="addRecordsList")
	void insertRecords(String uuid_8, String start_time, String end_time, Integer records_money, int goods_id, int cid);



	 /**
	  * 内部类
	  * @author suyin
	  *
	  */
	 class GoodsDaoProvider {  
		 //查询商品集合
	        public String findGoodsList(String goods_name, Integer ptype_id, Integer pctype_id, Integer curr, Integer limit) {  
	            String sql = "SELECT goods_id,goods_img,goods_name,goods_status,goods_desc,goods_price,goods_num,ptype_id,pctype_id FROM goods";  
	            if(goods_name!=null&&goods_name!=""){  
	                sql += " where goods_name like '%${goods_name}%'";  
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
	        //查询商品集合条数
	        public String findGoodsListCount(String goods_name, Integer ptype_id, Integer pctype_id) {  
	            String sql = "SELECT count(*) FROM goods";  
	            if(goods_name!=null&&goods_name!=""){  
	                sql += " where goods_name like '%${goods_name}%'";  
	            }  
	            if(ptype_id!=null&&ptype_id!=0&&pctype_id==null&&pctype_id==0) {
	            	 sql += " where ptype_id = #{ptype_id}";  
	            }
	            if(ptype_id!=null&&ptype_id!=0&&pctype_id!=null&&pctype_id!=0) {
	            	sql += " where ptype_id = #{ptype_id} and pctype_id = #{pctype_id}";  
	            }
	            return sql;  
	        }  
	        //查询出售记录集合
	        public String findRecordsList(String startRecords_time, String endRecords_time, Integer curr, Integer limit) {
				return new SQL() {{
					SELECT("records_uuid,start_time,end_time,records_time,goods_id,cid,records_money");
					FROM("records");
					if(startRecords_time!=null&&endRecords_time!=null) {
						WHERE("records_time between #{start_time} and #{end_time}");
					}
					LIMIT("#{curr},#{limit}");
					ORDER_BY("records_time desc");
				
				}}.toString();
	        	
	        }
	        //查询出售记录集合条数
	        public String findRecordsListCount(String startRecords_time, String endRecords_time) {
				return new SQL() {{
					SELECT("count(*)");
					FROM("records");
					if(startRecords_time!=null&&endRecords_time!=null) {
						WHERE("records_time between #{start_time} and #{end_time}");
					}
				
				}}.toString();
	        	
	        }
	        
	        //修改商品信息
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
		            		SET("ptype_id = #{ptype.ptype_id}");
			        	}
			        	if(goods.getPctype().getPctype_id()!=null&&goods.getPctype().getPctype_id()!=0) {
			        		SET(" pctype_id = #{pctype.pctype_id}");
			        	}
		                WHERE(" goods_id = #{goods_id}");
					}
				}.toString();
	        }
	        
	        //增加商品
	        public String addGoodsList(Goods goods) {
	        	return new SQL(){
	                {
	                    INSERT_INTO("goods");
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
		               	if(goods.getGoods_num()!=null) {
		            		VALUES(" goods_status "," #{goods_status}");
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
		            		VALUES("ptype_id "," #{ptype.ptype_id}");
			        	}else {
			        		VALUES("ptype_id "," #{ptype.ptype_id}");
			        	}
			        	if(goods.getPctype().getPctype_id()!=null&&goods.getPctype().getPctype_id()!=0) {
			        		VALUES("pctype_id "," #{pctype.pctype_id}");
			        	}
	                }
	            }.toString();
	        }
	        public String addRecordsList(String uuid_8, String start_time, String end_time, Integer records_money, int goods_id, int cid) {
	        	return new SQL(){
	                {
	                    INSERT_INTO("records");
	                    VALUES("records_uuid", "#{uuid_8}");
	                    if(end_time!=null) {
	                    	 VALUES("start_time", "#{start_time}");
	                    	 VALUES("end_time", "#{end_time}");
	                    }
	                    VALUES("records_time", "#{start_time}");
	                    VALUES("records_money", "#{records_money}");
	                    VALUES("goods_id", "#{goods_id}");
	                    VALUES("cid", "#{cid}");
	                   
	                }

	            }.toString();
	            
	        }
}





	



























	
	
	
}
