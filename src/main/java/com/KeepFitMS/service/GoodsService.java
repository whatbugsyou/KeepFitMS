package com.KeepFitMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.KeepFitMS.entity.Goods;
import com.KeepFitMS.entity.Pctype;
import com.KeepFitMS.entity.Ptype;
import com.KeepFitMS.entity.Records;
import com.KeepFitMS.exception.PersonnelServiceException;

public interface GoodsService {


	List<Ptype> selectAllPtype(); 

	List<Pctype> selectPctypeByPtypeId(Integer ptype_id) ;

	List<Goods> selectGoods(String name, Integer provid, Integer cityid, Integer curr, Integer limit) throws PersonnelServiceException;

	Integer selectAllCount(String name, Integer provid, Integer cityid ) throws PersonnelServiceException;

	int updateGoodsStatus(Integer id, Boolean status)  throws PersonnelServiceException;

	int updateGoods(Goods goods)  throws PersonnelServiceException;

	int insertGoods(Goods goods)  throws PersonnelServiceException;

	List<Records> selectRecords(String startRecords_time, String endRecords_time, Integer curr, Integer limit) ;

	Integer selectRecordsCount(String startRecords_time, String endRecords_time)  ;

	Integer selectRcordsCountByptypeid1() ;

	Integer selectRcordsCountByptypeid2() ;

	Integer selectRcordsMoneyByptypeid1() ;

	Integer selectRcordsMoneyByptypeid2() ;

	List<Integer> selectCountByTime(String records_time) ;

	List<Integer> selectMoneyByTime(String records_time);

	int use(String start_time, String end_time, Integer records_money, int goods_id, int cid,int goods_num) throws PersonnelServiceException;

}
