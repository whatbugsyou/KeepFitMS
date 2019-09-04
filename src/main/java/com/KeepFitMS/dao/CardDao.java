package com.KeepFitMS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KeepFitMS.entity.Card;

/*
 * 会员卡持久层
 * */
@Mapper
public interface CardDao {
	//用户开卡(和录入会员信息同时进行)
	@Insert("insert into card (ctype,cprice,cmoney,cpoints,mid,sdate,edate) values(#{ctype},#{cprice},#{cmoney},#{cpoints},#{mid},#{sdate},#{edate})")
	int  addCard(Card card);
	//根据类型查询卡
	@Select("select *from card where ctype=#{ctype}")
	List<Card> getCardsByType(String ctype);
	//根据卡号查询会员卡余额
	@Select("select cmoney from card where cid=#{cid}")
	int getCardMoney(int cid);
	//根据卡号充值
	@Update("update card c set c.cmoney=c.cmoney+{#money} where c.cid=#{cid}")
	int deposit(@Param("money")int money,@Param("cid")int cid);
	//删除卡
	@Delete("delete from card where cid=#{cid}")
	int delCard(int cid);
}
