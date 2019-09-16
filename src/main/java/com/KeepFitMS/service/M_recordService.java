package com.KeepFitMS.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.KeepFitMS.entity.M_record;

public interface M_recordService {
	//获取所有记录
		List<M_record> getAllM_records();
		//分页查询记录
		
		List<M_record> getM_recordsByTimeLimit(int pageNo,int pageSize);
		
		//用户每次到店记录
		int addRecord(M_record mr);
		//删除一条记录
		int deleteRecordByMrid(int mrid);
		//筛选记录
		List<M_record> shaixuanM_records(M_record mr);
}
