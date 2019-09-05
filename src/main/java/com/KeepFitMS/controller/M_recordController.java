package com.KeepFitMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//会员来访记录控制层
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KeepFitMS.entity.M_record;
import com.KeepFitMS.service.M_recordService;
import java.util.List;
@Controller
public class M_recordController {
	@Autowired
	private M_recordService mrs;
	
	//测试方法
	@ResponseBody
	@RequestMapping("m_recordTest")
	public String Test(int pageNo,int pageSize) {
		int firstParam=(pageNo-1)*pageSize;
	
		List<M_record> lm=mrs.getM_recordsByTimeLimit(firstParam, pageSize);
		for (M_record m_record : lm) {
			System.out.println(m_record);
		}
		return "test";
	}
	
	
	@RequestMapping("addM_record.do")
	public String addM_record(M_record mr) {
		mrs.addRecord(mr);
		return "test.html";
	}
	
}
